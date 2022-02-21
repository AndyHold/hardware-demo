package co.nz.hardwarestore.quickpractical.repository;

import co.nz.hardwarestore.quickpractical.model.entity.UserDetailsEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * An interface to define persistence methods for users.
 *
 * @author Andrew Holden
 */
public interface IUserDetailsRepository extends Repository<UserDetailsEntity, Long> {

  /**
   * Saves or updates a user details entity in the data persistence.
   *
   * @param entity the entity to be saved.
   * @return the entity after it has been saved.
   */
  UserDetailsEntity save(UserDetailsEntity entity);

  /**
   * Finds a user by their unique identifier.
   *
   * @param id the unique identifier of the user.
   * @return an optional object containing the requested user or empty if none exists.
   */
  Optional<UserDetailsEntity> findById(Long id);
}
