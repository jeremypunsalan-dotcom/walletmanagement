package com.jeremypunsalan.takehome.walletmanagement.model.validator;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountKeyForm;


public class PlayerAccountKeyFormValidator {

	public static List<String> validateFormRequiredAll(PlayerAccountKeyForm form) {

		List<String> errorList = new ArrayList<String>();

		// required fields - all
		if (!Optional.ofNullable(form.getAccountId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_ID_REQUIRED);
		}
		
		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}

		return errorList;
	}
	
	public static List<String> validateFormRequiredPlayerId(PlayerAccountKeyForm form) {

		List<String> errorList = new ArrayList<String>();
		
		//required - only player id

		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}

		return errorList;
	}

	public static String getErrorMessages(List<String> errorList) {
		return errorList.stream().collect(Collectors.joining(Constants.SEMI_COLON + Constants.SPACE));
	}

}
