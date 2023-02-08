package se.lexicon.spring_boot_rest.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.spring_boot_rest.Model.Entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Modifying
    @Query("update User u set u.expired = :expired where u.username = :username")
    void updateExpiredByUsername(@Param("username") String username, @Param("expired") boolean expired);

    @Modifying
    @Query("update User u set u.password = : newPassword where u.username = : username")
    void resetPassword(@Param("username") String username, @Param("newPassword") String newPassword);
}
