import os
import json
import pandas as pd

path = 'value_data'

li = list(os.listdir(path))
li_name = []
li1 = []
li2 = []
li3 = []
li4 = []
li5 = []
li6 = []

for file_name in li:
    user_name = file_name[:-10]
    if user_name not in li_name:
        li_name.append(user_name)
        with open(os.path.join(path,file_name), 'r') as f:
            json_data = json.load(f)

        json.dumps(json_data)

        value1 = json_data['value_1']
        value2 = json_data['value_2']
        value3 = json_data['value_3']
        value4 = json_data['value_4']
        value5 = json_data['value_5']
        value6 = json_data['value_6']
        li1.append(value1)
        li2.append(value2)
        li3.append(value3)
        li4.append(value4)
        li5.append(value5)
        li6.append(value6)

    else:
        pass

no = list(range(1, len(li_name) + 1))

data = {
    'No': no,
    'Name' : li_name,
    'value_1' : li1,
    'value_2' : li2,
    'value_3' : li3,
    'value_4' : li4,
    'value_5' : li5,
    'value_6' : li6
}

df = pd.DataFrame(data)

df.to_excel("value_data.xlsx",index=False)