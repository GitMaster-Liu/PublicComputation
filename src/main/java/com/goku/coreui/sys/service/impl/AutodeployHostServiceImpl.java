package com.goku.coreui.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.coreui.sys.mapper.AutodeployHostMapper;
import com.goku.coreui.sys.model.AutodeployHost;
import com.goku.coreui.sys.service.AutodeployHostService;



@Service
public class AutodeployHostServiceImpl implements AutodeployHostService{
	@Autowired
	AutodeployHostMapper AutodeployHostMapper;
	@Override
	public List<AutodeployHost> getDeployHostByHostId(String hostId) {
		return AutodeployHostMapper.getDeployHostByHostId(hostId);
	}
}