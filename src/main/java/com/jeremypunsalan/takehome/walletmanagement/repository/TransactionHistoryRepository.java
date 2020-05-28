package com.jeremypunsalan.takehome.walletmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeremypunsalan.takehome.walletmanagement.model.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

	List<TransactionHistory> findByAccountId(Integer accountId) ;

}
