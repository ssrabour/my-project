package th.co.scb.bookstore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Log implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logId;
	
	private String logType;
	
	@NotNull
	private String username;
	
	@NotNull
	private Long userId;

	private Long orderMstId;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	public Log() {}
	
	public Log(String logType, String username, Long userId) {
		this.logType = logType;
		this.username = username;
		this.userId = userId;
	}
	
	public Log(String logType, String username, Long userId, Long orderMstId) {
		this.logType = logType;
		this.username = username;
		this.userId = userId;
		this.orderMstId = orderMstId;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderMstId() {
		return orderMstId;
	}

	public void setOrderMstId(Long orderMstId) {
		this.orderMstId = orderMstId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
