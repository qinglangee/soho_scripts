
import re
from util import file_util as fu

# 表达一个片段模板的类， 有名字、文本和可替换的字段列表
class Snippet:
    def __init__(self, name="", text=""):
        self.name = name
        self.text = text
        self.fields = []

    def replace(self, dicts):
        text = self.text
        for field in self.fields:
            if field in dicts:
                text = text.replace('${'+field+'}', dicts[field])
            else:
                text = text.replace('${'+field+'}', '')
        return text
    def replace_by_list(self, str_list):
        text = self.text
        if len(self.fields) > len(str_list):
            return text
        i = 0
        for field in self.fields:
            text = text.replace('${'+field+'}', str_list[i])
            i += 1
        return text

reg_field = re.compile(r'\$\{(.*?)\}')

# 解析整个文件，转换为 Snippets
def parse_file(filename):
    lines = fu.read_lines(filename)
    templates = {}
    templateName = None
    template = ''
    for line in lines:
        if line.startswith('```'):
            line = line.strip()
            if len(line) == 3:
                templates[templateName] = parse_snippet(template, templateName)
            elif len(line) > 3:
                templateName = line[3:]
            # ``` 之外的行就忽略掉了
            template = ''
        else:
            template += line + "\n"
    return templates

# 模板文本转化为 Snippet, 解析出需要替换的变量
def parse_snippet(text, name):
    snippet = Snippet(name, text)
    fields = reg_field.findall(text)
    snippet.fields = list(set(fields))
    return snippet