package co.nz.hardwarestore.quickpractical.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "numbered_product")
public class ProductProductOrderEntity {

  @EmbeddedId
  private ProductProductOrderPKEmbeddable id;
  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("product_id")
  @JoinColumn(name = "product_id")
  private ProductEntity product;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("order_id")
  @JoinColumn(name = "order_id")
  private ProductOrderEntity order;
  @Column(name = "quantity", nullable = false)
  private Integer quantity;
}
