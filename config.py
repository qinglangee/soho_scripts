import os

script_dir = os.path.dirname(os.path.realpath(__file__))
# scriptDir = script_dir
# init 脚本的模板目录
template_dir = os.path.join(script_dir, 'templates')
templateDir = template_dir

# create_class 脚本的模板目录
create_template_dir = os.path.join(script_dir, 'create_templates')
createTemplateDir = create_template_dir

# create_code 脚本的模板目录
code_template_dir = os.path.join(script_dir, 'code_template')