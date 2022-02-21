package co.nz.hardwarestore.quickpractical.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Defines the primary key of the join table for product order and product entities.
 *
 * @author Andrew Holden
 */
@Embeddable
public class ProductProductOrderPKEmbeddable implements Serializable {

  @Column(name = "product_id")
  private Long product_id;

  @Column(name = "order_id")
  private Long order_id;
}
