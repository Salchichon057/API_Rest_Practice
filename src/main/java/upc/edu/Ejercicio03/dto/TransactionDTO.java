package upc.edu.Ejercicio03.dto;

import lombok.*;
import upc.edu.Ejercicio03.model.Transaction;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionDTO {

    private String type;
    private Double amount;
    private Double balance;

    public static List<TransactionDTO> of(List<Transaction> transactions) {
        return transactions.stream().map(TransactionDTO::from).collect(Collectors.toList());
    }

    private static TransactionDTO from(Transaction transaction) {
        return new TransactionDTO(
                transaction.getType(),
                transaction.getAmount(),
                transaction.getBalance()
        );
    }
}
