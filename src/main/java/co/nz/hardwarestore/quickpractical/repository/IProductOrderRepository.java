package co.nz.hardwarestore.quickpractical.repository;

import co.nz.hardwarestore.quickpractical.model.entity.ProductOrderEntity;
import org.springframework.data.repository.Repository;

/**
 * An interface to define persistence methods for product orders.
 *
 * @author Andrew Holden
 */
public interface IProductOrderRepository extends Repository<ProductOrderEntity, Long> {

    /**
     * Save a product order to the data persistence.
     *
     * @param entity the entity to be saved.
     * @return the entity after being saved.
     */
    ProductOrderEntity save(ProductOrderEntity entity);
}
