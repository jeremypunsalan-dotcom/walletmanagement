package com.jeremypunsalan.takehome.walletmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeremypunsalan.takehome.walletmanagement.model.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
