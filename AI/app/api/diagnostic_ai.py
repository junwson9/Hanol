import torch
import time
from torchvision import transforms
from PIL import Image
from io import BytesIO
from fastapi import UploadFile

class DiagnosticAI:
    async def process_diagnostic(self, file: UploadFile):
        image_bytes = await file.read()

        image = Image.open(BytesIO(image_bytes))
        time1 = time.time()
        model_path = 'AI_model/test_model1.pt'
        model = torch.load(model_path, map_location=torch.device('cuda' if torch.cuda.is_available() else 'cpu'))

        image_size = (600, 600)
        normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
        preprocess = transforms.Compose([
            transforms.Resize(image_size),
            transforms.ToTensor(),
            normalize
        ])

        input_tensor = preprocess(image)
        input_batch = input_tensor.unsqueeze(0)

        with torch.no_grad():
            output = model(input_batch)

        probabilities = torch.nn.functional.softmax(output[0], dim=0)

        class_names = ['클래스0', '클래스1', '클래스2', '클래스3']
        top_p, top_class = probabilities.topk(1, dim=0)
        predicted_class = class_names[top_class]

        print(f'예측 클래스: {predicted_class}, 확률: {top_p.item()}')
        time2 = time.time()

        print(time2 - time1)

        return {"예측 클래스": predicted_class, "확률": top_p.item()}
