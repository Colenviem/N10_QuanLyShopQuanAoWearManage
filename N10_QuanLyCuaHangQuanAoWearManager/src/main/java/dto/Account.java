package dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "account_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String username;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String password;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "DATE")
    private LocalDate createdDate;

    @ToString.Exclude
    @OneToOne(mappedBy = "account")
    private Employee employee;

    @ToString.Exclude
    @OneToOne(mappedBy = "account")
    private Customer customer;
}
