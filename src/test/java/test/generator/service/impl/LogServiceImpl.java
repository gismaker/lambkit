package test.generator.service.impl;

import test.generator.service.LogService;
import test.generator.model.Log;


import com.lambkit.common.service.BaseModelServiceImpl;

public class LogServiceImpl extends BaseModelServiceImpl<Log> implements LogService {

	protected final Log DAO = new Log();
	
	public Log dao() {
		return DAO;
	}
}