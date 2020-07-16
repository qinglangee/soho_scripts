from tkinter import *
import tkinter

# from PIL import Image, ImageTk
# import PIL


from base_win import BaseWin
from gui_util import *
import config as c
import file_util as fu

class AppWin(BaseWin):
    def __init__(self):
        super().__init__(c.winTitle, c.winWidth, c.winHeight)

if __name__ == "__main__":
    AppWin().start()