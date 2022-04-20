import os

def true_or_exit(condition, message="Error: Condition failed."):
    if not condition:
        print(message)
        exit()

def file_exists(filename, message="File not exists."):
    if not os.path.exists(filename):
        print(message, filename)
        exit()