import os;

def append_links(lines):
  files = os.listdir('./')
  for f in files:
    if f.endswith('.html'):
      print('---', f)
      lines.append(f'<a href="{f}">{f}</a><br>\n')

def create_index():
  new_lines = []
  f = open("index.html")
  lines = f.readlines()
  start_body = False
  end_body = False
  appended = False
  # 把 <body> </body> 中间的内容用文件列表替换
  for line in lines:
    # print(line)
    if line.strip() == '</body>':
      end_body = True
    if end_body and not appended:
      append_links(new_lines)
      appended = True
    if not start_body or end_body:
      new_lines.append(line)
    if line.strip() == '<body>':
      start_body = True
  f.close()
  f = open("index.html", 'w')
  f.writelines(new_lines)
  f.close()

if __name__ == '__main__':
  create_index()