from pydantic import BaseModel

class ImageRequest(BaseModel):
    image_bytes: bytes
    sse_id: int