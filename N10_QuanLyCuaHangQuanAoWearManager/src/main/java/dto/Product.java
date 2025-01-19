package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class Product {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    private String id;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrderDetail> purchaseOrderDetails;

    @PrePersist
    @PreUpdate
    private void calculatePriceAndStock() {
        if (purchaseOrderDetails != null) {
            this.price = purchaseOrderDetails.stream()
                    .filter(purchaseOrderDetail -> purchaseOrderDetail.getProduct().getId().equals(this.id))
                    .mapToDouble(purchaseOrderDetail -> purchaseOrderDetail.getPurchasePrice() * (1 - discount / 100.0)) // Áp dụng chiết khấu đúng cách
                    .sum();
        }

        // Kiểm tra nếu orderDetails không null và tính số lượng tồn kho khi xuất kho
//        if (orderDetails != null) {
//            int quantitySold = orderDetails.stream()
//                    .filter(orderDetail -> orderDetail.getProduct().getId().equals(this.id))
//                    .mapToInt(OrderDetail::getQuantity)
//                    .sum();
//            this.stockQuantity -= quantitySold;
//        }

        // Tính số lượng tồn kho khi nhập kho từ purchaseOrderDetails
        if (purchaseOrderDetails != null) {
            int quantityPurchased = purchaseOrderDetails.stream()
                    .filter(purchaseOrderDetail -> purchaseOrderDetail.getProduct().getId().equals(this.id))
                    .mapToInt(PurchaseOrderDetail::getQuantity)
                    .sum();
            this.stockQuantity += quantityPurchased;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "  id='" + id + '\n' +
                "  productName = " + productName + '\n' +
                "  description = " + description + '\n' +
                "  price = " + price + '\n' +
                "  discount = " + discount + '\n' +
                "  stockQuantity = " + stockQuantity + '\n' +
                "  color = " + color + '\n' +
                "  status = " + status + '\n' +
                "  imageUrl = " + imageUrl + '\n' +
                "  size = " + size + '\n' +
                '}';
    }
}
