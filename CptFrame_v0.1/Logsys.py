# -*- coding: cp936 -*-
'''
    Flask Web��ˣ���־ϵͳ
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
import logging

class Logger():
    def __init__(self,PATH):
        # ����logger
        self.logger = logging.getLogger()
        self.logger.setLevel(logging.INFO)

        # �ն�Handler
        self.consoleHandler = logging.StreamHandler()
        self.consoleHandler.setLevel(logging.INFO)

        # �ļ�Handler
        self.fileHandler = logging.FileHandler(PATH, mode='w', encoding='UTF-8')
        self.fileHandler.setLevel(logging.NOTSET)

        # Formatter
        self.formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        self.consoleHandler.setFormatter(self.formatter)
        self.fileHandler.setFormatter(self.formatter)

        self.logger.addHandler(self.consoleHandler)
        self.logger.addHandler(self.fileHandler)

    def getLogger(self):
        return self.logger