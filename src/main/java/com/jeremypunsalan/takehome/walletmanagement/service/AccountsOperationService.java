package com.jeremypunsalan.takehome.walletmanagement.service;

import java.util.List;
import java.util.Map;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountKeyForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionForm;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;

public interface AccountsOperationService {

	Double getAccountBalance(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException;

	List<PlayerAccountsView> getAccountBalances(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException;
	
	TransactionHistoryView transactAccount(TransactionForm transactionForm) throws ValidationException, ResourceNotFoundException, TransactionException;

	List<TransactionHistoryView> getTransactionHistoryPerAccount(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException;

	Map<Integer, List<TransactionHistoryView>> getTransactionHistoryPerPlayer(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException;

	

}
