

def create_index():
  f = open("index.html")
  while f.readline():
    print(f.readline())
  f.close()

if __name__ == '__main__':
  create_index()