import torch
from torchvision import transforms
from PIL import Image
import time

class DiagnosticAI:
    def __init__(self) -> None:
        pass

    async def process_diagnostic(self, request: Image):
        time1 = time.time()
        image = Image
        model_path = 'AI_model/test_model1.pt'
        model = torch.load(model_path, map_location=torch.device('cpu'))

        image_size = (600, 600)  # 모델의 입력 이미지 크기에 맞게 설정
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

        # 클래스별 확률과 예측 클래스를 출력합니다.
        class_names = ['클래스0', '클래스1', '클래스2', '클래스3']  # 클래스 이름을 모델에 따라 수정하세요.
        top_p, top_class = probabilities.topk(1, dim=0)
        predicted_class = class_names[top_class]

        print(f'예측 클래스: {predicted_class}, 확률: {top_p.item()}')
        time2 = time.time()

        print(time2-time1)

        return {"예측 클래스": predicted_class,"확률":top_p.item()}
    