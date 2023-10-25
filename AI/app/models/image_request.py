from pydantic import BaseModel

class ImageRequest(BaseModel):
    image_bytes: str