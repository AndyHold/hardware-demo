package co.nz.hardwarestore.quickpractical.model.entity;

import co.nz.hardwarestore.quickpractical.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Persistence entity to represent a user.<br>
 * Contains data to be persisted regarding a user.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;
//    Note: other data including a hashed password and username/email would be here also.
    @Column(name = "role")
    private Role role;

    public UserDetailsEntity() { /* Empty constructor. For use when setting details later via setters. */ }
}
