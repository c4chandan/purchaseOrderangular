  
package com.project.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.StatusDao;
import com.project.model.Status;
import com.project.service.StatusService;


@Service
@Transactional
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	StatusDao statusDao;
	

	@Override
	public Status viewStatus(int statusId) {
		// TODO Auto-generated method stub
		return viewStatus(statusId);
	}

	@Override
	public boolean addStatus(Status statusObj) {
		// TODO Auto-generated method stub
		return addStatus(statusObj);
	}

	@Override
	public boolean updateStatus(String statusName) {
		
		return updateStatus(statusName);
	}

}