# -*- coding: cp936 -*-
'''
    Jenkins端，发送post请求
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
import xml_parse
import requests
import os
import time
import shutil

def pythonpost(authorname,projectname):
    # 解析config文件
    os.chdir('D:\\comput\\ftp_file\\'+authorname+'\\'+projectname)
    user, proj, type, path, main, mail = xml_parse.parse('config.xml')
    # 参数列表
    data_list = {'username': user,
                 'project': proj,
                 'type': type,
                 'path': path,
                 'main': main,
                 'mail': mail}
    r = requests.post('http://127.0.0.1:9010',data=data_list)#'http://192.168.7.106:8077/login'








if __name__=='__main__':

    os.chdir(r"D:\Goku.Framework.CoreUI-master\src\main\webapp\upload")
    while 1:
        time.sleep(5)
        for dirpath, dirnames, filenames in os.walk(r"D:\Goku.Framework.CoreUI-master\src\main\webapp\upload"):
            for filename in filenames:
                forword, extension = os.path.splitext("D:\\Goku.Framework.CoreUI-master\\src\\main\\webapp\\upload\\"+filename)
                if extension == '.xml':
                    try:
                        user, proj, type, path, main, mail = xml_parse.parse(filename)
                        data_list = {'username': user,
                                     'project': proj,
                                     'type': type,
                                     'path': path,
                                     'main': main,
                                     'mail': mail}
                        try:
                            requests.post('http://127.0.0.1:9010', data=data_list)
                            os.system("del D:\\Goku.Framework.CoreUI-master\\src\\main\\webapp\\upload\\" + filename)
                            shutil.rmtree("D:\\comput\\ftp_file\\" + user)
                            os.system("del D:\\Goku.Framework.CoreUI-master\\src\\main\\webapp\\upload\\" + proj + ".rar")
                        except:
                            pass
                    except:
                        os.system("del D:\\Goku.Framework.CoreUI-master\\src\\main\\webapp\\upload\\"+filename)






