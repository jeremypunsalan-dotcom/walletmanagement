package com.jeremypunsalan.takehome.walletmanagement.model.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jeremypunsalan.takehome.walletmanagement.helper.Constants;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionType;

public class TransactionFormValidator {

	public static List<String> validateForm(TransactionForm transactionForm) {

		List<String> errorList = new ArrayList<String>();

		// required fields
		if (!Optional.ofNullable(transactionForm.getAccountId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_ACCOUNT_ID_REQUIRED);
		}
		if (!Optional.ofNullable(transactionForm.getPlayerId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_PLAYER_ID_REQUIRED);
		}
		if (!Optional.ofNullable(transactionForm.getTransactionAmount()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_TRANSACTION_AMT_REQUIRED);
		}
		if (!Optional.ofNullable(transactionForm.getTransactionId()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_TRANSACTION_ID_REQUIRED);
		}
		if (!Optional.ofNullable(transactionForm.getTransactionType()).isPresent()) {
			errorList.add(Constants.ERROR_MSG_TRANSACTION_TYPE_REQUIRED);
		}

		// check if transactiontype is either C or D
		if (Optional.ofNullable(transactionForm.getTransactionType()).isPresent() && Arrays.stream(TransactionType.values())
				.noneMatch(trxnType -> trxnType.toString().equals(transactionForm.getTransactionType()))) {
			errorList.add(Constants.ERROR_MSG_TRANSACTION_TYPE_INVALID);
		}

		// check if amount is positive, we only accept zero to positive input
		if (Optional.ofNullable(transactionForm.getTransactionAmount()).isPresent()
				&& transactionForm.getTransactionAmount().compareTo(0.0) < 0) {
			errorList.add(Constants.ERROR_MSG_TRANSACTION_AMT_POSITIVE);
		}
		
		return errorList;

	}

	public static String getErrorMessages(List<String> errorList) {
		return errorList.stream().collect(Collectors.joining(Constants.SEMI_COLON + Constants.SPACE));
	}

}
