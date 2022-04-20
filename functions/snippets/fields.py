
from util import string_util
from .snippet_common import snippet_result

def create(unit):
    result = ''
    template_type = unit.get_field('type')
    if template_type == None:
        result = create_field(unit)
    elif template_type == 'init':
        result = create_init_fun(unit)
    elif template_type == 'check':
        result = create_check_empty(unit)
    
    result = string_util.indent(result)
    snippet_result(result, unit)
    


field_text = '''JLabel {}L;
JTextField {}T;
'''

def create_field(unit):
    '''创建字段的语句'''
    result = ''
    for line in unit.items:
        name = line[0]
        result += field_text.format(name, name)
    
    return result

init_text = '''{}L = new JLabel("{}:");
{}T = createText();
'''
def create_init_fun(unit):
    '''初始化字段的语句'''
    result = ''
    for line in unit.items:
        result += init_text.format(line[0], line[1], line[0])
    return result

check_empty_text = '''if({}T.getText().isEmpty()){{
    BaseDialog.showErr("Enter {} please.");
    return false;
}}
'''

def create_check_empty(unit):
    '''检测内容为空的语句'''
    result = ''
    for line in unit.items:
        result += check_empty_text.format(line[0], line[1])
    return result