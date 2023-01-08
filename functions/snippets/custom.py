from functions.template.line_spliter import DefUnit
from util import string_util
from .snippet_common import snippet_result

def create(unit):
    template = unit['template']
    values = unit['values']
    result = ''
    for val in values:
        result += template.format(*val)
    result = string_util.indent(result)
    uu = DefUnit('parts[1]')
    uu.add_field('file', unit['file'])
    uu.add_field('title', unit['title'])
    snippet_result(result, uu)