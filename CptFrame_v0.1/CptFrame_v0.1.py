# -*- coding: cp936 -*-
'''
    Flask Web后端，响应Http请求
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

# 全局日志
log_global = Logger('D:\CptFramework\workspace\log.log')
logger = log_global.getLogger()
app = Flask(__name__)

@app.route('/',methods=['GET','POST'])

def comput_project():
    if request.method == 'POST':
        # 获取post参数
        logger.info("开始任务")
        username = request.form['username']
        project = request.form['project']
        protype = request.form['type']
        mainpath = request.form['path']
        mainfile = request.form['main']
        mail = request.form['mail']
        try:
            logger.info("用户：" + username)
            logger.info("工程：" + project)
            os.chdir('D:\CptFramework\workspace')
            if os.path.exists (project):
                logger.info("目录已存在")
                shutil.rmtree(project)
                logger.info("清除目录")
            os.mkdir(project)
            logger.info("创建目录")
            logger.info("计算开始")

            # 创建ftp下载实例
            fdowmObj = FTPSync(username, project, logger)
            # 下载指定项目文件
            fdowmObj.run()
            # 执行运算
            path = Comput_Excute.excute(project,protype,mainpath,mainfile,logger)
            # 压缩文件
            rarpath = File_Move.file_move(path, project)
            # FTP上传
            file_remote = project + '.rar'
            file_local = rarpath + '\\' + file_remote
            ftp_upload = FTPup()
            ftp_upload.file_upload(file_remote, file_local,username,project)
            # 删除临时目录
            os.chdir('D:\CptFramework\workspace')

            if os.path.exists(project):
                shutil.rmtree(project)
            if os.path.exists(project+'.rar'):
                os.remove(project+'.rar')

            logger.info("临时目录已删除")
            # 发送邮件
            try:
                send_mail(mail)
                logger.info("邮件已发送")
            except:
                pass
        except:
            logger.info("系统错误")
            send_error_mail(mail)
            logger.info("邮件已发送")
    return "0"

if __name__ == '__main__':
    app.run(
        host='127.0.0.1',
        port=9010,
        debug=True
    )
