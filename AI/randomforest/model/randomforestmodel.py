import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split,GridSearchCV,StratifiedKFold
import joblib

path = 'data/total_type_data.xlsx'
# path = 'data/total_new_type_data.xlsx'

df = pd.read_excel(path)
df = df.dropna()

target = 'type'

df = df[df.groupby('type')['type'].transform('count') != 1]

features = df[['Age', 'Gender', 'Answer7', 'treatment', 'Answer2', 'Answer3', 'Answer1', 'rinse']]

target_frequencies = df['type'].value_counts()
print(target_frequencies)

class_weights = {class_label: 1.0 / frequency for class_label, frequency in target_frequencies.items()}

X = df[features.columns]

Y = df[target]

X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2, random_state=0)

# params = {
#     'n_estimators': [10, 100],
#     'max_depth': [6, 8, 10, 12],
#     'min_samples_leaf': [8, 12, 18],
#     'min_samples_split': [8, 16, 20],
#     'max_features': ['auto', 'sqrt', 'log2', None],
#     'bootstrap': [True, False],
#     'criterion': ['gini', 'entropy']
# }
# clf = RandomForestClassifier(random_state=0)
# grid_cv = GridSearchCV(clf,param_grid=params,n_jobs=-1,cv=StratifiedKFold(n_splits=3))

# grid_cv.fit(X_train,Y_train)

# Y_pred = grid_cv.predict(X_test)

# print('최적 하이퍼 파라미터: ', grid_cv.best_params_)
# print('최고 예측 정확도: {:.4f}'.format(grid_cv.best_score_))

# clf = RandomForestClassifier(random_state=0)
# clf.fit(X_train,Y_train)
# Y_pred = clf.predict(X_test)

# accuracy = accuracy_score(Y_test,Y_pred)
# print('정확도:', accuracy)

clf = RandomForestClassifier(
    n_estimators=100,
    max_depth=8,
    min_samples_leaf=12,
    min_samples_split=8,
    max_features='sqrt',
    bootstrap=False,
    criterion='gini',
    random_state=0
)

# 모델 학습
clf.fit(X_train, Y_train)

model_filename = 'model/random_forest_model3.pkl'
joblib.dump(clf,model_filename)