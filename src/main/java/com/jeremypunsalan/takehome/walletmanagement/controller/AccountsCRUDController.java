package com.jeremypunsalan.takehome.walletmanagement.controller;

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
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerForm;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;
import com.jeremypunsalan.takehome.walletmanagement.service.AccountsCRUDService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="AccountsCRUDController", description="Rest API for Player Profile and Accounts CRUD.")
@RestController
@CrossOrigin
@RequestMapping(value = "/rest/accounts/crud")
public class AccountsCRUDController {
	
	@Autowired
	@Qualifier("AccountsCRUDService")
	private AccountsCRUDService accountsCRUDService;
	
	@ApiOperation(value = "Create Player profile.", response = PlayerView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@PostMapping(value="/player/create")
	public PlayerView createPlayer(
			@RequestBody final PlayerForm form
			) throws ValidationException {
		return accountsCRUDService.createPlayer(form);
	}
	
	@ApiOperation(value = "Get Player profile by player ID.", response = PlayerView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@GetMapping(value="/player/{playerId}")
	public PlayerView getPlayerInfo(
			@ApiParam(value = "Player ID.", name = "playerId", required = true)
			@PathVariable("playerId") final Integer playerId
			) throws ValidationException, ResourceNotFoundException {
		
		PlayerForm form = new PlayerForm();
		form.setPlayerId(playerId);
		
		return accountsCRUDService.getPlayerInfo(form);
		
	}
	
	@ApiOperation(value = "Update Player profile.", response = PlayerView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@PostMapping(value="/player/update")
	public PlayerView updatePlayer(
			@RequestBody final PlayerForm form
			) throws ValidationException, ResourceNotFoundException {
		return accountsCRUDService.updatePlayerInfo(form);
	}
	
	@ApiOperation(value = "Create Account profile.", response = PlayerAccountsView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@PostMapping(value="/playeraccount/create")
	public PlayerAccountsView createPlayerAccount(
			@RequestBody final PlayerAccountForm form
			) throws ValidationException, ResourceNotFoundException {
		return accountsCRUDService.createPlayerAccount(form);
	}
	
	@ApiOperation(value = "Get Player Account by Account ID and Player ID.", response = PlayerAccountsView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@GetMapping(value="/playeraccount/{playerId}")
	public PlayerAccountsView getPlayerAccount(
			@ApiParam(value = "Player ID.", name = "playerId", required = true)
			@PathVariable("playerId")
			final Integer playerId,
			@ApiParam(value = "Account ID.", name = "accountId", required = true)
			@RequestParam(name = "accountId", required = true)
			final Integer accountId
			) throws ValidationException, ResourceNotFoundException {
		PlayerAccountForm form = new PlayerAccountForm();
		form.setAccountId(accountId);
		form.setPlayerId(playerId);
		
		return accountsCRUDService.getPlayerAccount(form);
	}
	
	@ApiOperation(value = "Update Player Account.", response = PlayerAccountsView.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Exception"),
			@ApiResponse(code = 404, message = "Resource Not Found Exception"),
			@ApiResponse(code = 500, message = "Internal Exception")
			
	})
	
	@PostMapping(value="/playeraccount/update")
	public PlayerAccountsView updatePlayerAccount(
			@RequestBody final PlayerAccountForm form
			) throws ValidationException, ResourceNotFoundException {
		return accountsCRUDService.updatePlayerAccount(form);
	}
	

}
