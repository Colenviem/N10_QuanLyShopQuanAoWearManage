package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "purchase_order_details")
public class PurchaseOrderDetail {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "purchase_order_detail_id")
    private String id;

    private int quantity;

    private double purchasePrice;

    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    @PreUpdate
    private void calculateSubTotal() {
        this.subTotal = this.quantity * this.purchasePrice;
    }
}
