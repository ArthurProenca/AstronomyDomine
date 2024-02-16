import numpy as np
import datetime
import scrapping
import re
import json
import matplotlib.pyplot as plt
import imageio
import cv2
import os

def preprocessamento(imagem, day):
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

        # Adicionar o conteúdo de 'day' no canto superior da imagem recortada
        fonte = cv2.FONT_HERSHEY_SIMPLEX
        cv2.putText(imagem_recortada, day, (10, 30), fonte, 1, (255, 255, 255), 2, cv2.LINE_AA)

        return imagem_recortada

    # Se nenhum contorno for encontrado, retornar a imagem original
    return imagem


def extract_x_value(position):
    # Extrai o valor 'x' da posição no formato cddcdd(xxx, -yyy)
    x_value = re.search(r'\(([-+]?\d+)', position).group(1)
    return int(x_value)

def create_graphic(result):
    plt.figure(figsize=(18, 14))  # Ajuste o tamanho da figura conforme necessário

    for entry in result:
        noaa_number = entry['noaaNumber']
        positions = entry['latestPositions']

        # Extrair informações para o gráfico
        dates = [pos['day'] for pos in positions]
        longitudes = [pos['longitude'] for pos in positions]

        # Verificar se há pontos suficientes para a regressão linear
        if len(dates) >= 2:
            # Plotar o gráfico de dispersão
            plt.scatter(dates, longitudes, label=f'Mancha {noaa_number}', s=50)  # Ajuste o tamanho dos pontos conforme necessário

            # Ajustar uma reta (regressão linear)
            x_values = np.arange(len(dates))
            coefficients = np.polyfit(x_values, longitudes, 1)
            a, b = coefficients

            # Plotar a reta ajustada
            plt.plot(x_values, a * x_values + b, label=f'Reta de Ajuste (a={a:.2f}, b={b:.2f})', linestyle='--')

    plt.xlabel('Dia', fontsize=12)  # Ajuste o tamanho da fonte conforme necessário
    plt.ylabel('Longitude', fontsize=12)  # Ajuste o tamanho da fonte conforme necessário
    plt.title('Gráfico de Dispersão e Reta de Ajuste: Longitude versus Tempo para Cada Mancha Solar', fontsize=14)  # Ajuste o tamanho da fonte conforme necessário
    plt.legend(loc='upper left', bbox_to_anchor=(1, 1), fontsize=10)  # Ajuste o tamanho da fonte conforme necessário
    plt.grid(True)
    plt.tight_layout()
    
    # Adicionar margens
    plt.subplots_adjust(left=0.1, right=0.85, top=0.9, bottom=0.1)

    plt.savefig('scatter_plot.png', dpi=300)



def get_days_arr(initial_date, number_of_days): 
    final_date = initial_date + datetime.timedelta(days=number_of_days)
    return final_date


def process(initial_date, number_of_days):
    initial_date = datetime.datetime.strptime(initial_date, "%Y-%m-%d")

    final_date = get_days_arr(initial_date, number_of_days)

    current_date = initial_date
    images = []
    days_arr = []
    table_contents = []
    table_contents_aux = []
    result = []

    while current_date <= final_date:
        current_date_str = current_date.strftime("%Y-%m-%d")
        days_arr.append(current_date_str)
        table_contents.append(scrapping.get_table_content_from_date(current_date_str.split("-")[0],
                                                          current_date_str.split("-")[1],
                                                          current_date_str.split("-")[2]))
        images.append(scrapping.get_table_image_from_date(current_date_str.split("-")[0],
                                                             current_date_str.split("-")[1],
                                                             current_date_str.split("-")[2]))
        
        
        current_date += datetime.timedelta(days=1)

    images = [scrapping.download_img(image) for image in images]
    images_aux = []
    days_arr_index = 0

    for image in images:
        images_aux.append(preprocessamento(image, days_arr[days_arr_index]))
        days_arr_index += 1

    images = images_aux

    [table_contents_aux.append(json.loads(table_content)) for table_content in table_contents]

    for i, table_content in enumerate(table_contents_aux):
        for entry in table_content:
            number = entry['NOAA Number']
            position = entry['Latest Position']
            x_coordinate = entry['Coordinate X'].replace('"', '')
            y_coordinate = entry['Coordinate Y'].replace('"', '')


            longitude = int(x_coordinate) * 0.09
            latitude = int(y_coordinate) * 0.09
            longitude = round(longitude, 2)
            latitude = round(latitude, 2)

            day = days_arr[i]  # Dia correspondente ao item atual
            found = False
            for item in result:
                if item['noaaNumber'] == number:
                    item['latestPositions'].append({'position': position, 'day': day,'x_coordinate': x_coordinate, 
                                'y_coordinate': y_coordinate,
                                'longitude': longitude,
                                'latitude': latitude})
                    found = True
                    break
            if not found:
                result.append({'noaaNumber': number, 'latestPositions': 
                               [{'position': position, 
                                'day': day, 
                                'x_coordinate': x_coordinate, 
                                'y_coordinate': y_coordinate,
                                'longitude': longitude,
                                'latitude': latitude}]})
                
    create_graphic(result)

    imageio.mimsave('movie.gif', images, fps=1, loop=0)
# Chamada da função
process("2023-12-25", 3)
