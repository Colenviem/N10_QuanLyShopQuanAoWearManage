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
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalAmount;// isDerived totalAmount = SUM(subTotal)

    @Column(columnDefinition = "DATE")
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    @PrePersist
    private void calculateTotalAmount() {
        if(this.orderDetails != null){
            this.totalAmount = this.orderDetails.stream()
                    .filter(orderDetail -> orderDetail.getOrder().getId() == this.id)
                    .mapToDouble(OrderDetail::getSubTotal)
                    .sum();
        }else{
            System.out.println("OrderDetails is null");
        }
    }
}
