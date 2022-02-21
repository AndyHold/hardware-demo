package co.nz.hardwarestore.quickpractical.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Persistence entity to represent a product.<br>
 * Contains data to be persisted regarding a product.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "product_name", nullable = false, unique = true)
    private String name;
    @Column(name = "product_price", nullable = false)
    private Double price;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductProductOrderEntity> productOrders;
}
