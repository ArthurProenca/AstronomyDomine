import cv2
import requests
import numpy as np
import easyocr
import datetime
import imageio
import matplotlib.pyplot as plt
from scrapping import get_image_links_from_date
import re

reader = easyocr.Reader(['en'], gpu=True)


def encontrar_textos_e_posicoes(imagem):
    imagem_processada = preprocessamento(imagem)
    result = reader.readtext(imagem_processada)
    textos_e_posicoes = []
    manchas_existentes = set()
    altura, largura, _ = imagem.shape
    centro_x, centro_y = largura // 2, altura // 2  # Calculando o centro da imagem

    for detection in result:
        texto = detection[1]
        pontos = detection[0]

        # Obtendo o centro do bounding box do texto
        x_centro = (pontos[0][0] + pontos[2][0]) / 2
        y_centro = (pontos[0][1] + pontos[2][1]) / 2

        # Convertendo para o sistema cartesiano desejado (-1000 a 1000)
        x_cartesiano = ((x_centro - centro_x) / centro_x) * 1000
        y_cartesiano = -((y_centro - centro_y) / centro_y) * 1000  # Negativo para inverter a direção do eixo Y

        if re.match(r'^\d{5}$', texto):

            textos_e_posicoes.append((texto, (x_cartesiano, y_cartesiano)))
            if texto.isdigit() and len(texto) == 5 and len(pontos) == 2:
                manchas_existentes.add(texto)

    return textos_e_posicoes, manchas_existentes


def baixar_imagem(url):
    response = requests.get(url)
    imagem = cv2.imdecode(np.frombuffer(response.content, np.uint8), cv2.IMREAD_COLOR)
    return imagem

def preprocessamento(imagem):
    # Converter a imagem para escala de cinza
    imagem_cinza = cv2.cvtColor(imagem, cv2.COLOR_BGR2GRAY)

    # Aplicar um desfoque para reduzir o ruído
    imagem_desfocada = cv2.GaussianBlur(imagem_cinza, (5, 5), 0)

    # Binarizar a imagem para destacar os elementos escuros (assumindo que o retângulo é preto)
    _, imagem_binaria = cv2.threshold(imagem_desfocada, 50, 255, cv2.THRESH_BINARY_INV)

    # Encontrar contornos na imagem binarizada
    contornos, _ = cv2.findContours(imagem_binaria, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    maior_contorno = None
    maior_area = 0

    # Encontrar o maior contorno interno (presumivelmente o retângulo preto dentro da área branca)
    for contorno in contornos:
        area = cv2.contourArea(contorno)
        if area > maior_area:
            maior_area = area
            maior_contorno = contorno

    # Se houver um contorno identificado como maior, recortar a região retangular correspondente
    if maior_contorno is not None:
        x, y, w, h = cv2.boundingRect(maior_contorno)
        imagem_recortada = imagem[y:y+h, x:x+w]
        cv2.imwrite("imagem_recortada.png", imagem_recortada)
        return imagem_recortada

    # Se nenhum contorno for encontrado, retornar a imagem original
    return imagem

def process(initial_date, final_date):
    initial_date = datetime.datetime.strptime(initial_date, "%Y-%m-%d")
    final_date = datetime.datetime.strptime(final_date, "%Y-%m-%d")
    current_date = initial_date

    days_arr = []

    urls_imagens = []

    cont_days_arr = 0

    objs_arr = []

    gif = []

    while current_date <= final_date:
        current_date_str = current_date.strftime("%Y-%m-%d")
        days_arr.append(current_date_str)
        urls_imagens.append(get_image_links_from_date(current_date_str.split("-")[0],
                                                       current_date_str.split("-")[1],
                                                       current_date_str.split("-")[2])[0])
        
        current_date += datetime.timedelta(days=1)

    print(days_arr)
        
        


    for url_imagem in urls_imagens:
        imagem = baixar_imagem(url_imagem)
        
        gif.append(imagem)

        day_of_detection = days_arr[cont_days_arr]

        textos_e_posicoes, _ = encontrar_textos_e_posicoes(imagem)

        objs_arr.append({
            'day': day_of_detection,
            'objs': textos_e_posicoes
        })

        cont_days_arr += 1

    plot_data = {}
    for entry in objs_arr:
        day = entry['day']
        for label, (x, y) in entry['objs']:
            if label not in plot_data:
                plot_data[label] = {'x': [], 'y': []}
            plot_data[label]['x'].append(day)
            plot_data[label]['y'].append(x)



    # Plotagem
    plt.figure(figsize=(12, 8))

    for label, values in plot_data.items():
        if len(values['x']) >= 2:  # Verifica se há pelo menos 2 pontos para formar um segmento
            plt.scatter(values['x'], values['y'], marker='o', label=label)

    plt.xlabel('Data')
    plt.ylabel('Coordenada Y')
    plt.title('Gráfico das manchas ao longo do tempo')
    plt.legend(loc='upper left', bbox_to_anchor=(1, 1))
    plt.grid(True)
    plt.tight_layout()

    plt.savefig('posicao_y_mancha_solar.png', dpi=300)

    with imageio.get_writer('imagens_com_texto_deslocamento_esfera.gif', fps=1, loop=0) as writer:
        for imagem in gif:
            writer.append_data(gif)

process("2023-11-14", "2023-11-21")