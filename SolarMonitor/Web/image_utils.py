import easyocr
import cv2
import numpy as np

def highlight_text_in_images(images, texts):
    # Create an EasyOCR reader with the desired language
    reader = easyocr.Reader(['en'])

    # For each image, highlight the specified text
    for i, image in enumerate(images):
        # Convert the image to a NumPy array
        image_np = np.array(image)

        # Convert the image to a format compatible with EasyOCR
        image_np_rgb = cv2.cvtColor(image_np, cv2.COLOR_BGR2RGB)

        # Extract the text from the image using EasyOCR
        result = reader.readtext(image_np_rgb)

        # Highlight the specified text
        for detection in result:
            detected_text = detection[1]
            for text in texts:
                if text in detected_text:
                    # Highlight the text in the original image
                    bbox = detection[0]
                    cv2.rectangle(image_np_rgb, tuple(map(int, bbox[0])), tuple(map(int, bbox[2])), (0, 255, 0), 2)

        # Convert the image back to the original format
        image = cv2.cvtColor(image_np_rgb, cv2.COLOR_RGB2BGR)
        images[i] = image

    return images

def preprocess_image(image, day):
    # Convert the image to grayscale
    gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Apply blur to reduce noise
    blurred_image = cv2.GaussianBlur(gray_image, (5, 5), 0)

    # Binarize the image to highlight dark elements (assuming the rectangle is black)
    _, binary_image = cv2.threshold(blurred_image, 50, 255, cv2.THRESH_BINARY_INV)

    # Find contours in the binarized image
    contours, _ = cv2.findContours(binary_image, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    largest_contour = None
    largest_area = 0

    # Find the largest inner contour (presumably the black rectangle inside the white area)
    for contour in contours:
        area = cv2.contourArea(contour)
        if area > largest_area:
            largest_area = area
            largest_contour = contour

    # If a contour is identified as the largest, crop the corresponding rectangular region
    if largest_contour is not None:
        x, y, w, h = cv2.boundingRect(largest_contour)
        cropped_image = image[y:y+h, x:x+w]

        # Add the 'day' content to the top left corner of the cropped image
        font = cv2.FONT_HERSHEY_SIMPLEX
        cv2.putText(cropped_image, day, (10, 30), font, 1, (255, 255, 255), 2, cv2.LINE_AA)

        return cropped_image

    # If no contour is found, return the original image
    return image
