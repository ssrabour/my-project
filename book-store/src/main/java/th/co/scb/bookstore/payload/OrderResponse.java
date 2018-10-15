package th.co.scb.bookstore.payload;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import th.co.scb.bookstore.util.DataUtil;

public class OrderResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long order_no;
	private String order_date;
	private BigDecimal price;
	private List<Long> books = new ArrayList<Long>();

	public OrderResponse(Long orderNo, Date orderDate, BigDecimal price, List<Long> books) {
		this.order_no = orderNo;
		this.order_date = DataUtil.toString(orderDate, DataUtil.DATE_FORMAT_DDMMYYYY);
		this.price = price;
		this.books = books;
	}

	public Long getOrder_no() {
		return order_no;
	}

	public String getOrder_date() {
		return order_date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public List<Long> getBooks() {
		return books;
	}
}
