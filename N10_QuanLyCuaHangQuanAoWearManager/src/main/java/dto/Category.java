package dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String name;

    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
