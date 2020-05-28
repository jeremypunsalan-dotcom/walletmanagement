package com.jeremypunsalan.takehome.walletmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.helper.EntityToViewConverter;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.Player;
import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;
import com.jeremypunsalan.takehome.walletmanagement.repository.PlayerAccountRepository;
import com.jeremypunsalan.takehome.walletmanagement.repository.PlayerRepository;

@Component("AccountsCRUDDao")
public class AccountsCRUDDaoImpl implements AccountsCRUDDao {
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public void setPlayerRepository(PlayerRepository repo) {
		this.playerRepository = repo;
	}
	
	private PlayerAccountRepository playerAccountRepository;
	
	@Autowired
	public void setPlayerAccountRepository(PlayerAccountRepository repo) {
		this.playerAccountRepository = repo;
	}

	@Override
	public PlayerView createPlayer(Player player) {
		
		Player savedPlayer = playerRepository.save(player);
		
		return EntityToViewConverter.convertEntityToPlayerView(savedPlayer);
		
	}

	@Override
	public PlayerView getPlayerInfo(Player player) throws ResourceNotFoundException {
		
		Player playerEntity = playerRepository.findById(player.getPlayerId()).orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_MSG_PLAYER_PROFILE_NOT_FOUND));
		
		List<PlayerAccount> accounts = playerAccountRepository.findByPlayerId(playerEntity.getPlayerId());
		
		if(!Optional.ofNullable(accounts).isPresent() || accounts.size() == 0) 
			return EntityToViewConverter.convertEntityToPlayerView(playerEntity);
		else return EntityToViewConverter.convertEntityToPlayerView(playerEntity, accounts);
	
	}

	@Override
	public PlayerView updatePlayerInfo(Player player) throws ResourceNotFoundException {
		
		Player playerEntity = playerRepository.findById(player.getPlayerId()).orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_MSG_PLAYER_PROFILE_NOT_FOUND));
		playerEntity.setPlayerName(player.getPlayerName());
		
		Player savedEntity = playerRepository.save(playerEntity);
		
		return EntityToViewConverter.convertEntityToPlayerView(savedEntity);
	}

	@Override
	public PlayerAccountsView createPlayerAccount(PlayerAccount playerAccount) throws ResourceNotFoundException {
		if(!playerRepository.findById(playerAccount.getPlayerId()).isPresent())
			throw new ResourceNotFoundException(Constants.ERROR_MSG_PLAYER_PROFILE_NOT_FOUND);
		PlayerAccount savedEntity = playerAccountRepository.save(playerAccount);
		return EntityToViewConverter.convertEntityToPlayerAccountsView(savedEntity);
	}

	@Override
	public PlayerAccountsView getPlayerAccount(PlayerAccount playerAccount) throws ResourceNotFoundException {
		
		playerAccount = playerAccountRepository.findById(playerAccount.getAccountId()).orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_MSG_NO_ACCT));
		
		return EntityToViewConverter.convertEntityToPlayerAccountsView(playerAccount);
	}

	@Override
	public PlayerAccountsView updatePlayerAccount(PlayerAccount playerAccount) throws ResourceNotFoundException {
		
		PlayerAccount playerAccountEntity = playerAccountRepository.findById(playerAccount.getAccountId()).orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_MSG_NO_ACCT));
		
		playerAccountEntity.setAccountName(playerAccount.getAccountName());
		playerAccountEntity.setBalance(playerAccount.getBalance());
		
		PlayerAccount savedEntity = playerAccountRepository.save(playerAccountEntity);
		
		return EntityToViewConverter.convertEntityToPlayerAccountsView(savedEntity);
	}

}
