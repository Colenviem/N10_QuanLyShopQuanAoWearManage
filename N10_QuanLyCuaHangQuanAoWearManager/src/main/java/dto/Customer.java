package dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    private int point;// isDerived

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @PrePersist
    @PreUpdate
    private void calculatePoint() {
        this.point = orders == null ? 0 :
                orders.stream()
                        .filter(order -> order.getCustomer().getId() == this.id)
                        .mapToInt(order -> (int) (order.getTotalAmount() * 0.01))
                        .sum();
    }
}
