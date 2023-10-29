package upc.edu.Ejercicio03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.Ejercicio03.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);

    List<Transaction> findAllByAccount_Id(Long id);
}
