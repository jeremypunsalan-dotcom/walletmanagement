package com.jeremypunsalan.takehome.walletmanagement.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player Account Keys Form.")
public class PlayerAccountKeyForm {

	@ApiModelProperty(value = "Player ID.", required = true)
	Integer playerId;
	@ApiModelProperty(value = "Player ID.", required = true)
	Integer accountId;
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	
	
	
}
