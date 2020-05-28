package com.jeremypunsalan.takehome.walletmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import com.jeremypunsalan.takehome.walletmanagement.dao.AccountsDao;
import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.helper.FormToEntityConverter;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountKeyForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionForm;
import com.jeremypunsalan.takehome.walletmanagement.model.validator.InputValidator;
import com.jeremypunsalan.takehome.walletmanagement.model.validator.PlayerAccountKeyFormValidator;
import com.jeremypunsalan.takehome.walletmanagement.model.validator.TransactionFormValidator;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;

@Component("AccountsOperationService")
public class AccountsOperationServiceImpl implements AccountsOperationService {
	
	private AccountsDao accountsDao;
	
	@Autowired
	public void setAccountsDao(AccountsDao dao) {
		this.accountsDao = dao;
	}

	@Override
	public Double getAccountBalance(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException {
		
		//sanitize input
		InputValidator<PlayerAccountKeyForm> validator = PlayerAccountKeyFormValidator::validateFormRequiredAll; 
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) {
			throw new ValidationException(PlayerAccountKeyFormValidator.getErrorMessages(errorList));
		}
		//get output from dao
		Double balance = accountsDao.getBalance(FormToEntityConverter.convertFormToPlayerAccount(form));
		//return value
		return balance;
	}
	
	@Override
	public List<PlayerAccountsView> getAccountBalances(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException {
		
		//sanitize input
		InputValidator<PlayerAccountKeyForm> validator = PlayerAccountKeyFormValidator::validateFormRequiredPlayerId;
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) {
			throw new ValidationException(PlayerAccountKeyFormValidator.getErrorMessages(errorList));
		}
		List<PlayerAccountsView> list = accountsDao.getBalances(FormToEntityConverter.convertFormToPlayerAccount(form));
		//get output from dao
		
		//return list
		
		return list;
	}

	@Override
	public TransactionHistoryView transactAccount(TransactionForm transactionForm) throws ValidationException, ResourceNotFoundException, TransactionException {
		
		//sanitize input
		InputValidator<TransactionForm> validator = TransactionFormValidator::validateForm;
		List<String> errorList = validator.validate(transactionForm);
		if(errorList.size() > 0) {
			throw new ValidationException(TransactionFormValidator.getErrorMessages(errorList));
		}
		
		TransactionHistoryView view = null;
		try {
			view = accountsDao.transact(FormToEntityConverter.convertFormToTransactionHistory(transactionForm)); 
		} catch(ObjectOptimisticLockingFailureException ex) {
			throw new TransactionException(Constants.ERROR_MSG_TRANSACTION_UPDATED_BY_ANOTHER_TRANSACTION + ex.getMessage());
		} catch(TransactionException | ValidationException | ResourceNotFoundException ex ) {
			throw ex;
		}
		
		return view;
	}

	@Override
	public List<TransactionHistoryView> getTransactionHistoryPerAccount(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException {
		
		InputValidator<PlayerAccountKeyForm> validator = PlayerAccountKeyFormValidator::validateFormRequiredAll; 
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) 
			throw new ValidationException(PlayerAccountKeyFormValidator.getErrorMessages(errorList));

		return accountsDao.getTransactionHistory(FormToEntityConverter.convertFormToPlayerAccount(form));
	}

	@Override
	public Map<Integer, List<TransactionHistoryView>> getTransactionHistoryPerPlayer(PlayerAccountKeyForm form) throws ValidationException, ResourceNotFoundException {
		
		InputValidator<PlayerAccountKeyForm> validator = PlayerAccountKeyFormValidator::validateFormRequiredPlayerId;
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) 
			throw new ValidationException(PlayerAccountKeyFormValidator.getErrorMessages(errorList));
		
		return accountsDao.getTransactionHistoryPerPlayer(FormToEntityConverter.convertFormToPlayerAccount(form));
	}



}
