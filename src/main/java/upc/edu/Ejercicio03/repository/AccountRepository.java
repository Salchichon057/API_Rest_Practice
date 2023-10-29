package upc.edu.Ejercicio03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.Ejercicio03.model.Account;
import upc.edu.Ejercicio03.model.Transaction;

public interface AccountRepository extends JpaRepository<Account, Long> {
        Account findByNameCustomer(String nameCustomer);
        Boolean existsByNameCustomerAndNumberAccount(String nameCustomer, String numberAccount);
}
