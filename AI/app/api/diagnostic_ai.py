import torch
import time
from torchvision import transforms
from PIL import Image
from io import BytesIO
from fastapi import UploadFile

class DiagnosticAI:
    def __init__(self):
        self.model1 = self.load_model('AI_model/model1.pt')
        self.model2 = self.load_model('AI_model/model2.pt')
        self.model3 = self.load_model('AI_model/model3.pt')
        self.model4 = self.load_model('AI_model/model4.pt')
        self.model5 = self.load_model('AI_model/model5.pt')
        self.model6 = self.load_model('AI_model/model6.pt')

    def load_model(self, model_path):
        return torch.load(model_path, map_location=torch.device('cuda' if torch.cuda.is_available() else 'cpu'))

    def predict(self, model, input_batch):
        with torch.no_grad():
            output = model(input_batch)
        probabilities = torch.nn.functional.softmax(output[0], dim=0)
        class_names = ['클래스0', '클래스1', '클래스2', '클래스3']
        top_p, top_class = probabilities.topk(1, dim=0)
        predicted_class = class_names[top_class]
        return predicted_class

    async def process_diagnostic(self, file: UploadFile):
        image_bytes = await file.read()

        image = Image.open(BytesIO(image_bytes))
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

        predicted_class1 = self.predict(self.model1, input_batch)
        predicted_class2 = self.predict(self.model2, input_batch)
        predicted_class3 = self.predict(self.model3, input_batch)
        predicted_class4 = self.predict(self.model4, input_batch)
        predicted_class5 = self.predict(self.model5, input_batch)
        predicted_class6 = self.predict(self.model6, input_batch)

        time2 = time.time()

        print(time2 - time1)

        response = {
            "미세각질": predicted_class1,
            "피지과다": predicted_class2,
            "모낭사이홍반": predicted_class3,
            "모낭홍반농포": predicted_class4,
            "비듬": predicted_class5,
            "탈모": predicted_class6
        }

        print("결과 출력 완료", response)

        return response
