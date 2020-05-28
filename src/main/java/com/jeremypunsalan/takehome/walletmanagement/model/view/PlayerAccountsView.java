package com.jeremypunsalan.takehome.walletmanagement.model.view;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player Accounts Output View.")
public class PlayerAccountsView {
	
	@ApiModelProperty(value = "Account ID.")
	private Integer accountId;
	@ApiModelProperty(value = "Account Name.")
	private String accountName;
	@ApiModelProperty(value = "Account Balance.")
	private Double balance;
	@ApiModelProperty(value = "Date account created.")
	private Date dateCreated;
	@ApiModelProperty(value = "Date account updated.")
	private Date dateUpdated;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayerAccountsView [accountId=");
		builder.append(accountId);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
