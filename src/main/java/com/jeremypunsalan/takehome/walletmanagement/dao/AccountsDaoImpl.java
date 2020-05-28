package com.jeremypunsalan.takehome.walletmanagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.helper.EntityToViewConverter;
import com.jeremypunsalan.takehome.walletmanagement.helper.Helper;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.Player;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.TransactionHistory;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;
import com.jeremypunsalan.takehome.walletmanagement.repository.PlayerAccountRepository;
import com.jeremypunsalan.takehome.walletmanagement.repository.PlayerRepository;
import com.jeremypunsalan.takehome.walletmanagement.repository.TransactionHistoryRepository;

@Component("AccountsDao")
public class AccountsDaoImpl implements AccountsDao {

	private PlayerAccountRepository playerAccountRepository;

	@Autowired
	public void setPlayerAccountRepository(PlayerAccountRepository repo) {
		this.playerAccountRepository = repo;
	}

	private TransactionHistoryRepository transactionHistoryRepository;

	@Autowired
	public void setTransactionHistoryRepository(TransactionHistoryRepository repo) {
		this.transactionHistoryRepository = repo;
	}
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public void setPlayerRepository(PlayerRepository repo) {
		this.playerRepository = repo;
	}

	@Override
	public Double getBalance(PlayerAccount playerAccount) throws ResourceNotFoundException {

		playerAccount = playerAccountRepository.findById(playerAccount.getAccountId())
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_MSG_PLAYER_ACCOUNT_NOT_FOUND));

		return playerAccount.getBalance();

	}

	@Override
	public List<PlayerAccountsView> getBalances(PlayerAccount playerAccount) throws ResourceNotFoundException {

		List<PlayerAccount> list = playerAccountRepository.findByPlayerId(playerAccount.getPlayerId());
		if (Optional.ofNullable(list).isPresent() && list.size() >= 1) {
			List<PlayerAccountsView> outputList = new ArrayList<PlayerAccountsView>();
			for (PlayerAccount acct : list) {
				PlayerAccountsView view = EntityToViewConverter.convertEntityToPlayerAccountsView(acct);
				outputList.add(view);
			}
			return outputList;
		} else {
			throw new ResourceNotFoundException(Constants.ERROR_MSG_PLAYER_ACCOUNT_NOT_FOUND);
		}

	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public TransactionHistoryView transact(TransactionHistory transactionHistory) throws ValidationException, ResourceNotFoundException, TransactionException, ObjectOptimisticLockingFailureException {

		if (transactionHistoryRepository.findById(transactionHistory.getTransactionId()).isPresent()) {
			throw new ValidationException(Constants.ERROR_MSG_TRANSACTION_ID_NOT_UNIQUE);
		}
		
		PlayerAccount account = playerAccountRepository.findByAccountId(transactionHistory.getAccountId())
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_MSG_PLAYER_ACCOUNT_NOT_FOUND));
		
		transactionHistory.setBeforeBalance(account.getBalance());
		transactionHistory.setAfterBalance(account.getBalance() + transactionHistory.getAmount());
		
		if(Helper.isDoubleNegative(transactionHistory.getAfterBalance())) throw new ValidationException(Constants.ERROR_MSG_TRANSACTION_INSUFFICIENT_FUNDS);
		
		TransactionHistory savedHistory = transactionHistoryRepository.saveAndFlush(transactionHistory);
		account.setBalance(transactionHistory.getAfterBalance());
		PlayerAccount savedAccount = playerAccountRepository.saveAndFlush(account);
		if(!savedAccount.getVersion().equals(account.getVersion())) 
			throw new TransactionException(Constants.ERROR_MSG_TRANSACTION_UPDATED_BY_ANOTHER_TRANSACTION);
		Player player = playerRepository.findById(transactionHistory.getPlayerId()).get();
		return EntityToViewConverter.convertEntityToTransactionHistoryView(savedHistory, savedAccount, player); 
	
	}

	@Override
	public List<TransactionHistoryView> getTransactionHistory(PlayerAccount playerAccount) throws ResourceNotFoundException {
		
		List<TransactionHistory> list = transactionHistoryRepository.findByAccountId(playerAccount.getAccountId());
		
		if(!Optional.ofNullable(list).isPresent() || list.size() == 0) {
			throw new ResourceNotFoundException(Constants.ERROR_MSG_TRANSACTION_BY_ACCT_ID_NOT_FOUND);
		}
		
		List<TransactionHistoryView> outputList = new ArrayList<TransactionHistoryView>();
		for(TransactionHistory history: list) {
			PlayerAccount acct = playerAccountRepository.findById(history.getAccountId()).get();
			Player player = playerRepository.findById(acct.getPlayerId()).get();
			TransactionHistoryView view = EntityToViewConverter.convertEntityToTransactionHistoryView(history, acct, player);
			outputList.add(view);
		}
		
		return outputList;
	}

	@Override
	public Map<Integer, List<TransactionHistoryView>> getTransactionHistoryPerPlayer(PlayerAccount playerAccount) throws ResourceNotFoundException {

		List<PlayerAccount> accounts = playerAccountRepository.findByPlayerId(playerAccount.getPlayerId());
		
		if(!Optional.ofNullable(accounts).isPresent() || accounts.size() == 0) 
			throw new ResourceNotFoundException(Constants.ERROR_MSG_NO_ACCT);
		
		Map<Integer, List<TransactionHistoryView>> map = new HashMap<Integer, List<TransactionHistoryView>>();

		for(PlayerAccount acct: accounts) {
			map.put(acct.getAccountId(), this.getTransactionHistory(acct));
		}

		return map;
	}

}
