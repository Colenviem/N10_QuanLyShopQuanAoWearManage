package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_id")
    @EqualsAndHashCode.Include
    private String id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String username;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String password;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "DATE")
    private LocalDate createdDate;

    @OneToOne(mappedBy = "account")
    private Employee employee;

    @OneToOne(mappedBy = "account")
    private Customer customer;
}
