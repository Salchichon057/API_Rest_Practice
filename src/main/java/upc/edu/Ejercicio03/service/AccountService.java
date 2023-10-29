package upc.edu.Ejercicio03.service;
import upc.edu.Ejercicio03.model.Account;
import java.util.List;

public interface AccountService {
    public abstract Account createAccount(Account account);
    public abstract Account getAccountByNameCustomer(String nameCustomer);
    List<Account> getAllAccounts();
}