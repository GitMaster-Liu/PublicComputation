# -*- coding: cp936 -*-
'''
    Jenkins�ˣ�����config�ļ�
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
from xml.dom.minidom import parse
import xml.dom.minidom

def parse(CONF):
    DOMTree = xml.dom.minidom.parse(CONF)
    collection = DOMTree.documentElement

    # �û���
    user = collection.getElementsByTagName("user")[0]
    user = user.childNodes[0].data

    # ��Ŀ��
    proj = collection.getElementsByTagName("proj")[0]
    proj = proj.childNodes[0].data

    # ��������
    type = collection.getElementsByTagName("type")[0]
    type = type.childNodes[0].data

    # ���·��
    path = collection.getElementsByTagName("path")[0]
    path = path.childNodes[0].data

    # �������
    main = collection.getElementsByTagName("main")[0]
    main = main.childNodes[0].data

    # �ʼ���ַ
    mail = collection.getElementsByTagName("mail")[0]
    mail = mail.childNodes[0].data
    return user, proj, type, path, main, mail
