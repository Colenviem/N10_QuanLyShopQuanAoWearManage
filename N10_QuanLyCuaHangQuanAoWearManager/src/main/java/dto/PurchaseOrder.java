package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "purchase_order_id")
    private String id;

    @Column(columnDefinition = "DATE")
    private LocalDate orderDate;

    private double totalAmount;// isDerived totalAmount = SUM(subTotal)

    @Column(columnDefinition = "BIT")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrderDetail> purchaseOrderDetails;

    @PrePersist
    @PreUpdate
    private void calculateTotalAmount() {
        if(this.purchaseOrderDetails != null){
            this.totalAmount = this.purchaseOrderDetails.stream()
                    .filter(purchaseOrderDetail -> purchaseOrderDetail.getPurchaseOrder().getId().equals(this.id))
                    .mapToDouble(PurchaseOrderDetail::getSubTotal)
                    .sum();
        }
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "  id='" + id + '\n' +
                "  orderDate=" + orderDate + '\n' +
                "  totalAmount=" + totalAmount + '\n' +
                "  status=" + status + '\n' +
                '}';
    }
}
