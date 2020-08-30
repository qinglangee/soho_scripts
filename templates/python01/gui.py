from tkinter import *
from tkinter import ttk
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

        self.initFrame()
    
    def initFrame(self):

        self.top = self.layoutGrid(rWeight=3)
        self.bottom = self.layoutGrid(rWeight=7)

        startB = self.gridAdd(self.top,'button',{'text':'开始'})

        


if __name__ == "__main__":
    app = AppWin()
    app.start()
    