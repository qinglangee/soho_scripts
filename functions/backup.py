import os
import shutil

file_list = [
    # vscode config
    ("c:/users/zhch/AppData/Roaming/Code/User/keybindings.json", "d:/eachcloud/我的坚果云/config_all/vscode/User"),
    ("c:/users/zhch/AppData/Roaming/Code/User/snippets", "d:/eachcloud/我的坚果云/config_all/vscode/User/"),
]

def execute():
    for pair in file_list:
        src = pair[0]
        dest = pair[1]
        basename = os.path.basename(src)
        dest = os.path.join(dest, basename)
        if(os.path.isfile(src)):
            if os.path.exists(dest):
                os.remove(dest)
            shutil.copyfile(src, dest)
        else:
            if os.path.exists(dest):
                shutil.rmtree(dest)
            shutil.copytree(src, dest)
