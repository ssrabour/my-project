package th.co.scb.bookstore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class OrderMst implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderMstId;
	
	@NotNull
	private Long userId;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	private BigDecimal totalPrice;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderMst")
	private List<OrderDtl> orderDtls;

	public Long getOrderMstId() {
		return orderMstId;
	}

	public void setOrderMstId(Long orderMstId) {
		this.orderMstId = orderMstId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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

	public List<OrderDtl> getOrderDtls() {
		return orderDtls;
	}

	public void setOrderDtls(List<OrderDtl> orderDtls) {
		this.orderDtls = orderDtls;
	}
}
