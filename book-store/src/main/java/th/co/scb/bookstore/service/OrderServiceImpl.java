package th.co.scb.bookstore.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.scb.bookstore.model.OrderDtl;
import th.co.scb.bookstore.model.OrderMst;
import th.co.scb.bookstore.model.User;
import th.co.scb.bookstore.repository.OrderMstRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMstRepository orderMstRepository;
	
	@Override
	public List<OrderMst> findByUserId(Long userId) {
		return orderMstRepository.findByUserId(userId);
	}
	
	@Override
	public void deleteOrderMst(OrderMst orderMst) {
		orderMstRepository.delete(orderMst);
	}
	
	@Override
	@Transactional
	public OrderMst createOrder(OrderMst orderMst, List<OrderDtl> orderDtls, User user) {
		Date createDate = new Date();
		for (OrderDtl orderDtl : orderDtls) {
			orderDtl.setOrderMst(orderMst);
			orderDtl.setCreatedAt(createDate);
			orderDtl.setUpdatedAt(createDate);
		}
		orderMst.setOrderDtls(orderDtls);
		orderMst.setUserId(user.getUserId());
		orderMst.setCreatedAt(createDate);
		orderMst.setUpdatedAt(createDate);
		orderMstRepository.save(orderMst);
		return orderMst;
	}
}
