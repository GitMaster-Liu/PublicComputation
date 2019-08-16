# -*- coding: cp936 -*-
'''
    Flask Web后端，FTP上传
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/09
'''
import ftplib

class FTPup(object):
    def __init__(self):
        host = '192.168.3.187'
        username = '123'
        password = '5zaidlut'
        # ftp链接
        self.conn = ftplib.FTP(host)
        self.conn.login(username,password)


    def file_upload(self,file_remote,file_local,username,project):
        self.conn.cwd('Results/' + username + '/' + project)
        bufsize = 1024
        fp = open(file_local,'rb')
        self.conn.storbinary('STOR ' + file_remote, fp, bufsize)
        fp.close()

