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