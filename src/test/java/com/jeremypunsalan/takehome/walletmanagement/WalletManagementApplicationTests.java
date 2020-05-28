package com.jeremypunsalan.takehome.walletmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
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
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerAccountsView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.PlayerView;
import com.jeremypunsalan.takehome.walletmanagement.model.view.TransactionHistoryView;
import com.jeremypunsalan.takehome.walletmanagement.service.AccountsCRUDService;
import com.jeremypunsalan.takehome.walletmanagement.service.AccountsOperationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WalletManagementApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class WalletManagementApplicationTests {

	@Autowired
	private AccountsOperationService accountsOperationService;

	@Autowired
	private AccountsCRUDService accountsCRUDService;

	@BeforeEach
	void setup() {
	}

	@Test
	@Order(1)
	void testGetBalance() throws ValidationException, ResourceNotFoundException {

		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setAccountId(1);
		form.setPlayerId(1);

		assertEquals(new Double(6), accountsOperationService.getAccountBalance(form));

	}

	@Test
	@Order(2)
	void testGetBalanceByPlayer() throws ValidationException, ResourceNotFoundException {

		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setPlayerId(1);

		List<PlayerAccountsView> list = accountsOperationService.getAccountBalances(form);
		System.out.println(list);
		assertEquals(2, list.size());

		PlayerAccountsView account1 = new PlayerAccountsView();
		account1.setAccountId(1);
		account1.setAccountName("Slot Machine Games Account");
		account1.setBalance(new Double(6));

		assertTrue(list.get(0).getAccountId().equals(account1.getAccountId()));
		assertTrue(list.get(0).getAccountName().equals(account1.getAccountName()));
		assertTrue(list.get(0).getBalance().equals(account1.getBalance()));

		PlayerAccountsView account2 = new PlayerAccountsView();
		account2.setAccountId(2);
		account2.setAccountName("Lottery Games Account");
		account2.setBalance(new Double(10));

		assertTrue(list.get(1).getAccountId().equals(account2.getAccountId()));
		assertTrue(list.get(1).getAccountName().equals(account2.getAccountName()));
		assertTrue(list.get(1).getBalance().equals(account2.getBalance()));

	}

	@Test
	@Order(3)
	void testCreditAccount() throws ValidationException, ResourceNotFoundException, TransactionException {

		TransactionForm transactionForm = new TransactionForm();
		transactionForm.setAccountId(1);
		transactionForm.setPlayerId(1);
		transactionForm.setTransactionAmount(new Double(6));
		transactionForm.setTransactionType("C");
		transactionForm.setTransactionId(12345);

		TransactionHistoryView trxn = accountsOperationService.transactAccount(transactionForm);
		System.out.println(trxn);

		assertEquals(new Double(6), trxn.getCreditAmount());
		assertEquals(new Integer(12345), trxn.getTransactionId());

		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setAccountId(1);
		form.setPlayerId(1);

		assertEquals(new Double(12), accountsOperationService.getAccountBalance(form));

		TransactionForm transactionForm1 = new TransactionForm();
		transactionForm1.setAccountId(2);
		transactionForm1.setPlayerId(1);
		transactionForm1.setTransactionAmount(new Double(10));
		transactionForm1.setTransactionType("C");
		transactionForm1.setTransactionId(11111);

		TransactionHistoryView trxn1 = accountsOperationService.transactAccount(transactionForm1);
		System.out.println(trxn1);

		assertEquals(new Double(10), trxn1.getCreditAmount());
		assertEquals(new Integer(11111), trxn1.getTransactionId());

	}

	@Test
	@Order(4)
	void testDebitAccout() throws ValidationException, ResourceNotFoundException, TransactionException {
		TransactionForm transactionForm = new TransactionForm();
		transactionForm.setAccountId(1);
		transactionForm.setPlayerId(1);
		transactionForm.setTransactionAmount(new Double(10));
		transactionForm.setTransactionType("D");
		transactionForm.setTransactionId(678910);

		TransactionHistoryView trxn = accountsOperationService.transactAccount(transactionForm);
		System.out.println(trxn);
		assertEquals(new Double(10), trxn.getDebitAmount());
		assertEquals(new Integer(678910), trxn.getTransactionId());

		TransactionForm transactionForm1 = new TransactionForm();
		transactionForm1.setAccountId(2);
		transactionForm1.setPlayerId(1);
		transactionForm1.setTransactionAmount(new Double(1));
		transactionForm1.setTransactionType("D");
		transactionForm1.setTransactionId(22222);

		TransactionHistoryView trxn1 = accountsOperationService.transactAccount(transactionForm1);
		System.out.println(trxn1);
		assertEquals(new Double(1), trxn1.getDebitAmount());
		assertEquals(new Integer(22222), trxn1.getTransactionId());
		

		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setAccountId(1);
		form.setPlayerId(1);

		assertEquals(new Double(2), accountsOperationService.getAccountBalance(form));
	}

	@Test
	@Order(5)
	void testGetTransactionHistory() throws ValidationException, ResourceNotFoundException {

		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setAccountId(1);
		form.setPlayerId(1);

		List<TransactionHistoryView> listTransactions = accountsOperationService.getTransactionHistoryPerAccount(form);
		assertEquals(4, listTransactions.size());

	}

	@Test
	@Order(6)
	void testGetTransactionHistoryByPlayerId() throws ValidationException, ResourceNotFoundException {

		PlayerAccountKeyForm form = new PlayerAccountKeyForm();
		form.setPlayerId(1);

		Map<Integer, List<TransactionHistoryView>> map = accountsOperationService.getTransactionHistoryPerPlayer(form);

		System.out.println(map);
		assertEquals(2, map.size());

	}

	@Test
	@Order(7)
	void testPlayerCRUD() throws ValidationException, ResourceNotFoundException {

		String playerName = "Princess Gian Allag";
		PlayerForm createForm = new PlayerForm();
		createForm.setPlayerName(playerName);
		PlayerView player = accountsCRUDService.createPlayer(createForm);

		System.out.println(player);
		assertEquals("Princess Gian Allag", player.getPlayerName());
		

		PlayerForm form = new PlayerForm();
		form.setPlayerId(1);

		PlayerView readPlayer = accountsCRUDService.getPlayerInfo(form);

		System.out.println(readPlayer);

		assertEquals(2, readPlayer.getAccounts().size());
		assertEquals(new Integer(1), readPlayer.getPlayerId());

		PlayerForm form2 = new PlayerForm();
		form2.setPlayerId(1);
		form2.setPlayerName("Jeremy pogi");

		PlayerView updatePlayer = accountsCRUDService.updatePlayerInfo(form2);

		System.out.println(updatePlayer);

		assertEquals(new Integer(1), updatePlayer.getPlayerId());
		assertEquals("Jeremy pogi", updatePlayer.getPlayerName());

	}
	
	@Test
	@Order(8)
	void testPlayerAccountCRUD() throws ValidationException, ResourceNotFoundException {
		
		PlayerAccountForm form = new PlayerAccountForm();
		form.setPlayerId(1);
		form.setAccountName("extra account");
		
		PlayerAccountsView view = accountsCRUDService.createPlayerAccount(form);
		System.out.println(view);
		assertEquals("extra account", view.getAccountName());
		assertEquals(new Double(0), view.getBalance());
		
		form.setAccountId(view.getAccountId());
		
		PlayerAccountsView view2 = accountsCRUDService.getPlayerAccount(form);
		System.out.println(view2);
		assertEquals(view.getAccountId(), view2.getAccountId());
		assertEquals(view.getAccountName(), view2.getAccountName());
		assertEquals(new Double(0), view2.getBalance());
		
		form.setAccountName("extra extra account");
		form.setBalance(new Double(5));
		
		PlayerAccountsView view3 = accountsCRUDService.updatePlayerAccount(form);
		
		System.out.println(view3);
		
		assertEquals("extra extra account", view3.getAccountName());
		assertEquals(new Double(5), view3.getBalance());
		
	}

}
