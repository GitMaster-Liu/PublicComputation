# -*- coding: cp936 -*-
import queue
import json
import threading
import time
import paramiko
import json_parse
import os
import python_post
import shutil

queue = queue.Queue()
i = 1
class GerritStream(threading.Thread):
    """Listens for gerrit event and put them in a queue"""

    def run(self):
        while 1:
            client = paramiko.SSHClient()
            key = paramiko.AutoAddPolicy()
            client.set_missing_host_key_policy(key)

            try:
                client.connect('192.168.7.6', 29418, 'admin', 'admin', timeout=5)

                client.get_transport().set_keepalive(60)
                _, stdout, _ = client.exec_command('gerrit stream-events')
                for line in stdout:
                    queue.put(json.loads(line))
                    print (line)

                    with open('D:\GerritJson' + '.json', 'a') as outfile:
                        json.dump(line, outfile, ensure_ascii=False)
                        outfile.write('\n')



            except:
                exit()
            finally:
                client.close()
            time.sleep(5)

gerrit = GerritStream()
gerrit.daemon = True
gerrit.start()

while 1:
    event = queue.get()
    print(event)



    list1 = json_parse.get_target_value('submitter', event,[])
    if not list1 == []:
        print ('list1:', list1)
        dict1 = {i: v for i, v in enumerate(list1)}
        print ('dict1:', dict1)
        str2 = json_parse.get_target_value('username', dict1,[])
        author_name = "".join(str2)
        print ('author_name:', author_name)
        list3 = json_parse.get_target_value('project', event,[])
        print ('list3:', list3)
        project_name = "".join(list3)
        print ('project_name:',project_name)
        os.chdir('D:\\comput\\ftp_file')
        if not(os.path.exists ('Results')):
            os.mkdir('Results')
        if not(os.path.exists (author_name)):
            os.mkdir(author_name)
        os.chdir(author_name)
        if os.path.exists (project_name):
            shutil.rmtree(project_name)
        str = 'git clone ssh://' + author_name + '@192.168.7.6:29418/' + project_name
        os.system(str)

        os.chdir('D:\\comput\\ftp_file\\'+author_name+'\\'+project_name)

        os.system('rmdir /s/q .git')

        python_post.pythonpost(author_name,project_name)



