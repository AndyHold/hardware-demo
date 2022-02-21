package co.nz.hardwarestore.quickpractical.config;

import co.nz.hardwarestore.quickpractical.model.Role;
import co.nz.hardwarestore.quickpractical.model.Tuple;
import co.nz.hardwarestore.quickpractical.model.entity.ProductEntity;
import co.nz.hardwarestore.quickpractical.model.entity.UserDetailsEntity;
import co.nz.hardwarestore.quickpractical.repository.IProductRepository;
import co.nz.hardwarestore.quickpractical.repository.IUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * This class is run at start up.<br>
 * Adds some test users and products to the data persistence.
 *
 * @author Andrew Holden
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private final IUserDetailsRepository userDetailsRepository;
  private final IProductRepository productRepository;

  @Autowired
  public SetupDataLoader(
      IUserDetailsRepository userDetailsRepository, IProductRepository productRepository) {
    this.userDetailsRepository = userDetailsRepository;
    this.productRepository = productRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    UserDetailsEntity testCustomer = new UserDetailsEntity();
    testCustomer.setRole(Role.CUSTOMER);

    UserDetailsEntity testAdministrator = new UserDetailsEntity();
    testAdministrator.setRole(Role.ADMINISTRATOR);

    testCustomer = userDetailsRepository.save(testCustomer);
    System.out.printf("Customer user created with id of %d%n", testCustomer.getId());
    testAdministrator = userDetailsRepository.save(testAdministrator);
    System.out.printf("Administrator user created with id of %d%n", testAdministrator.getId());

    Set<Tuple<String, Double>> products =
        Set.of(
            new Tuple<>("Sledgehammer", 124.95),
            new Tuple<>("Axe", 199.00),
            new Tuple<>("Bandsaw", 1095.00),
            new Tuple<>("Chisel", 15.00),
            new Tuple<>("Hacksaw", 21.95));

    for (Tuple<String, Double> product : products) {
      ProductEntity entity = new ProductEntity();
      entity.setName(product.key());
      entity.setPrice(product.value());

      productRepository.save(entity);
      System.out.printf(
          "%s product created with id of %d and price of %f%n",
          product.key(), entity.getId(), product.value());
    }
  }
}
