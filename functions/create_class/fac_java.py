import os

import file_util as fu
import config as c
from .fac_base import FacBase

class FacJava(FacBase):

    # 解析数据类型
    def parseType(self, content):
        if content == 'i':
            return 'int'
        elif content == 'l':
            return 'long'
        elif content == 's':
            return 'String'
        elif content == 'f':
            return 'float'
        elif content == 'd':
            return 'double'
        else:
            return content

    # 读取源文件模板
    def readTemplateLines(self):
        lines = fu.read_lines(os.path.join(c.createTemplateDir, 'java01.java'))
        return lines

    # 转换注释格式
    def createComment(self, comment, before="", after=""):
        # print('use fac java.createComment()')
        return '/** ' + before + comment + after + ' */'

    # 创建 toString 方法
    def createToString(self, template, beanDefine):
        if beanDefine.toString == 0:
            return ""
        appendFields = ''
        for field in beanDefine.fields:
            appendFields += 'sb.append("' + field.name
            appendFields += ':").append(' + field.name + ')'
            appendFields += '.append(",");\n'
        result = template.replace(r"${modifier}", beanDefine.fields[0].modifier)
        result = result.replace(r"${appendFields}", appendFields)
        return result

    # 生成文件名
    def getBeanFilename(self, beanDefine):
        return os.path.join(self.fileDir, beanDefine.classname  + '.java')
