package com.jeremypunsalan.takehome.walletmanagement.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player Information Form.")
public class PlayerForm {
	
	@ApiModelProperty(value = "Player ID.", required = true)
	Integer playerId;
	@ApiModelProperty(value = "Player Name", required = true)
	String playerName;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayerForm [playerId=");
		builder.append(playerId);
		builder.append(", playerName=");
		builder.append(playerName);
		builder.append("]");
		return builder.toString();
	}
	

}
