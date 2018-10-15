package th.co.scb.bookstore.payload;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import th.co.scb.bookstore.model.OrderDtl;
import th.co.scb.bookstore.model.OrderMst;
import th.co.scb.bookstore.model.User;
import th.co.scb.bookstore.util.DataUtil;

public class UserResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	private String date_of_birth;
	private List<OrderResponse> orders = new ArrayList<OrderResponse>();
	
	public UserResponse(User user, List<OrderMst> orderMsts) {
		name = user.getName();
		surname = user.getSurname();
		date_of_birth = DataUtil.toString(user.getDateOfBirth(), DataUtil.DATE_FORMAT_DDMMYYYY);
		for (OrderMst orderMst : orderMsts) {
			List<Long> books = new ArrayList<Long>();
			for (OrderDtl orderDtl : orderMst.getOrderDtls()) {
				books.add(orderDtl.getBookId());
			}
			Collections.sort(books);
			OrderResponse order = new OrderResponse(orderMst.getOrderMstId(), orderMst.getOrderDate(), 
					orderMst.getTotalPrice(), books);
			orders.add(order);
		}
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public List<OrderResponse> getOrders() {
		return orders;
	}
}
