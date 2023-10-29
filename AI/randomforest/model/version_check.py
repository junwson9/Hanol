import sklearn
from sklearn.ensemble import RandomForestClassifier
import joblib

# 저장된 모델을 로드
# model = RandomForestClassifier()
model = joblib.load('model/random_forest_model.pkl')
# model.load('random_forest_model.pkl')

# 모델이 학습된 scikit-learn 버전 확인
model_version = model.__version__

print("모델이 학습된 scikit-learn 버전:", model_version)
