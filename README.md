Wallet Management Microservice

This is the implementation of the Simple Wallet Management REST API.

Overview --
The implementation has the following main operations:
1. Get Account Balance by Player ID and Account ID :
/rest/accounts/operations/getaccountbalance/{playerId}?accountId={accountId}
2. Get Account Balance by Player ID only:
/rest/accounts/operations/getaccountbalance/all/{playerId}
3. Do transaction either by credit or debit:
/rest/accounts/operations/transact
4. Get Transaction History by Player ID and Account ID:
/rest/accounts/operations/gettransactionhistory/{playerId}?accountId={accountId}
5. Get Transaction History by Player ID only:
/rest/accounts/operations/gettransactionhistory/all/{playerId}
Furthermore, these are the CRUD operations for both player profile and player accounts:
1. Create Player profile:
/rest/accounts/crud/player/create
2. Get Player profile by player ID:
/rest/accounts/crud/player/{playerId}
3. Update Player profile:
/rest/accounts/crud/player/update
4. Create Account profile:
/rest/accounts/crud/playeraccount/create
5. Get Player Account by Account ID and Player ID:
/rest/accounts/crud/playeraccount/{playerId}?accountId={accountId}
6. Update Player Account:
/rest/accounts/crud/playeraccount/update

Data format:
The data was decided that in every gaming accounts there will be multiple accounts belonging to the same player (ie, 1 player can have multiple unique accounts).
So the operations also revolve on this structure, for example, when creating 1 player info, it can happen to create multiple accounts for 1 player info.


Installation
1. Download code repository from https://github.com/jeremypunsalan-dotcom/walletmanagement.git
2. Run mvn clean install under the project folder

To run the project:
1. Using your favorite IDE
	a. Run the project as Spring Boot Project (depends on IDE on how to run project as Spring Boot)
	b. Hit http://localhost:8083/swagger-ui.html to check and test API functionality.
2. Using java -jar
	a. Go to /target folder (make sure the project is compiled first)
	b. run "java -jar walletmanagement.jar"
	c. Hit http://localhost:8083/swagger-ui.html to check and test API functionality.
3. Using mvn
	a. Under the project folder, run "mvn spring-boot:run"
	b. Hit http://localhost:8083/swagger-ui.html to check and test API functionality.

Installation and run containerized walletmanagement app
This project has been containerized if you choose to use this instead. To do this:
1. Download the container using the command "docker pull jeremypunsalandotcom/walletmanagement:<version>" where version is the latest version number
(please check the docker hub first the latest version number: https://hub.docker.com/repository/docker/jeremypunsalandotcom/walletmanagement)
2. Run and spin the container.
3. Hit <container public url>:8083/swagger-ui.html to check and test API functionality.  

Limitations (list of TODOs so that this api can be production-ready):
1. Delete functions are not done in the CRUD service. 
2. Player ID and Account ID are auto incremented and integer-based. This should be a UUID or a unique identifier.
3. When creating a player profile, in online gaming platforms, the player accounts where automatically created. Since the account types are not stated, this feature is not done.
4. The current h2 in memory db is set to memory mode. To set it to persistent, just change the application.properties to make it file-based.
5. Transaction Date Range in searching transaction should be included.
6. Authentication thru the requests should be implemented.


