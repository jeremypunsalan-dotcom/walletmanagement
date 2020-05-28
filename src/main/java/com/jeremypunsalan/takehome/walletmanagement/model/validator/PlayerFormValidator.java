package com.jeremypunsalan.takehome.walletmanagement.model.validator;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerForm;


public class PlayerFormValidator {

	public static List<String> validateFormRequiredAll(PlayerForm form) {

		List<String> errorList = new ArrayList<String>();

		// required fields - all
		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}
		
		if(!Optional.ofNullable(form.getPlayerName()).isPresent() || Constants.BLANK.equals(form.getPlayerName())) {
			errorList.add(Constants.ERROR_MSG_PLAYER_NAME_REQUIRED);
		} 

		return errorList;
	}
	
	public static List<String> validateFormRequiredPlayerName(PlayerForm form) {

		List<String> errorList = new ArrayList<String>();
		
		if (!Optional.ofNullable(form.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_NAME_REQUIRED);
		}

		return errorList;
	}

	public static String getErrorMessages(List<String> errorList) {
		return errorList.stream().collect(Collectors.joining(Constants.SEMI_COLON + Constants.SPACE));
	}

}
