package upc.edu.Ejercicio03.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.Ejercicio03.dto.TransactionDTO;
import upc.edu.Ejercicio03.exception.ValidationException;
import upc.edu.Ejercicio03.model.Account;
import upc.edu.Ejercicio03.model.Transaction;
import upc.edu.Ejercicio03.repository.AccountRepository;
import upc.edu.Ejercicio03.repository.TransactionRepository;
import upc.edu.Ejercicio03.service.TransactionService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Transaction createTransaction(Long id, Transaction transaction) {
        validationTransaction(transaction);
        Account account = new Account();
        account.setId(id);
        transaction.setAccount(account);

        // Inicializar el balance de la transacción
        if (transaction.getBalance() != null) {
            transaction.setBalance(transaction.getBalance());
        } else {
            transaction.setBalance(0.0);
        }

        caseBalance(transaction);
        transaction.setCreateDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }
    @Override
    public List<TransactionDTO> getTransactionsByNameCustomer(String nameCustomer) {
        Account account_id = accountRepository.findByNameCustomer(nameCustomer);
        List<Transaction> transactions = transactionRepository.findAllByAccount_Id(account_id.getId());
        List<TransactionDTO> transactionDTOs = TransactionDTO.of(transactions);
        return transactionDTOs;
    }

//  Como funciona el modelMapper.map?
//  modelMapper.map(Objeto, Clase.class) -> retorna un objeto de tipo Clase.class
//  Ejemplo:
//  modelMapper.map(transactionDTO, Transaction.class) -> retorna un objeto de tipo Transaction


    @Override
    public List<Transaction> getTransactionByCreateDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByCreateDateBetween(startDate, endDate);
    }

    public void validationTransaction(Transaction transaction) {
        if (transaction.getType() == null || transaction.getType().isEmpty()) {
            throw new ValidationException("El tipo de transacción bancaria debe ser obligatorio");
        }
        if (transaction.getAmount() < 0.0 && (transaction.getType().equals("Deposito") || transaction.getType().equals("Retiro")) ) {
            throw new ValidationException("El monto de la transacción bancaria debe ser mayor a S/. 0.0");
        }
        if (transaction.getAmount() > transaction.getBalance() && transaction.getType().equals("Retiro")) {
            throw new ValidationException("En una transacción bancaria tipo retiro el monto no puede ser mayor al saldo");
        }

        if (transaction.getBalance() == 0 && transaction.getType().equals("Retiro")) {
            throw new ValidationException("No se puede realizar un retiro porque el saldo es 0");
        }
    }
    public void caseBalance(Transaction transaction) {
        if (Objects.equals(transaction.getBalance(), transaction.getAmount()) && transaction.getType().equals("Retiro")) {
            transaction.setBalance(0.0);
        }

        if (transaction.getBalance() == null && transaction.getType().equals("Deposito")) {
            transaction.setBalance(transaction.getAmount());
        }
        if (transaction.getType().equals("Deposito")) {
            transaction.setBalance(transaction.getBalance() + transaction.getAmount());
        }
        if (transaction.getType().equals("Retiro") && transaction.getBalance() > transaction.getAmount()) {
            transaction.setBalance(transaction.getBalance() - transaction.getAmount());
        }

    }

}
