package se.lexicon.spring_boot_rest.Model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Validated
public class UserDto {
@NotEmpty
@Size(min = 3,max = 50)
    private String username;
@Size(min = 7,max = 50)
    private String password;
@NotNull(message = "Every User should Have at least one Role")
    private List<RoleDto> roles;


}
