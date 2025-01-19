package dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "category_id")
    private String id;
    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String name;
    @Column(columnDefinition = "VARCHAR(200)", nullable = true)
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "  id = " + id + '\n' +
                "  name = " + name + '\n' +
                "  description = " + description + '\n' +
                '}';
    }
}
