import json

def load_data():
    # read data from data.txt, and parse text content to json
    with open('data.txt', 'r') as file:
        data = file.read()
    
    data = json.loads(data)
    if 'users' not in data:
        data['users'] = []
    return data

def save_data(data):
    # write data to data.txt
    with open('data.txt', 'w') as file:
        file.write(json.dumps(data))

def find_by_name(name):
    data = load_data()
    users = data['users']
    for user in users:
        if user['username'] == name:
            return user
    return None

def find_user_by_password(name, password):
    
    data = load_data()
    users = data['users']
    for user in users:
        if user['username'] == name and user['password'] == password:
            return user
    return None

def save_user(user):
    data = load_data()
    if data['users'] is None:
        data['users'] = []
    data['users'].append(user)
    save_data(data)

def update_user(user):
    data = load_data()
    users = data['users']
    for i in range(len(users)):
        if users[i]['username'] == user['username']:
            users[i] = user
            break
    save_data(data)