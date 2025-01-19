package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_detail_id")
    private String id;

    private int quantity;

    private double price;

    private double subTotal;// isDerived subTotal = quantity * price

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    @PreUpdate
    private void calculateSubTotal() {
        this.subTotal = this.quantity * this.price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "  id = " + id + '\n' +
                "  quantity = " + quantity + '\n' +
                "  price = " + price + '\n' +
                "  subTotal = " + subTotal + '\n' +
                '}';
    }
}
