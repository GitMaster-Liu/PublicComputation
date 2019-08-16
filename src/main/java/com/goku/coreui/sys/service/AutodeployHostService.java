package com.goku.coreui.sys.service;

import java.util.List;

import com.goku.coreui.sys.model.AutodeployHost;

public interface AutodeployHostService {
	List<AutodeployHost> getDeployHostByHostId(String hostId);
}
