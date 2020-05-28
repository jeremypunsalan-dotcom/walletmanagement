package com.jeremypunsalan.takehome.walletmanagement.helper;

import java.util.Optional;

import com.jeremypunsalan.takehome.walletmanagement.model.entity.Player;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.TransactionHistory;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountKeyForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionType;

public class FormToEntityConverter {

	public static PlayerAccount convertFormToPlayerAccount(PlayerAccountKeyForm form) {

		PlayerAccount account = new PlayerAccount();
		account.setAccountId(form.getAccountId());
		account.setPlayerId(form.getPlayerId());
		return account;

	}

	public static TransactionHistory convertFormToTransactionHistory(TransactionForm transactionForm) {
		TransactionHistory history = new TransactionHistory();
		history.setAccountId(transactionForm.getAccountId());
		history.setPlayerId(transactionForm.getPlayerId());
		history.setTransactionId(transactionForm.getTransactionId());
		history.setTransactionDescription(transactionForm.getTransactionDescription());
		if (!Optional.ofNullable(history.getTransactionDescription()).isPresent()) {
			String defaultDesc = TransactionType.C.toString().equalsIgnoreCase(
					transactionForm.getTransactionType()) ? Constants.TRXN_DESC_DEFAULT_CREDIT
							: Constants.TXN_DESC_DEFAULT_DEBIT;
			history.setTransactionDescription(defaultDesc);
		}
		if (TransactionType.C.toString().equalsIgnoreCase(transactionForm.getTransactionType().toString())) {
			history.setAmount(transactionForm.getTransactionAmount());
		} else {
			history.setAmount(Constants.D_ZERO - transactionForm.getTransactionAmount());
		}

		return history;
	}

	public static Player convertFormToPlayer(PlayerForm player) {

		Player playerEntity = new Player();
		playerEntity.setPlayerId(player.getPlayerId());
		playerEntity.setPlayerName(player.getPlayerName());

		return playerEntity;
	}

	public static PlayerAccount convertFormToPlayerAccount(PlayerAccountForm form) {

		PlayerAccount account = new PlayerAccount();
		account.setAccountId(form.getAccountId());
		account.setAccountName(form.getAccountName());
		account.setBalance(form.getBalance());
		account.setPlayerId(form.getPlayerId());

		return account;
	}

}
