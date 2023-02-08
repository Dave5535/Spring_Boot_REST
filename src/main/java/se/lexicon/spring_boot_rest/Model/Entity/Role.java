package se.lexicon.spring_boot_rest.Model.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(updatable = false)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;

    public Role(String name) {
        this.username = name;
    }
}
