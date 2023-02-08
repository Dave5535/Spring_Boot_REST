package se.lexicon.spring_boot_rest.Repository;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.spring_boot_rest.Model.Entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    List<Role> findAllByOrderByIdDesc();

}
