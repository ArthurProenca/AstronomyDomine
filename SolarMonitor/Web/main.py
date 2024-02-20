from typing import List
from fastapi import FastAPI, Query
from fastapi.responses import StreamingResponse
from io import BytesIO
import datetime
import image_utils
import graphic_utils
import utils
import imageio

app = FastAPI()

@app.get("/solar-monitor/{initial_date}/{number_of_days}/gif")
def get_solar_monitor_gif_from_days(initial_date: str, 
                                    number_of_days: int, 
                                    sunspot_numbers: List[str] = Query(None, description="Sunspot number"),
                                    is_backward: bool = Query(False, description="Whether to go backward in time")):
    initial_date = datetime.datetime.strptime(initial_date, "%Y-%m-%d")

    final_date = utils.get_positive_days_arr(initial_date, number_of_days)

    if is_backward:
        final_date = utils.get_negative_days_arr(initial_date, number_of_days)

    days_arr, _, images = utils.get_solar_monitor_info(initial_date, final_date, is_backward)

    images = utils.download_and_preprocess_images(images, days_arr)

    if sunspot_numbers is not None:
        images = image_utils.highlight_text_in_images(images, sunspot_numbers)

    gif_bytes = BytesIO()
    imageio.mimsave(gif_bytes, images, format='gif', fps=1, loop=0)
    gif_bytes.seek(0)

    return StreamingResponse(gif_bytes, media_type="image/gif")



@app.get("/solar-monitor/{initial_date}/{number_of_days}")
def get_solar_monitor_info(
        initial_date: str,
        number_of_days: int,
        sunspot_numbers: List[str] = Query(None, description="Sunspot number"),
        is_backward: bool = Query(False, description="Whether to go backward in time")
):
    initial_date = datetime.datetime.strptime(initial_date, "%Y-%m-%d")

    final_date = utils.get_positive_days_arr(initial_date, number_of_days)

    if is_backward:
        final_date = utils.get_negative_days_arr(initial_date, number_of_days)

    days_arr, table_contents, images = utils.get_solar_monitor_info(initial_date, final_date, is_backward)

    table_contents_aux = utils.convert_table_contents_to_json(table_contents)
    result = []

    images = utils.download_and_preprocess_images(images, days_arr)

    utils.process_positions(table_contents_aux, result, days_arr)

    if sunspot_numbers is not None:
        result = utils.get_by_noaa_number(result, sunspot_numbers)

    img_bytes = BytesIO()

    graphic_utils.create_graphic(result, img_bytes)

    img_bytes.seek(0)
    return StreamingResponse(img_bytes, media_type="image/png")
