package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "supplier_id")
    private String id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String supplierName;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String address;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    @Column(columnDefinition = "BIT")
    private Boolean status;

    @OneToMany(mappedBy = "supplier")
    private Set<PurchaseOrder> purchaseOrders;
}
