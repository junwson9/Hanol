import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split,GridSearchCV,StratifiedKFold
import joblib

# 데이터 파일 경로
path = 'data/total_new_type_data.xlsx'

# 데이터 불러오기
df = pd.read_excel(path)
df = df.dropna()

# 타겟 열
target = 'type'

# 선택할 특성 열 지정
features = df[['Age', 'Gender', 'Answer7', 'treatment', 'Answer2', 'Answer3', 'Answer1', 'rinse']]

# 학습 및 테스트 데이터 분리
X = features
Y = df[target]
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2, random_state=0)

clf = RandomForestClassifier(
    n_estimators=100,
    max_depth=6,
    min_samples_leaf=18,
    min_samples_split=8,
    max_features='log2',
    bootstrap=False,
    criterion='gini',
    random_state=0
)

# 모델 학습
clf.fit(X_train, Y_train)

model_filename = 'model/random_forest_model2.pkl'
joblib.dump(clf,model_filename)

# 모델 저장
# model_filename = 'model/random_forest_model.pkl'
# joblib.dump(clf, model_filename)
