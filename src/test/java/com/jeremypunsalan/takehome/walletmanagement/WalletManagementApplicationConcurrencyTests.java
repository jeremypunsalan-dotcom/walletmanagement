package com.jeremypunsalan.takehome.walletmanagement;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerAccountKeyForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.PlayerForm;
import com.jeremypunsalan.takehome.walletmanagement.model.form.TransactionForm;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;
import com.jeremypunsalan.takehome.walletmanagement.service.AccountsCRUDService;
import com.jeremypunsalan.takehome.walletmanagement.service.AccountsOperationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WalletManagementApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class WalletManagementApplicationConcurrencyTests {

	@Autowired
	private AccountsOperationService accountsOperationService;

	@Autowired
	private AccountsCRUDService accountsCRUDService;

	PlayerView playerProfile;

	@BeforeEach
	void setup() throws ValidationException, ResourceNotFoundException, TransactionException {

		// create the player profile and accounts to be used for concurrent testing
		PlayerForm playerForm = new PlayerForm();
		playerForm.setPlayerName("Jeremy Longakit Punsalan");

		playerProfile = accountsCRUDService.createPlayer(playerForm);

		PlayerAccountForm playerAccountForm = new PlayerAccountForm();
		playerAccountForm.setPlayerId(playerProfile.getPlayerId());
		playerAccountForm.setAccountName("Main Account");
		accountsCRUDService.createPlayerAccount(playerAccountForm);

		playerForm.setPlayerId(playerProfile.getPlayerId());
		playerProfile = accountsCRUDService.getPlayerInfo(playerForm);
		
		

		TransactionForm form = new TransactionForm();
		form.setPlayerId(playerProfile.getPlayerId());
		form.setAccountId(playerProfile.getAccounts().get(0).getAccountId());
		form.setTransactionAmount(new Double(100));
		form.setTransactionType("C");
		form.setTransactionId(new Integer(19));
		accountsOperationService.transactAccount(form);

	}

	@Test
	void testConcurrency() throws InterruptedException, ExecutionException {

		ExecutorService exService1 = Executors.newFixedThreadPool(2);

		TransactionForm form1 = new TransactionForm();
		form1.setPlayerId(playerProfile.getPlayerId());
		form1.setAccountId(playerProfile.getAccounts().get(0).getAccountId());
		form1.setTransactionAmount(new Double(10));
		form1.setTransactionId(new Integer(20));
		form1.setTransactionType("C");

		Callable<TransactionHistoryView> transaction1 = createTransaction(form1);

		TransactionForm form2 = new TransactionForm();
		form2.setPlayerId(playerProfile.getPlayerId());
		form2.setAccountId(playerProfile.getAccounts().get(0).getAccountId());
		form2.setTransactionAmount(new Double(2));
		form2.setTransactionType("D");
		form2.setTransactionId(new Integer(21));

		Callable<TransactionHistoryView> transaction2 = createTransaction(form2);

		Future<TransactionHistoryView> f1 = exService1.submit(transaction1);
		Future<TransactionHistoryView> f2 = exService1.submit(transaction2);

		assertThrows(ExecutionException.class, () -> {
			f1.get();
			f2.get();
		});
		
		ExecutorService exService2 = Executors.newFixedThreadPool(2);
		
		TransactionForm form3 = new TransactionForm();
		form3.setPlayerId(playerProfile.getPlayerId());
		form3.setAccountId(playerProfile.getAccounts().get(0).getAccountId());
		form3.setTransactionAmount(new Double(10));
		form3.setTransactionId(new Integer(1099));
		form3.setTransactionType("C");
		Callable<TransactionHistoryView> transaction3 = createTransaction(form3);

		PlayerAccountKeyForm form4 = new PlayerAccountKeyForm();
		form4.setAccountId(playerProfile.getAccounts().get(0).getAccountId());
		form4.setPlayerId(playerProfile.getPlayerId());

		Callable<Double> transaction4 = getAccountBalance(form4);
		
		Future<TransactionHistoryView> f3 = exService2.submit(transaction3);
		Future<Double> f4 = exService2.submit(transaction4);
		
		System.out.println(f3.get());
		System.out.println(f4.get());
		
		
		

	}

	private Callable<TransactionHistoryView> createTransaction(TransactionForm form) {
		return () -> {
			return accountsOperationService.transactAccount(form);
		};
	}

	private Callable<Double> getAccountBalance(PlayerAccountKeyForm form) {
		return () -> {
			return accountsOperationService.getAccountBalance(form);
		};

	}

}
