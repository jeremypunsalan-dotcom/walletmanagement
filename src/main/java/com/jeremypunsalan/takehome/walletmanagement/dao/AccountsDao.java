package com.jeremypunsalan.takehome.walletmanagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.TransactionHistory;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;

public interface AccountsDao {

	Double getBalance(PlayerAccount playerAccount) throws ResourceNotFoundException;

	List<PlayerAccountsView> getBalances(PlayerAccount playerAccount) throws ResourceNotFoundException;
	
	TransactionHistoryView transact(TransactionHistory transactionHistory) throws ValidationException, ResourceNotFoundException, TransactionException, ObjectOptimisticLockingFailureException;

	List<TransactionHistoryView> getTransactionHistory(PlayerAccount playerAccount) throws ResourceNotFoundException;

	Map<Integer, List<TransactionHistoryView>> getTransactionHistoryPerPlayer(PlayerAccount playerAccount) throws ResourceNotFoundException;

}
