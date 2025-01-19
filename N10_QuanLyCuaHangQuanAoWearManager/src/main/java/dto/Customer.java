package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "customer_id")
    private String id;
    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String name;
    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    private int point;// isDerived

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @PrePersist
    @PreUpdate
    private void calculatePoint() {
        this.point = orders == null ? 0 :
                orders.stream()
                        .filter(order -> order.getCustomer().getId().equals(this.id))
                        .mapToInt(order -> (int) (order.getTotalAmount() * 0.01))
                        .sum();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "  id = " + id + '\n' +
                "  name = " + name + '\n' +
                "  phone = " + phone + '\n' +
                "  point = " + point +
                '}';
    }
}
