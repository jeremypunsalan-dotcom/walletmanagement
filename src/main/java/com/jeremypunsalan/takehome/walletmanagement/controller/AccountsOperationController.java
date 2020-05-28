package com.jeremypunsalan.takehome.walletmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountKeyForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionForm;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;
import com.jeremypunsalan.takehome.walletmanagement.service.AccountsOperationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="AccountsOperationController", description="Rest API for Main Wallet Management transactions.")
@RestController
@CrossOrigin
@RequestMapping(value = "/rest/accounts/operations")
public class AccountsOperationController {
	
	@Autowired
	@Qualifier("AccountsOperationService")
	private AccountsOperationService accountsOperationService;
	
	@ApiOperation(value = "Get Account Balance by Player ID and Account ID.", response = Double.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@GetMapping(value="/getaccountbalance/{playerId}")
	public Double getAccountBalance(
			@ApiParam(value = "Player ID.", name = "playerId", required = true)
			@PathVariable("playerId")
			final Integer playerId,
			@ApiParam(value = "Account ID.", name = "accountId", required = true)
			@RequestParam(name = "accountId", required = true)
			final Integer accountId
			) throws ValidationException, ResourceNotFoundException {
		
		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setAccountId(accountId);
		form.setPlayerId(playerId);
		
		return accountsOperationService.getAccountBalance(form);
	}
	
	@ApiOperation(value = "Get Account Balance by Player ID only.", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@GetMapping(value="/getaccountbalance/all/{playerId}")
	public List<PlayerAccountsView> getAccountBalance(
			@ApiParam(value = "Player ID.", name = "playerId", required = true)
			@PathVariable("playerId")
			final Integer playerId
			) throws ValidationException, ResourceNotFoundException {
		
		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setPlayerId(playerId);
		
		return accountsOperationService.getAccountBalances(form);
	}
	
	@ApiOperation(value = "Do transaction either by credit or debit.", response = TransactionHistoryView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Transaction Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@PostMapping(value="/transact")
	public TransactionHistoryView transact(
			@RequestBody final TransactionForm transactionForm
			) throws ValidationException, ResourceNotFoundException, TransactionException {
		
		return accountsOperationService.transactAccount(transactionForm);
		
	}
	
	@ApiOperation(value = "Get Transaction History by Player ID and Account ID.", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@GetMapping(value="/gettransactionhistory/{playerId}")
	public List<TransactionHistoryView> getTransactionHistoryPerAccount(
			@ApiParam(value = "Player ID.", name = "playerId", required = true)
			@PathVariable("playerId")
			final Integer playerId,
			@ApiParam(value = "Account ID.", name = "accountId", required = true)
			@RequestParam(name = "accountId", required = true)
			final Integer accountId
			) throws ValidationException, ResourceNotFoundException {
		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setAccountId(accountId);
		form.setPlayerId(playerId);
		
		return accountsOperationService.getTransactionHistoryPerAccount(form);
		
	}
	
	@ApiOperation(value = "Get Transaction History by Player ID only.", response = Map.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@GetMapping(value="/gettransactionhistory/all/{playerId}")
	public Map<Integer, List<TransactionHistoryView>> getTransactionHistoryPerPlayer(
			@ApiParam(value = "Player ID.", name = "playerId", required = true)
			@PathVariable("playerId")
			final Integer playerId
			) throws ValidationException, ResourceNotFoundException {
		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setPlayerId(playerId);
		
		return accountsOperationService.getTransactionHistoryPerPlayer(form);
		
	}
	

}
