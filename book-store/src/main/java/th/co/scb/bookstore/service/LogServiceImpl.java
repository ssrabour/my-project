package th.co.scb.bookstore.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.scb.bookstore.model.Log;
import th.co.scb.bookstore.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	@Override
	public Log createLog(Log log) {
		log.setCreatedAt(new Date());
		return logRepository.save(log);
	}

	@Override
	public void deleteByLogTypeAndUserId(String logType, Long userId) {
		logRepository.deleteByLogTypeAndUserId(logType, userId);
	}
}
