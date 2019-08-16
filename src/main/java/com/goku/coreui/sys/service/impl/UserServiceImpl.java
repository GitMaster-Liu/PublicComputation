package com.goku.coreui.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.coreui.sys.mapper.UserMapper;
import com.goku.coreui.sys.model.User;
import com.goku.coreui.sys.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	@Override
	public User getUserByMemberId(String memberId) {
		return userMapper.getUserByMemberId(memberId);
	}

}
