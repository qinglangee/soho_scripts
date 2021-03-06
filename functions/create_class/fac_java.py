import os

import file_util as fu
import config as c
from .fac_base import FacBase

class FacJava(FacBase):

    # 解析数据类型
    def parseType(self, content):
        if content == 'i':
            return 'int'
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
        appendFields = ''
        for field in beanDefine.fields:
            appendFields += 'sb.append("' + field.name
            appendFields += ':").append(' + field.name + ')'
            appendFields += '.append(",");\n'
        result = template.replace(r"${modifier}", beanDefine.fields[0].modifier)
        result = result.replace(r"${appendFields}", appendFields)
        return result
    # 生成的内容写入文件
    def writeSrcFile(self, content, beanDefine):
        outputFile = os.path.join(self.fileDir, beanDefine.classname  + '.java')
        print('outputfile:', outputFile)
        fu.write_file(outputFile, content)
