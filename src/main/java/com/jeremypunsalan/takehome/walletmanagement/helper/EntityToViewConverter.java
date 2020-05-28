package com.jeremypunsalan.takehome.walletmanagement.helper;

import java.util.ArrayList;
import java.util.List;

import com.jeremypunsalan.takehome.walletmanagement.model.entity.Player;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.TransactionHistory;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;

public class EntityToViewConverter {

	public static PlayerAccountsView convertEntityToPlayerAccountsView(PlayerAccount acct) {
		PlayerAccountsView view = new PlayerAccountsView();
		view.setAccountId(acct.getAccountId());
		view.setAccountName(acct.getAccountName());
		view.setBalance(acct.getBalance());
		view.setDateCreated(acct.getCreateDate());
		view.setDateUpdated(acct.getUpdateDate());
		return view;
	}

	public static TransactionHistoryView convertEntityToTransactionHistoryView(TransactionHistory history,
			PlayerAccount acct, Player player) {
		TransactionHistoryView view = new TransactionHistoryView();
		view.setAccountId(history.getAccountId());
		view.setAccountName(acct.getAccountName());
		if (Helper.isDoubleNegative(history.getAmount())) {
			view.setDebitAmount(Math.abs(history.getAmount()));
		} else {
			view.setCreditAmount(history.getAmount());
		}
		view.setBeginningBalance(history.getBeforeBalance());
		view.setEndingBalance(history.getAfterBalance());
		view.setPlayerId(player.getPlayerId());
		view.setPlayerName(player.getPlayerName());
		view.setTransactionDate(history.getCreateDate());
		view.setTransactionDescription(history.getTransactionDescription());
		view.setTransactionId(history.getTransactionId());

		return view;
	}

	public static PlayerView convertEntityToPlayerView(Player savedPlayer) {
		PlayerView view = new PlayerView();
		view.setPlayerId(savedPlayer.getPlayerId());
		view.setPlayerName(savedPlayer.getPlayerName());
		view.setDateCreated(savedPlayer.getCreateDate());
		view.setDateUpdated(savedPlayer.getUpdateDate());
		return view;
	}

	public static PlayerView convertEntityToPlayerView(Player playerEntity, List<PlayerAccount> accounts) {
		List<PlayerAccountsView> accountsView = new ArrayList<PlayerAccountsView>();

		for (PlayerAccount account : accounts) {
			accountsView.add(EntityToViewConverter.convertEntityToPlayerAccountsView(account));
		}
		
		PlayerView view = convertEntityToPlayerView(playerEntity);
		view.setAccounts(accountsView);
		
		return view;
	}

}
