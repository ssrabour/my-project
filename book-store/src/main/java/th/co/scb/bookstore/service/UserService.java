package th.co.scb.bookstore.service;

import java.util.List;

import th.co.scb.bookstore.model.OrderMst;
import th.co.scb.bookstore.model.User;

public interface UserService {

	User createUser(User user);	
	void deleteUserLogged(User user);
	User findByUsername(String username);
	List<OrderMst> listBookOrders(User user);
	OrderMst createOrderBooks(List<Long> bookIds, User user);
}
