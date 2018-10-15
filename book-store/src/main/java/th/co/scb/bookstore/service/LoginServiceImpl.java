package th.co.scb.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.scb.bookstore.exception.ResourceNotFoundException;
import th.co.scb.bookstore.model.Log;
import th.co.scb.bookstore.model.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private SecurityService securityService;
	
	@Override
	public void doLogin(String username, String password) {
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("User", "username", username);
		}
		securityService.authenUserLogin(username, password);
		Log log = new Log(LogService.LOG_TYPE_LOGIN, username, user.getUserId());
		logService.createLog(log);
	}
}
