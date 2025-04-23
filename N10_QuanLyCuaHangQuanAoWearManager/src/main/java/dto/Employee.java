package dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String fullName;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    private double salary;

    @Column(columnDefinition = "DATE")
    private LocalDate hireDate;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private Set<Order> orders;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private Set<PurchaseOrder> purchaseOrders;
}
