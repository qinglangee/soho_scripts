from tkinter import *
from tkinter import ttk
import tkinter
# from PIL import Image, ImageTk
# import PIL

from base_win import BaseWin
from gui_util import showErr, showInfo
import config as c
from util import file_util as fu

class AppWin(BaseWin):
    def __init__(self):
        super().__init__(c.winTitle, c.winWidth, c.winHeight)

        self.initFrame()
    
    def initFrame(self):

        self.top = self.layout_grid(r_weight=3)
        self.bottom = self.layout_grid(r_weight=7)

        startB = self.grid_add(self.top,'button',{'text':'开始'})

        


if __name__ == "__main__":
    app = AppWin()
    app.start()
    