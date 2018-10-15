package th.co.scb.bookstore.service;

import th.co.scb.bookstore.model.Log;

public interface LogService {
	
	String LOG_TYPE_ORDER = "ORDER";
	String LOG_TYPE_LOGIN = "LOGIN";
	
	Log createLog(Log log);
	void deleteByLogTypeAndUserId(String logType, Long userId);
}
