DROP TABLE IF EXISTS players;

CREATE TABLE players (
	playerid INT AUTO_INCREMENT PRIMARY KEY,
  	playername VARCHAR(255) NOT NULL,
  	createdate TIMESTAMP,
  	updatedate TIMESTAMP,
  	version INT
);

INSERT INTO players (playername, createdate, version) VALUES
  	('Jeremy Punsalan', CURRENT_TIMESTAMP(), 1),
  	('Prince Jeremiah Punsalan', CURRENT_TIMESTAMP(), 1),
  	('Angelica Punsalan', CURRENT_TIMESTAMP(), 1);

DROP TABLE IF EXISTS player_accounts;

CREATE TABLE player_accounts (
	accountid INT AUTO_INCREMENT PRIMARY KEY,
	playerid INT NOT NULL,
	accountname VARCHAR(255) NOT NULL,
	balance DOUBLE NOT NULL DEFAULT 0,
	createdate TIMESTAMP,
  	updatedate TIMESTAMP,
  	version INT
);

INSERT INTO player_accounts (playerid, accountname, balance, createdate, version) VALUES
  	(1, 'Slot Machine Games Account', 6, CURRENT_TIMESTAMP(), 1),
  	(1, 'Lottery Games Account', 10, CURRENT_TIMESTAMP(), 1),
  	(2, 'Lottery Games Account', 4, CURRENT_TIMESTAMP(), 1),
 	(3, 'Slot Machine Games Account', 5, CURRENT_TIMESTAMP(), 1);
 
DROP TABLE IF EXISTS transaction_history;

CREATE TABLE transaction_history (
	transactionid INT NOT NULL PRIMARY KEY,
	accountid INT NOT NULL,
	playerid INT NOT NULL,
	transaction_desc VARCHAR(255) NOT NULL,
	transaction_amount DOUBLE NOT NULL DEFAULT 0,
	before_balance DOUBLE NOT NULL DEFAULT 0,
	after_balance DOUBLE NOT NULL DEFAULT 0,
	createdate TIMESTAMP,
  	updatedate TIMESTAMP,
  	version INT
);

INSERT INTO transaction_history (transactionid, accountid, playerid, transaction_desc, transaction_amount, before_balance, after_balance, createdate, version) VALUES
  	(1, 1, 1, 'Credit to account', 10, 0, 10, CURRENT_TIMESTAMP(), 1),
  	(2, 1, 1, 'Debit to account', -4, 10, 6, CURRENT_TIMESTAMP(), 1),
  	(3, 2, 1, 'Credit to account', 10, 0, 10, CURRENT_TIMESTAMP(), 1),
  	(4, 3, 2, 'Credit to account', 10, 0, 10, CURRENT_TIMESTAMP(), 1),
  	(5, 3, 2, 'Debit to account', -6, 10, 4, CURRENT_TIMESTAMP(), 1),
  	(6, 4, 3, 'Credit to account', 10, 0, 10, CURRENT_TIMESTAMP(), 1);
  
