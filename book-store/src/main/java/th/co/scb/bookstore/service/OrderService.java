package th.co.scb.bookstore.service;

import java.util.List;

import th.co.scb.bookstore.model.OrderDtl;
import th.co.scb.bookstore.model.OrderMst;
import th.co.scb.bookstore.model.User;

public interface OrderService {
	
	List<OrderMst> findByUserId(Long userId);
	void deleteOrderMst(OrderMst orderMst);
	OrderMst createOrder(OrderMst orderMst, List<OrderDtl> orderDtls, User user);
}
