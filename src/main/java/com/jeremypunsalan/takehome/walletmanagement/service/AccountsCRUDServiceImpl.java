package com.jeremypunsalan.takehome.walletmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeremypunsalan.takehome.walletmanagement.dao.AccountsCRUDDao;
import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.helper.FormToEntityConverter;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerForm;
import com.jeremypunsalan.takehome.walletmanagement.model.validator.InputValidator;
import com.jeremypunsalan.takehome.walletmanagement.model.validator.PlayerAccountFormValidator;
import com.jeremypunsalan.takehome.walletmanagement.model.validator.PlayerFormValidator;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;

@Component("AccountsCRUDService")
public class AccountsCRUDServiceImpl implements AccountsCRUDService {

	private AccountsCRUDDao accountsCRUDDao;

	@Autowired
	public void setAccountsCRUDDao(AccountsCRUDDao dao) {
		this.accountsCRUDDao = dao;
	}

	@Override
	public PlayerView createPlayer(PlayerForm player) throws ValidationException {

		if (!Optional.ofNullable(player.getPlayerName()).isPresent() || Constants.BLANK.equals(player.getPlayerName())) {
			throw new ValidationException(Constants.ERROR_MSG_PLAYER_NAME_REQUIRED);
		}

		return accountsCRUDDao.createPlayer(FormToEntityConverter.convertFormToPlayer(player));

	}

	@Override
	public PlayerView getPlayerInfo(PlayerForm player) throws ValidationException, ResourceNotFoundException {
		
		if (!Optional.ofNullable(player.getPlayerId()).isPresent()) {
			throw new ValidationException(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}
		
		return accountsCRUDDao.getPlayerInfo(FormToEntityConverter.convertFormToPlayer(player));
	}

	@Override
	public PlayerView updatePlayerInfo(PlayerForm form) throws ValidationException, ResourceNotFoundException {
		
		InputValidator<PlayerForm> validator = PlayerFormValidator::validateFormRequiredAll;
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) {
			throw new ValidationException(PlayerFormValidator.getErrorMessages(errorList));
		}
		
		return accountsCRUDDao.updatePlayerInfo(FormToEntityConverter.convertFormToPlayer(form)); 
		
	}

	@Override
	public PlayerAccountsView createPlayerAccount(PlayerAccountForm form) throws ValidationException, ResourceNotFoundException {
		
		InputValidator<PlayerAccountForm> validator = PlayerAccountFormValidator::validateFormCreate;
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) {
			throw new ValidationException(PlayerAccountFormValidator.getErrorMessages(errorList));
		}
		if(!Optional.ofNullable(form.getBalance()).isPresent())
			form.setBalance(Constants.D_ZERO);
		
		return accountsCRUDDao.createPlayerAccount(FormToEntityConverter.convertFormToPlayerAccount(form));
	}

	@Override
	public PlayerAccountsView getPlayerAccount(PlayerAccountForm form) throws ValidationException, ResourceNotFoundException {
		
		InputValidator<PlayerAccountForm> validator = PlayerAccountFormValidator::validateFormRetrieve;
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) {
			throw new ValidationException(PlayerAccountFormValidator.getErrorMessages(errorList));
		}
		
		return accountsCRUDDao.getPlayerAccount(FormToEntityConverter.convertFormToPlayerAccount(form));
	}

	@Override
	public PlayerAccountsView updatePlayerAccount(PlayerAccountForm form) throws ValidationException, ResourceNotFoundException {
		
		InputValidator<PlayerAccountForm> validator = PlayerAccountFormValidator::validateFormUpdate;
		List<String> errorList = validator.validate(form);
		if(errorList.size() > 0) {
			throw new ValidationException(PlayerAccountFormValidator.getErrorMessages(errorList));
		}
		
		return accountsCRUDDao.updatePlayerAccount(FormToEntityConverter.convertFormToPlayerAccount(form));
		
	}

}
