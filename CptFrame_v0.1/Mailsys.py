# -*- coding: cp936 -*-
'''
    Flask Web后端，邮件系统
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/10
'''
import smtplib
from email.mime.text import MIMEText
from email.header import Header

def send_mail(mail):
    email_host = 'smtp.sina.com'
    email_user = 'cptframework@sina.com'
    email_pwd = 'cimsnihao'

    sender = email_user
    maillist = mail

    mail_msg = '''
    <p>任务已完成,请登录并查看运算结果</p>
    <a href="ftp://192.168.3.187">ftp://192.168.3.187</a>
    '''
    message = MIMEText(mail_msg,'html','utf-8')
    message['From'] = email_user
    message['To'] = maillist

    subject = '系统通知'
    message['Subject'] = Header(subject, 'utf-8')


    smtpObj = smtplib.SMTP(email_host)
    smtpObj.login(email_user,email_pwd)
    smtpObj.sendmail(sender,maillist,message.as_string())

def send_error_mail(mail):
    email_host = 'smtp.sina.com'
    email_user = 'cptframework@sina.com'
    email_pwd = 'cimsnihao'

    sender = email_user
    maillist = mail

    mail_msg = '''
    <p>编译发生错误，请检查输入参数后重新编译</p>
    '''
    message = MIMEText(mail_msg,'html','utf-8')
    message['From'] = email_user
    message['To'] = maillist

    subject = '系统通知'
    message['Subject'] = Header(subject, 'utf-8')


    smtpObj = smtplib.SMTP(email_host)
    smtpObj.login(email_user,email_pwd)
    smtpObj.sendmail(sender,maillist,message.as_string())
