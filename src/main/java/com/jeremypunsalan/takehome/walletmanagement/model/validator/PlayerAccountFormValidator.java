package com.jeremypunsalan.takehome.walletmanagement.model.validator;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountForm;

public class PlayerAccountFormValidator {

	public static List<String> validateFormCreate(PlayerAccountForm form) {

		List<String> errorList = new ArrayList<String>();

		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}

		if (!Optional.ofNullable(form.getAccountName()).isPresent() || Constants.BLANK.equals(form.getAccountName())) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_NAME_REQUIRED);
		}

		return errorList;
	}
	
	public static List<String> validateFormRetrieve(PlayerAccountForm form) {

		List<String> errorList = new ArrayList<String>();

		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}

		if (!Optional.ofNullable(form.getAccountId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_ID_REQUIRED);
		}

		return errorList;
	}

	public static List<String> validateFormUpdate(PlayerAccountForm form) {

		List<String> errorList = new ArrayList<String>();

		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}

		if (!Optional.ofNullable(form.getAccountId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_ID_REQUIRED);
		}

		if (!Optional.ofNullable(form.getAccountName()).isPresent() || Constants.BLANK.equals(form.getAccountName())) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_NAME_REQUIRED);
		}

		if (!Optional.ofNullable(form.getBalance()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_BALANCE_REQUIRED);
		}

		return errorList;
	}

	public static String getErrorMessages(List<String> errorList) {
		return errorList.stream().collect(Collectors.joining(Constants.SEMI_COLON + Constants.SPACE));
	}

}
