package com.jeremypunsalan.takehome.walletmanagement.model.view;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player Info Output View.")
public class PlayerView {
	
	@ApiModelProperty(value = "Player ID.")
	Integer playerId;
	@ApiModelProperty(value = "Player Name.")
	String playerName;
	@ApiModelProperty(value = "List of Accounts View.")
	List<PlayerAccountsView> accounts;
	@ApiModelProperty(value = "Date player account created.")
	Date dateCreated;
	@ApiModelProperty(value = "Date player account updated.")
	Date dateUpdated;
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
	public List<PlayerAccountsView> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<PlayerAccountsView> accounts) {
		this.accounts = accounts;
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
		builder.append("PlayerView [playerId=");
		builder.append(playerId);
		builder.append(", playerName=");
		builder.append(playerName);
		builder.append(", accounts=");
		builder.append(accounts);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}
	
	

}
