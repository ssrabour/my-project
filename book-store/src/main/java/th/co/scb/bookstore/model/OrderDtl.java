package th.co.scb.bookstore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class OrderDtl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderDtlId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_mst_id", nullable = false)
    private OrderMst orderMst;
	
	@NotNull
	private Long bookId;
	
	private Long qty;

	private BigDecimal unitPrice;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	public OrderDtl() {}
	
	public OrderDtl(Long bookId, Long qty, BigDecimal unitPrice) {
		this.bookId = bookId;
		this.qty = qty;
		this.unitPrice = unitPrice;
	}

	public Long getOrderDtlId() {
		return orderDtlId;
	}

	public void setOrderDtlId(Long orderDtlId) {
		this.orderDtlId = orderDtlId;
	}

	public OrderMst getOrderMst() {
		return orderMst;
	}

	public void setOrderMst(OrderMst orderMst) {
		this.orderMst = orderMst;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
