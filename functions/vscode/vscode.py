from .tab_set import tab

def short_desc():
    hint = "vs 修改vscode 配置: 参数 1 配置内容 \n"
    hint += "    vs tab add/remove设置 tab 配置\n"
    return hint

# 打印提示信息
def print_help():
    hint = "至少需要一个参数。 \n"
    hint += "vs tab \n"
    hint += "vs java \n"
    print(hint)


# 输入参数检查
def check_input(args):
    if(len(args) < 1): 
        print_help()
        return
    if args[0] == 'tab':
        tab(args[1:])

    else:
        print_help()