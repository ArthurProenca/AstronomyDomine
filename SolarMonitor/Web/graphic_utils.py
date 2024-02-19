import matplotlib.pyplot as plt
import re
import numpy as np

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

    plt.savefig('graphic.png', dpi=300)