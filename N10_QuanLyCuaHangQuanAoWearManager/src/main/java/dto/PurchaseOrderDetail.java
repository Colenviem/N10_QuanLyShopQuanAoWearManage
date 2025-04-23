package dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "purchase_order_details")
public class PurchaseOrderDetail implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "purchase_order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    private double purchasePrice;

    private double subTotal;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    @PreUpdate
    private void calculateSubTotal() {
        this.subTotal = this.quantity * this.purchasePrice;
    }
}
