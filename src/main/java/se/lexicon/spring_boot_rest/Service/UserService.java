package se.lexicon.spring_boot_rest.Service;

import se.lexicon.spring_boot_rest.Model.Entity.User;
import se.lexicon.spring_boot_rest.Model.dto.UserDto;


import java.util.List;
import java.util.Map;

public interface UserService {


    Map<String,Object>findByUserName(String username);

    UserDto register(UserDto Dto);

    void update(User Dto);

    void delete(String Name);

    void disableUserByUserName(String username);
}
