package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "employee_id")
    private String id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String fullName;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    private double salary;

    @Column(columnDefinition = "DATE")
    private LocalDate hireDate;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "employee")
    private Set<Order> orders;

    @OneToMany(mappedBy = "employee")
    private Set<PurchaseOrder> purchaseOrders;
}
