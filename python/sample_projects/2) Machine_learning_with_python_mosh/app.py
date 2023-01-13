# import pandas as pd
# music_data = pd.read_csv('music.csv')
# #print(df.shape)
# x = music_data.drop(columns=['genre'])
# y = music_data['genre']

# #  learning and predicting
# from sklearn.tree import DecisionTreeClassifier
# model = DecisionTreeClassifier()
# model.fit(x,y)
# print(music_data)
# pre= model.predict([ [21 , 1 ], [22, 0] ])
# print(pre)


# # calculating accuracy of model
# from sklearn.model_selection import train_test_split
# x_train ,x_test, y_train,y_test = train_test_split(x,y,test_size = 0.2)
# model.fit(x_train, y_train)
# predictions = model.predict(x_test)
# from sklearn.metrics import accuracy_score
# print(accuracy_score(y_test,predictions))

# presisting model
import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.externals import joblib
music_data = pd.read_csv('music.csv')
x = music_data.drop(columns=['genre'])
y = music_data['genre']
model = DecisionTreeClassifier()
model.fit(x,y)
# pre= model.predict([ [21 , 1 ], [22, 0] ])
x= joblib.dump(model,'mi=usic-recommender.joblib')
print(x)