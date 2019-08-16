# -*- coding: cp936 -*-
'''
    Flask Web后端，日志系统
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
import logging

class Logger():
    def __init__(self,PATH):
        # 创建logger
        self.logger = logging.getLogger()
        self.logger.setLevel(logging.INFO)

        # 终端Handler
        self.consoleHandler = logging.StreamHandler()
        self.consoleHandler.setLevel(logging.INFO)

        # 文件Handler
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