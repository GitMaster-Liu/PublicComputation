# -*- coding: cp936 -*-
'''
    Flask Web��ˣ�������ִ�м���
    author: Terrence332
    email: a11015zxz@163.com
    date: 2018/07/02
'''
import os

def excute(PROJ,TYPE,PATH,MAIN,Logger):
    try:
        os.chdir('D:\CptFramework\workspace\\'+PROJ)
        os.chdir(PATH)
        if TYPE == 'python':
            Logger.info("python����")
            os.system('python '+MAIN+'>console.txt')
            Logger.info("�������")
        elif TYPE == 'matlab':
            Logger.info("matlab����")
            os.system('echo ^quit>>'+MAIN+'.m')
            os.system('matlab -sd ' + 'D:\CptFramework\workspace\\'+ PROJ + ' -nosplash -nodesktop -r '+MAIN)
            os.system("sed -i '$d' " + MAIN + ".m")
            Logger.info("�������")
    except:
        pass

    file_path = os.getcwd()

    # ����ִ�н����·��
    return file_path
