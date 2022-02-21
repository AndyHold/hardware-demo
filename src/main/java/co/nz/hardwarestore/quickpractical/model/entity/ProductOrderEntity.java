package co.nz.hardwarestore.quickpractical.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Persistence entity to represent a product order.<br>
 * Contains data to be persisted regarding a product order.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
@Entity
@Table(name = "product_order")
public class ProductOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetailsEntity user;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<ProductProductOrderEntity> products;
}
