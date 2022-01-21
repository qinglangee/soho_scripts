from util import file_util as fu

def process(filename):
    lines = fu.read_lines(filename)
    for line in lines:
        print("---", line)
