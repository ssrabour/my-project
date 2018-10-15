package th.co.scb.bookstore.payload;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Long> orders;

	public List<Long> getOrders() {
		return orders;
	}

	public void setOrders(List<Long> orders) {
		this.orders = orders;
	}
}
