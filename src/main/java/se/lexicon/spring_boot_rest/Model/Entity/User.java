package se.lexicon.spring_boot_rest.Model.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.spring_boot_rest.Exception.DataDuplicateException;
import se.lexicon.spring_boot_rest.Exception.DataNotFoundException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity

public class User {

    @Id
    @Column(updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean expired;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "USERNAME")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    public Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void addRole(Role role) {
        if (roles.contains(role)) {
            throw new DataDuplicateException("Data already exists ");
        }
        roles.add(role);

    }

    public void removeRole(Role role) {
        if (!roles.contains(role)) {
            throw new DataNotFoundException("Data do not exists");
        }
        roles.remove(role);
    }
}
