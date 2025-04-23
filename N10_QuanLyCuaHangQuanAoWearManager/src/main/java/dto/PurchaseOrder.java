package dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "purchase_order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATE")
    private LocalDate orderDate;

    private double totalAmount;// isDerived totalAmount = SUM(subTotal)

    @Column(columnDefinition = "BIT")
    private boolean status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ToString.Exclude
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrderDetail> purchaseOrderDetails;

    @PrePersist
    @PreUpdate
    private void calculateTotalAmount() {
        if(this.purchaseOrderDetails != null){
            this.totalAmount = this.purchaseOrderDetails.stream()
                    .filter(purchaseOrderDetail -> purchaseOrderDetail.getPurchaseOrder().getId() == this.id)
                    .mapToDouble(PurchaseOrderDetail::getSubTotal)
                    .sum();
        }
    }
}
