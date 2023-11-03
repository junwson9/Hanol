import torch
import time
from torchvision import transforms
from PIL import Image
from io import BytesIO
from fastapi import UploadFile
import concurrent.futures

class DiagnosticAI:
    def __init__(self):
        self.models = [self.load_model(f'AI_model/model{i}.pt') for i in range(1, 7)]

    def load_model(self, model_path):
        return torch.load(model_path, map_location=torch.device('cuda' if torch.cuda.is_available() else 'cpu'))

    def predict(self, model, input_batch):
        with torch.no_grad():
            output = model(input_batch)
        probabilities = torch.nn.functional.softmax(output[0], dim=0)
        class_names = [0, 1, 2, 3]
        top_p, top_class = probabilities.topk(1, dim=0)
        predicted_class = class_names[top_class]
        return predicted_class

    async def process_diagnostic(self, image_bytes: UploadFile, sse_id: int):
        image_content = await image_bytes.read()
        image = Image.open(BytesIO(image_content))
        
        time1 = time.time()

        image_size = (600, 600)
        normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
        preprocess = transforms.Compose([
            transforms.Resize(image_size),
            transforms.ToTensor(),
            normalize
        ])

        input_tensor = preprocess(image)
        input_batch = input_tensor.unsqueeze(0)

        # 병렬로 모델 처리
        with concurrent.futures.ThreadPoolExecutor() as executor:
            predictions = list(executor.map(self.predict, self.models, [input_batch]*6))

        time2 = time.time()

        print(time2 - time1)

        response = {
            "sse_id": sse_id,
            "value1": predictions[0], #미세각질
            "value2": predictions[1], #피지과다
            "value3": predictions[2], #모낭사이홍반
            "value4": predictions[3], #모낭홍반농포
            "value5": predictions[4], #비듬
            "value6": predictions[5]  #탈모
        }

        print("결과 출력 완료", response)

        return response
