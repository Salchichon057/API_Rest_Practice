package upc.edu.Ejercicio03.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.Ejercicio03.model.Account;
import upc.edu.Ejercicio03.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class AccountController {
    @Autowired // Sirve para inyectar una dependencia
    private AccountService accountService;

    //URL http://localhost:8080/api/bank/v1/accounts
    // Method GET
    @Transactional(readOnly = true)
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    // URL http://localhost:8080/api/bank/v1/accounts
    // Method POST
    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    // URL http://localhost:8080/api/bank/v1/accounts
    // Method GET
    @Transactional
    @GetMapping("/accounts/filterByNameCustomer")
    public ResponseEntity<Account> getAccountByNameCustomer(@RequestBody String nameCustomer) {
        return new ResponseEntity<Account>(accountService.getAccountByNameCustomer(nameCustomer), HttpStatus.OK);
    }

}
