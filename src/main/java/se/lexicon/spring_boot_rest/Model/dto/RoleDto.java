package se.lexicon.spring_boot_rest.Model.dto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class RoleDto {

    private int id; // 0
   // To get custom message
    //@NotEmpty(message = "name should not be empty")
    //@Size(min = 2, max = 40, message = "name length should be between 2-40")
    @NotNull(message = "Name should not be empty")
    @Size(min = 3,max = 40)
    private String name;

}
