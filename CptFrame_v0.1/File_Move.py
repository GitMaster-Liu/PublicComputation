# -*- coding: cp936 -*-
'''
    Flask Web后端，文件压缩、移动
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/09
'''
import os

# path为console.txt文件路径
def file_move(PATH,PROJ):
    try:
        os.chdir(PATH)
        os.chdir('..')
        #os.system(r'copy D:\CptFramework\workspace\log.log PROJ')
        #os.system(r'move "' + path + r'\console.txt" '
        #        + r'"E:\CptFramework\workspace\Results"')
        #os.system(r'copy "' + path + r'\log.log" '
        #         + r'"E:\CptFramework\workspace\Results"')
        os.system('rar a '+PROJ+'.rar '+PROJ)

        path = os.getcwd()

    except:
        pass

    return path
