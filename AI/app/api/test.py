import torch
from torchvision import transforms
from PIL import Image
from io import BytesIO
import concurrent.futures

class DiagnosticAI:
    def __init__(self):
        self.device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
        self.models = [self.load_model(f'AI_model/model{i}.pt') for i in range(1, 7)]

    def load_model(self, model_path):
        model = torch.load(model_path)
        return model.to(self.device)

    def predict(self, model, input_batch):
        with torch.no_grad():
            output = model(input_batch)
        probabilities = torch.nn.functional.softmax(output[0], dim=0)
        class_names = [0, 1, 2, 3]
        top_p, top_class = probabilities.topk(1, dim=0)
        predicted_class = class_names[top_class]
        return predicted_class
    
    def process_diagnostic(self,image_bytes: Image, sse_id: int):
        image_content = image_bytes.read()
        image = Image.open(BytesIO(image_content))

        image_size = (600, 600)
        normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
        preprocess = transforms.Compose([
            transforms.Resize(image_size),
            transforms.ToTensor(),
            normalize
        ])

        input_tensor = preprocess(image).to(self.device)
        input_batch = input_tensor.unsqueeze(0)

        # 병렬로 모델 처리
        with concurrent.futures.ThreadPoolExecutor() as executor:
            predictions = list(executor.map(self.predict, self.models, [input_batch]*6))

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
    
image_path = 'api/rn_image_picker_lib_temp_8236168d-b5d5-459b-9007-cb9545873bb0.jpg'

# FastAPI 서버의 엔드포인트 URL
# server_url = 'http://43.201.8.10/image'

# 이미지 파일을 바이너리로 읽기
with open(image_path, 'rb') as image_file:
    image_data = image_file.read()

test = DiagnosticAI()

print(test.process_diagnostic(image_data,1))
