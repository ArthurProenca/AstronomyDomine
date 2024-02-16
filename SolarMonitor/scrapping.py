import requests
from bs4 import BeautifulSoup
import re
import json
import cv2
import requests
import numpy as np


base_url = "https://www.solarmonitor.org"
solar_monitor_url = base_url + "/full_disk.php?date={}&type=shmi_maglc&indexnum=1"

def get_html(url):
    return requests.get(url)

def get_soup(response):
    soup = BeautifulSoup(response.content, 'html.parser')
    return soup

def get_image_links_from_date(year, month, day):
    html_content = get_soup(get_html(solar_monitor_url.format(year+month+day, "")))
    links = html_content.find_all('a', string=re.compile(r'.*fd.*'))
    links = [link.get('href') for link in links]
    links = [solar_monitor_url.format(year, month, day, link) for link in links]
    return links

def get_table_content_from_date(year, month, day):
    # Supondo que você tenha a função get_soup e a URL correta definida
    html_content = get_soup(get_html(solar_monitor_url.format(year + month + day, "")))
    tables = html_content.find_all('div', class_='noaat')
    ordered_pair_regex_pattern = re.compile(r'\((.*?)\)')

    data = []

    for table in tables:
        rows = table.find_all('tr', class_='noaaresults')
        for row in rows:
            cells = row.find_all('td')
            coordinates_match = ordered_pair_regex_pattern.findall(cells[1].text.strip())
            coordinate_x = coordinates_match[0].split(",")[0]
            coordinate_y = coordinates_match[0].split(",")[1]
            entry = {
                "NOAA Number": cells[0].text.strip(),
                "Latest Position": cells[1].text.strip(),
                "Coordinate X": coordinate_x,
                "Coordinate Y": coordinate_y,
                "Hale Class": cells[2].text.strip(),
                "McIntosh Class": cells[3].text.strip(),
                "Sunspot Area [millionths]": cells[4].text.strip(),
                "Number of Spots": cells[5].text.strip(),
                "Recent Flares": [link.text.strip() for link in cells[6].find_all('a')]
            }
            data.append(entry)

    return json.dumps(data, indent=2, ensure_ascii=False)


def get_table_image_from_date(year, month, day):
    html_content = get_soup(get_html(solar_monitor_url.format(year + month + day, "")))
    # Encontra todas as tags de imagem <img> com a string 'shmi' no atributo src
    img_tags = html_content.find_all('img', src=lambda value: value and 'shmi_maglc_fd' in value)

    # Itera sobre as tags <img> que atendem ao critério especificado acima
    for img in img_tags:
        img_src = img.get('src')
        img_src = base_url + "/" + img_src
  # Imprime o src da imagem que contém 'shmi' no atributo src
        return (""+img_src)  # Retorna o primeiro src encontrado que satisfaz a condição



def download_img(url):
    response = requests.get(url)
    imagem = cv2.imdecode(np.frombuffer(response.content, np.uint8), cv2.IMREAD_COLOR)
    return imagem