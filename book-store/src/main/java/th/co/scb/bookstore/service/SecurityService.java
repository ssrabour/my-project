package th.co.scb.bookstore.service;

public interface SecurityService {

	String getLoggedInUsername();
	void authenUserLogin(String username, String password);
}
