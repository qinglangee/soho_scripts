import os
from util import file_util as fu
from util import string_util as su
from ..template import parser
from ..template import line_spliter
import config as c

# 读取页面模板，组装行列字段
def process(src_dir, filename):
    tpl_snippets = parser.parse_file(os.path.join(c.code_template_dir, "java/manager_tpl.txt"))
    units = line_spliter.parse_file(filename)
    setting = units[0]

    for unit in units[1:]:
        class_name = su.upper01(unit.name)
        field_dict = {}
        field_dict['bean'] = class_name
        content = tpl_snippets['file'].replace(field_dict)
        package_dir = setting.get_field('package').replace(".", "/")
        dest_file = os.path.join(src_dir, setting.get_field('output'), package_dir, class_name + "Manager.java")
        fu.write_file(dest_file, content)
