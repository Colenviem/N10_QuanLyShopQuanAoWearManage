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
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String productName;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String description;

    private double price;// isDerived price

    private double discount;

    private int stockQuantity;// isDerived stockQuantity

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String color;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Size size;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrderDetail> purchaseOrderDetails;

    @PrePersist
    @PreUpdate
    private void calculatePriceAndStock() {
        if (purchaseOrderDetails != null) {
            this.price = purchaseOrderDetails.stream()
                    .filter(purchaseOrderDetail -> purchaseOrderDetail.getProduct().getId() == this.id)
                    .mapToDouble(purchaseOrderDetail -> purchaseOrderDetail.getPurchasePrice() * (1 - discount / 100.0)) // Áp dụng chiết khấu đúng cách
                    .sum();
        }

        // Tính số lượng tồn kho khi nhập kho từ purchaseOrderDetails
        if (purchaseOrderDetails != null) {
            int quantityPurchased = purchaseOrderDetails.stream()
                    .filter(purchaseOrderDetail -> purchaseOrderDetail.getProduct().getId() == this.id)
                    .mapToInt(PurchaseOrderDetail::getQuantity)
                    .sum();
            this.stockQuantity += quantityPurchased;
        }
    }
}
