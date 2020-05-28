package com.jeremypunsalan.takehome.walletmanagement.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Transaction Form.")
public class TransactionForm {
	
	@ApiModelProperty(value = "Transaction ID.", required = true)
	Integer transactionId;
	@ApiModelProperty(value = "Account ID.", required = true)
	Integer accountId;
	@ApiModelProperty(value = "Player ID.", required = true)
	Integer playerId;
	@ApiModelProperty(value = "Transaction Description", required = false)
	String transactionDescription;
	@ApiModelProperty(value = "Transaction amount", required = true)
	Double transactionAmount;
	@ApiModelProperty(value = "Transaction type C for credit, D for Debit.", required = true, allowableValues = "C,D")
	String transactionType;
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
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	

	
	
	
	
	
	
	
	

}
