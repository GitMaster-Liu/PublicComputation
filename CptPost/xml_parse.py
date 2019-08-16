# -*- coding: cp936 -*-
'''
    Jenkins端，解析config文件
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
from xml.dom.minidom import parse
import xml.dom.minidom

def parse(CONF):
    DOMTree = xml.dom.minidom.parse(CONF)
    collection = DOMTree.documentElement

    # 用户名
    user = collection.getElementsByTagName("user")[0]
    user = user.childNodes[0].data

    # 项目名
    proj = collection.getElementsByTagName("proj")[0]
    proj = proj.childNodes[0].data

    # 程序类型
    type = collection.getElementsByTagName("type")[0]
    type = type.childNodes[0].data

    # 入口路径
    path = collection.getElementsByTagName("path")[0]
    path = path.childNodes[0].data

    # 程序入口
    main = collection.getElementsByTagName("main")[0]
    main = main.childNodes[0].data

    # 邮件地址
    mail = collection.getElementsByTagName("mail")[0]
    mail = mail.childNodes[0].data
    return user, proj, type, path, main, mail
