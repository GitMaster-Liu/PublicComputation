# -*- coding: cp936 -*-
'''
    Flask Web后端，批处理执行计算
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
            Logger.info("python工程")
            os.system('python '+MAIN+'>console.txt')
            Logger.info("运行完成")
        elif TYPE == 'matlab':
            Logger.info("matlab工程")
            os.system('echo ^quit>>'+MAIN+'.m')
            os.system('matlab -sd ' + 'D:\CptFramework\workspace\\'+ PROJ + ' -nosplash -nodesktop -r '+MAIN)
            os.system("sed -i '$d' " + MAIN + ".m")
            Logger.info("运行完成")
    except:
        pass

    file_path = os.getcwd()

    # 返回执行结果的路径
    return file_path
