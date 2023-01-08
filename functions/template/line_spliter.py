import re

from util import file_util as fu




# 表示一个单位的定义，具体是什么根据具体应用不同。 
# 主要就是把几行作为一个单元， 赋予名字或类型等不同属性。 
class DefUnit:

    def __init__(self, name=''):
        self.name = name  # 名字， 不用应用里可能有不同用处
        self.fields = [] # 除名字外的其它一些特殊属性， 元素为键值元组  [('type', 'class'), ('age','34')]
        self.items = [] # 列表的列表，每个元素代表一行，默认把行按 @ 切分, 包含 field 以外的行
        
        # 列表的列表，每个元素代表一行，默认把行按空白切分，以@开头的按@切分， 包含所有的行
        # fields 中的会以 * 开头， 为了 lines 统一处理时也可以区分
        self.lines = [] 

    def add_field(self, key, value):
        self.fields.append((key, value))
    def add_item(self, line):
        self.items.append(line)
    def add_line(self, line):
        self.lines.append(line)
    def get_field(self, name):
        for field in self.fields:
            if field[0] == name:
                return field[1]
        return None
    def has_field(self, name):
        return self.get_field(name) != None

def parse_with_setting(filename, trim=True):
    units = parse_file(filename, trim)
    return units[0], units[1:]

def parse_file(filename, trim=True):
    lines = fu.read_lines(filename)
    return parse_lines(lines, trim)


def parse_lines(lines, trim=True):
    '''解析自定义输入文件格式'''
    units = []
    unit = None
    for line in lines:
        if line.startswith("#") or line.strip() == "":
            continue
        parts = split_line(line, trim)

        if parts[0] == '*name':  # 一批行单元的名字
            if unit is not None:
                units.append(unit)
            unit = DefUnit(parts[1])
        elif parts[0].startswith("*"): # 特殊的字段
            unit.add_field(parts[0][1:], parts[1])
            unit.add_line(parts)
        else: # 普通字段
            unit.add_item(parts)
            unit.add_line(parts)
    units.append(unit)  # 添加上最后一个unit
    return units

def split_line(line, trim):
    '''把一行字符切分成字符串列表'''
    parts = []

    if line.startswith("@"):  #  “@”开头的行用@切分
        parts = line[1:].split("@")
    else:
        if '#' in line:
            line = line[:line.index('#')]  # 注释部分直接去掉
        parts = re.split("\s+", line) # 不以@开头的用空白切分

    if trim:
        parts = [part.strip() for part in parts]

    parts = list(filter(lambda x: not x.startswith('#'), parts))  # 过滤掉 # 开头的部分
    return parts
