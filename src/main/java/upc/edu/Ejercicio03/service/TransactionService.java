package upc.edu.Ejercicio03.service;
import upc.edu.Ejercicio03.dto.TransactionDTO;
import upc.edu.Ejercicio03.model.Account;
import upc.edu.Ejercicio03.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    public abstract Transaction createTransaction(Long id, Transaction transaction);
    List<TransactionDTO> getTransactionsByNameCustomer(String nameCustomer);


    List<Transaction> getTransactionByCreateDateRange(LocalDate startDate, LocalDate endDate);
}
