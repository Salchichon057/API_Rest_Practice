package upc.edu.Ejercicio03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.Ejercicio03.dto.TransactionDTO;
import upc.edu.Ejercicio03.model.Account;
import upc.edu.Ejercicio03.model.Transaction;
import upc.edu.Ejercicio03.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bank/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //  URL http://localhost:8080/api/bank/v1/accounts/{id}/transactions
    //  Method POST
    @Transactional
    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<Transaction> createTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return new ResponseEntity<Transaction>(transactionService.createTransaction(id, transaction), HttpStatus.CREATED);
    }

    //  URL http://localhost:8080/api/bank/v1/transactions/filterByNameCustomer
    //  Method GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByNameCustomer")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByNameCustomer(@RequestBody Account account) {
        return new ResponseEntity<List<TransactionDTO>>(transactionService.getTransactionsByNameCustomer(account.getNameCustomer()), HttpStatus.OK);
    }

    //  URL http://localhost:8080/api/bank/v1/transactions/filterByCreateDateRange
    //  Method GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/filterByCreateDateRange")
    public ResponseEntity<List<Transaction>> getTransactionByCreateDateRange(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate
    ) {
        // Valida los parámetros.
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().build();
        }

        // Obtiene las transacciones dentro del rango de fechas especificado.
        List<Transaction> transactions = transactionService.getTransactionByCreateDateRange(startDate, endDate);

        // Devuelve las transacciones.
        return ResponseEntity.ok(transactions);
    }
}
