package upc.edu.Ejercicio03.service.Impl;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.Ejercicio03.model.Account;
import upc.edu.Ejercicio03.repository.AccountRepository;
import upc.edu.Ejercicio03.service.AccountService;

import upc.edu.Ejercicio03.exception.ValidationException;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        validateAccount(account);
        existAccountByNameCustomerAndNumberAccount(account);
        return accountRepository.save(account);
    }
    @Override
    public Account getAccountByNameCustomer(String nameCustomer) {
        return accountRepository.findByNameCustomer(nameCustomer);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private void validateAccount(@NotNull Account account) {
        if (account.getNameCustomer() == null || account.getNameCustomer().isEmpty()) { // Si el nombre del cliente es nulo o vacío
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }
        if (account.getNameCustomer().length() > 30 ){
            throw new ValidationException("El nombre del cliente no debe exceder los 30 caracteres");
        }
        if (account.getNumberAccount() == null || account.getNumberAccount().isEmpty()) { // Si el número de cuenta es nulo o vacío
            throw new ValidationException("El número de cuenta debe ser obligatorio");
        }

        if (account.getNumberAccount().length() > 13 ){
            throw new ValidationException("El número de cuenta debe tener una longitud de 13 caracteres");
        }
    }
    private void existAccountByNameCustomerAndNumberAccount(@NotNull Account account) {
        if (accountRepository.existsByNameCustomerAndNumberAccount(account.getNameCustomer(), account.getNumberAccount())) {
            throw new ValidationException("No se puede registrar la cuenta porque ya existe uno con esos datos");
        }
    }
}
