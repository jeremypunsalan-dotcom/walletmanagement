package com.jeremypunsalan.takehome.walletmanagement.dao;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.Player;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;

public interface AccountsCRUDDao {

	PlayerView createPlayer(Player player);

	PlayerView getPlayerInfo(Player player) throws ResourceNotFoundException;

	PlayerView updatePlayerInfo(Player player) throws ResourceNotFoundException;

	PlayerAccountsView createPlayerAccount(PlayerAccount playerAccount) throws ResourceNotFoundException;

	PlayerAccountsView getPlayerAccount(PlayerAccount playerAccount) throws ResourceNotFoundException;

	PlayerAccountsView updatePlayerAccount(PlayerAccount playerAccount) throws ResourceNotFoundException;

}
