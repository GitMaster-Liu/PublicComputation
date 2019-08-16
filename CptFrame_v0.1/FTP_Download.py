# -*- coding: cp936 -*-
'''
    Flask Web��ˣ�FTP��������
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
import os
import ftplib
import re


class FTPSync(object):
    def __init__(self,USER,PROJ,Logger):
        host = '192.168.3.187' #'192.168.7.17'
        username = '123' #'cims'
        password = '5zaidlut' #'nihao'
        # ftp����
        self.conn = ftplib.FTP(host)
        self.conn.login(username,password)
        self.logger = Logger
        self.logger.info("������FTP������")
        # ftp��·��
        self.conn.cwd(USER+'\\'+PROJ)
        # ����·��
        os.chdir('D:\CptFramework\workspace')
        os.chdir(PROJ)


    def get_dirs_files(self):
        pattern = re.compile(r'.+\..+')
        dir_files = self.conn.nlst()
        files = list()
        dirs = list()
        for item in dir_files:
            match = pattern.match(item)
            if match != None:
                files.append(item)
            else:
                dirs.append(item)
        return files,dirs

    def walk_download(self,next_dir):
        self.conn.cwd(next_dir)
        try:
            os.mkdir(next_dir)
        except OSError:
            pass
        os.chdir(next_dir)
        # ftp��ǰ·��
        ftp_curr_dir = self.conn.pwd()
        # ���ص�ǰ·��
        local_curr_dir = os.getcwd()
        files,dirs = self.get_dirs_files()
        for f in files:
            pf = open(f,'wb')
            buffersize = 1024
            try:
                self.conn.retrbinary('RETR %s' % f, pf.write, buffersize)
            finally:
                pf.close()

        for d in dirs:
            os.chdir(local_curr_dir)
            self.conn.cwd(ftp_curr_dir)
            self.walk_download(d)


    def run(self):
        self.walk_download('.')
        self.logger.info('�����ļ�������')

