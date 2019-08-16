# -*- coding: cp936 -*-
def get_target_value(key, dic, tmp_list):

    if not isinstance(dic, dict) :
        return '不是dict格式'

    if key in dic.keys():
        tmp_list.append(dic[key])
    else:
        for value in dic.values():
            if isinstance(value, dict):
                get_target_value(key, value, tmp_list)
            elif isinstance(value, (list, tuple)):
                _get_value(key, value, tmp_list)
    return tmp_list

def _get_value(key, val, tmp_list):
    for val_ in val:
        if isinstance(val_, dict):
            get_target_value(key, val_, tmp_list)
        elif isinstance(val_, (list, tuple)):
            _get_value(key, val_, tmp_list)