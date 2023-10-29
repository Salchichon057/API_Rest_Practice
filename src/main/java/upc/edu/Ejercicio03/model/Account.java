package upc.edu.Ejercicio03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name_customer", length = 30, nullable = false, unique = true)
    private String nameCustomer;
    @Column(name="number_account", length = 13, nullable = false, unique = true)
    private String numberAccount;
}
