# -*- coding: cp936 -*-
'''
    Flask Web��ˣ���ӦHttp����
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
from flask import Flask,request
from Logsys import Logger
from FTP_Download import FTPSync
import os
import shutil
import Comput_Excute
import File_Move
from Mailsys import send_mail
from Mailsys import send_error_mail
from FTP_Upload import FTPup

# ȫ����־
log_global = Logger('D:\CptFramework\workspace\log.log')
logger = log_global.getLogger()
app = Flask(__name__)

@app.route('/',methods=['GET','POST'])

def comput_project():
    if request.method == 'POST':
        # ��ȡpost����
        logger.info("��ʼ����")
        username = request.form['username']
        project = request.form['project']
        protype = request.form['type']
        mainpath = request.form['path']
        mainfile = request.form['main']
        mail = request.form['mail']
        try:
            logger.info("�û���" + username)
            logger.info("���̣�" + project)
            os.chdir('D:\CptFramework\workspace')
            if os.path.exists (project):
                logger.info("Ŀ¼�Ѵ���")
                shutil.rmtree(project)
                logger.info("���Ŀ¼")
            os.mkdir(project)
            logger.info("����Ŀ¼")
            logger.info("���㿪ʼ")

            # ����ftp����ʵ��
            fdowmObj = FTPSync(username, project, logger)
            # ����ָ����Ŀ�ļ�
            fdowmObj.run()
            # ִ������
            path = Comput_Excute.excute(project,protype,mainpath,mainfile,logger)
            # ѹ���ļ�
            rarpath = File_Move.file_move(path, project)
            # FTP�ϴ�
            file_remote = project + '.rar'
            file_local = rarpath + '\\' + file_remote
            ftp_upload = FTPup()
            ftp_upload.file_upload(file_remote, file_local,username,project)
            # ɾ����ʱĿ¼
            os.chdir('D:\CptFramework\workspace')

            if os.path.exists(project):
                shutil.rmtree(project)
            if os.path.exists(project+'.rar'):
                os.remove(project+'.rar')

            logger.info("��ʱĿ¼��ɾ��")
            # �����ʼ�
            try:
                send_mail(mail)
                logger.info("�ʼ��ѷ���")
            except:
                pass
        except:
            logger.info("ϵͳ����")
            send_error_mail(mail)
            logger.info("�ʼ��ѷ���")
    return "0"

if __name__ == '__main__':
    app.run(
        host='127.0.0.1',
        port=9010,
        debug=True
    )
