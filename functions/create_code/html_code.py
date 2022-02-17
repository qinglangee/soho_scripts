import os
from util import file_util as fu
from ..template import parser
import config as c

# 读取页面模板，组装行列字段
def process(src_dir, filename):
    tpl_snippets = parser.parse_file(os.path.join(c.code_template_dir, "html/html_tpl.txt"))
    lines = fu.read_lines(filename)

    rows_text = ""
    for line in lines:
        rows_text += tpl_snippets['row'].replace({'col_name':line})

    file_text = tpl_snippets['file'].replace({'rows': rows_text})
    fu.write_file(os.path.join(src_dir, "aa.html"), file_text)
