package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_id")
    private String id;

    private double totalAmount;// isDerived totalAmount = SUM(subTotal)

    @Column(columnDefinition = "DATE")
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    @PrePersist
    @PreUpdate
    private void calculateTotalAmount() {
        if(this.orderDetails != null){
            this.totalAmount = this.orderDetails.stream()
                    .filter(orderDetail -> orderDetail.getOrder().getId().equals(this.id))
                    .mapToDouble(OrderDetail::getSubTotal)
                    .sum();
        }else{
            System.out.println("OrderDetails is null");
        }
    }
}
