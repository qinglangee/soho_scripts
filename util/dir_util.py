import os


# 列出目录的内容
def list_dir(dir, recursive = False, prefix=""):
    if not os.path.exists(dir):
        print("not exist", dir)
        return
    files = os.listdir(dir)
    for filename in files:
        filepath = os.path.join(dir, filename)
        if os.path.isdir(filepath):
            print(prefix, "d: ", filename)
            if recursive:
                list_dir(filepath, recursive, prefix + "    ")
        elif os.path.isfile(filepath):
            print(prefix, "f: ", filename)
        else:
            print(prefix, "o: ", filename)

# 返回符合条件的直接子文件
# @param filter 文件名包含
def get_files(dir, filter = None):
    files = []
    if not os.path.exists(dir):
        return files
    sub_files = os.listdir(dir)
    for filename in sub_files:
        if filter == None or filter in filename:
            files.append(os.path.join(dir, filename))
    
    return files

def create_dir(path):
    '''just for note'''
    os.makedirs(path)