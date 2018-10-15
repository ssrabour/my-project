package th.co.scb.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import th.co.scb.bookstore.exception.ApplicationErrorException;
import th.co.scb.bookstore.exception.InputFieldEmptyException;
import th.co.scb.bookstore.exception.InputFieldNotValidException;
import th.co.scb.bookstore.exception.ResourceNotFoundException;
import th.co.scb.bookstore.payload.LoginRequest;
import th.co.scb.bookstore.service.LoginService;
import th.co.scb.bookstore.util.DataUtil;

@RestController
@RequestMapping("/api")
@Api(value = "login", tags = "Login API")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "User login authentication")
	public void doLogin(@RequestBody LoginRequest request) {
		if (DataUtil.isEmpty(request.getUsername())) {
			throw new InputFieldEmptyException("username", "username is require field");
		}
		if (DataUtil.isEmpty(request.getPassword())) {
			throw new InputFieldEmptyException("password", "password is require field");
		}
		try {
			loginService.doLogin(request.getUsername(), request.getPassword());
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (BadCredentialsException e) {
			throw new InputFieldNotValidException("username and password");
		} catch (Exception e) {
			throw new ApplicationErrorException("login");
		}
	}
}
