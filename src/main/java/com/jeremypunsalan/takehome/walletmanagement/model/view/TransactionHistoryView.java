package com.jeremypunsalan.takehome.walletmanagement.model.view;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Transaction History Output View.")
public class TransactionHistoryView {
	
	@ApiModelProperty(value = "Transaction Date.")
	Date transactionDate;
	@ApiModelProperty(value = "Transaction ID.")
	Integer transactionId;
	@ApiModelProperty(value = "Account ID.")
	Integer accountId;
	@ApiModelProperty(value = "Account Name.")
	String accountName;
	@ApiModelProperty(value = "Player ID.")
	Integer playerId;
	@ApiModelProperty(value = "Player Name.")
	String playerName;
	@ApiModelProperty(value = "Transaction Details / Description.")
	String transactionDescription;
	@ApiModelProperty(value = "Credit amount.")
	Double creditAmount;
	@ApiModelProperty(value = "Debit amount.")
	Double debitAmount;
	@ApiModelProperty(value = "Beginning Balance.")
	Double beginningBalance;
	@ApiModelProperty(value = "Ending Balance.")
	Double endingBalance;
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public Double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public Double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public Double getBeginningBalance() {
		return beginningBalance;
	}
	public void setBeginningBalance(Double beginningBalance) {
		this.beginningBalance = beginningBalance;
	}
	public Double getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(Double endingBalance) {
		this.endingBalance = endingBalance;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionHistoryView [transactionDate=");
		builder.append(transactionDate);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", playerId=");
		builder.append(playerId);
		builder.append(", playerName=");
		builder.append(playerName);
		builder.append(", transactionDescription=");
		builder.append(transactionDescription);
		builder.append(", creditAmount=");
		builder.append(creditAmount);
		builder.append(", debitAmount=");
		builder.append(debitAmount);
		builder.append(", beginningBalance=");
		builder.append(beginningBalance);
		builder.append(", endingBalance=");
		builder.append(endingBalance);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
