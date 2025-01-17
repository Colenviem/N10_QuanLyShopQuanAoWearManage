package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "store_id")
    private String id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String address;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @OneToMany(mappedBy = "store")
    private Set<Employee> employees;
}
