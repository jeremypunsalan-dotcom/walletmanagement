package com.jeremypunsalan.takehome.walletmanagement.helper;

public interface Constants {

	static final String SEMI_COLON = ";";
	static final String SPACE = " ";
	
	
	
	static final String ERROR_MSG_PLAYER_ID_REQUIRED = "Player ID is required.";
	static final String ERROR_MSG_ACCOUNT_ID_REQUIRED = "Account ID is required.";
	static final String ERROR_MSG_PLAYER_ACCOUNT_NOT_FOUND = "Player account not found.";
	static final String ERROR_MSG_PLAYER_NAME_REQUIRED = "Player Name is required.";
	static final String ERROR_MSG_ACCOUNT_NAME_REQUIRED = "Account Name is required.";
	static final String ERROR_MSG_ACCOUNT_BALANCE_REQUIRED = "Account Balance is required.";
	static final String ERROR_MSG_TRANSACTION_AMT_REQUIRED = "Transaction amount is required.";
	static final String ERROR_MSG_TRANSACTION_ID_REQUIRED = "Transaction ID is required.";
	static final String ERROR_MSG_TRANSACTION_TYPE_REQUIRED = "Transaction type is required.";
	static final String ERROR_MSG_TRANSACTION_TYPE_INVALID = "Transaction type is invalid.";
	static final String ERROR_MSG_TRANSACTION_AMT_POSITIVE = "Transaction Amount should be positive.";
	static final String ERROR_MSG_TRANSACTION_ID_NOT_UNIQUE = "Transaction ID is not unique.";
	static final String ERROR_MSG_TRANSACTION_UPDATED_BY_ANOTHER_TRANSACTION = "Player Profile, Player Account or Transaction was updated by another process. Error Details: ";
	static final String ERROR_MSG_TRANSACTION_BY_ACCT_ID_NOT_FOUND = "Transactions by given account id not found.";
	static final String ERROR_MSG_TRANSACTION_INSUFFICIENT_FUNDS = "Insufficient funds.";
	static final String ERROR_MSG_NO_ACCT = "No accounts found for particular player.";
	static final String ERROR_MSG_TRANSACTION_CANNOT_CREATE_PLAYER = "Transaction issue; cannot create player profile.";
	static final String ERROR_MSG_PLAYER_PROFILE_NOT_FOUND = "Player profile not found.";
	
	static final String TRXN_DESC_DEFAULT_CREDIT = "Credit Transaction";
	static final String TXN_DESC_DEFAULT_DEBIT = "Debit Transaction";
	static final Double D_ZERO = new Double(0.0);
	static final String BLANK = "";
	
	
	
	
	
	
	
	
	

}
