package com.jeremypunsalan.takehome.walletmanagement.service;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerForm;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;

public interface AccountsCRUDService {

	PlayerView createPlayer(PlayerForm player) throws ValidationException;

	PlayerView getPlayerInfo(PlayerForm player) throws ValidationException, ResourceNotFoundException;

	PlayerView updatePlayerInfo(PlayerForm form) throws ValidationException, ResourceNotFoundException;

	PlayerAccountsView createPlayerAccount(PlayerAccountForm form) throws ValidationException, ResourceNotFoundException;

	PlayerAccountsView getPlayerAccount(PlayerAccountForm form) throws ValidationException, ResourceNotFoundException;

	PlayerAccountsView updatePlayerAccount(PlayerAccountForm form) throws ValidationException, ResourceNotFoundException;

}
