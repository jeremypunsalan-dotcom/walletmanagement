package com.jeremypunsalan.takehome.walletmanagement.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.jeremypunsalan.takehome.walletmanagement.model.entity.PlayerAccount;

public interface PlayerAccountRepository extends JpaRepository<PlayerAccount, Integer> {
	
	List<PlayerAccount> findByPlayerId(Integer playerId);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	Optional<PlayerAccount> findByAccountId(Integer accountId);

}
