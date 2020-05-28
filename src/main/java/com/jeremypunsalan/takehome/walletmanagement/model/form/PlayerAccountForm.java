package com.jeremypunsalan.takehome.walletmanagement.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player Account Form.")
public class PlayerAccountForm {

	@ApiModelProperty(value = "Player Account ID.", required = true)
	Integer accountId;
	@ApiModelProperty(value = "Player ID.", required = true)
	Integer playerId;
	@ApiModelProperty(value = "Account Name.", required = true)
	String accountName;
	@ApiModelProperty(value = "Balance.", required = true, example = "0")
	Double balance;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayerAccountForm [accountId=");
		builder.append(accountId);
		builder.append(", playerId=");
		builder.append(playerId);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", balance=");
		builder.append(balance);
		builder.append("]");
		return builder.toString();
	}
	
}
