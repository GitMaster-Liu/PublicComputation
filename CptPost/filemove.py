# -*- coding: cp936 -*-

import os


os.chdir(r"D:\Goku.Framework.CoreUI-master\src\main\webapp\upload")
os.system(r"move *  D:\comput\ftp_file")
for dirpath, dirnames, filenames in os.walk(r"D:\comput\ftp_file"):
    for filename in filenames:
        (filepath, tempfilename) = os.path.split(filename)
        (shotname, extension) = os.path.splitext(tempfilename)
        if extension == ".rar":
            os.system('md D:\comput\\ftp_file\\' + shotname)
            os.system('rar e D:\comput\\ftp_file\\' + filename + ' D:\comput\\ftp_file\\'+shotname)


