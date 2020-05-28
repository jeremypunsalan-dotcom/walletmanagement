package com.jeremypunsalan.takehome.walletmanagement.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="transaction_history")
public class TransactionHistory {
	
	@Id
	@Column(name="transactionid")
	private Integer transactionId;
	
	@Column(name="accountid")
	private Integer accountId;
	
	@Column(name="playerid")
	private Integer playerId;
	
	@Column(name="transaction_desc")
	private String transactionDescription;
	
	@Column(name="transaction_amount")
	private Double amount;
	
	@Column(name="before_balance")
	private Double beforeBalance;
	
	@Column(name="after_balance")
	private Double afterBalance;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdate", updatable=false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedate")
	private Date updateDate;
	
	@Version
	@Column(name = "version")
	private Integer version;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(Double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public Double getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(Double afterBalance) {
		this.afterBalance = afterBalance;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
  	
  	

}
