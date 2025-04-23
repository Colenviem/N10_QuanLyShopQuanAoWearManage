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
@Table(name = "stores")
public class Store implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String address;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String phone;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @ToString.Exclude
    @OneToMany(mappedBy = "store")
    private Set<Employee> employees;
}
