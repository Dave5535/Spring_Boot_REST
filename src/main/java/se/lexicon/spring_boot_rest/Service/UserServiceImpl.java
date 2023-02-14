package se.lexicon.spring_boot_rest.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.lexicon.spring_boot_rest.Exception.DataDuplicateException;
import se.lexicon.spring_boot_rest.Exception.DataNotFoundException;
import se.lexicon.spring_boot_rest.Exception.DataWasInsufficient;
import se.lexicon.spring_boot_rest.Model.Entity.Role;
import se.lexicon.spring_boot_rest.Model.Entity.User;
import se.lexicon.spring_boot_rest.Model.dto.RoleDto;
import se.lexicon.spring_boot_rest.Model.dto.UserDto;
import se.lexicon.spring_boot_rest.Repository.RoleRepository;
import se.lexicon.spring_boot_rest.Repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<String, Object> findByUserName(String username) {
        if (username == null) throw new DataWasInsufficient("UserName can't be null");
        User user = userRepository.findByUsername(username).orElseThrow(()-> new DataNotFoundException("Name was not found"));

        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("roles", user.getRoles());
        map.put("expired", user.isExpired());
        return map;
    }

    @Override
    public UserDto register(UserDto userDto) {
        // step1: check the methods params
        if (userDto == null) throw new DataWasInsufficient("Data Was Null");
        if (userDto.getUsername() == null || userDto.getPassword() == null)
            throw new DataWasInsufficient("Name or/and Password Was Null");
        if (userDto.getRoles()== null || userDto.getRoles().size() == 0)
            throw new DataWasInsufficient("Roles can't be null or have a size of zero");
        // step2: check the roles data
        for (RoleDto element: userDto.getRoles())
            roleRepository.findById(element.getId()).orElseThrow(() -> new DataNotFoundException("role id was not valid"));

        // step3: check the username that should not be duplicated
        if (userRepository.existsByUsername(userDto.getUsername()))
            throw new DataDuplicateException("UserName is taken");

        // step4: convert the dto to entity
        User convertedToEntity = modelMapper.map(userDto, User.class);
        // step5: execute the save method of UserRepository
        User createdEntity = userRepository.save(convertedToEntity);
        // step6: convert the created entity to dto
        // step7: return convertedEntity
        return modelMapper.map(createdEntity,UserDto.class);
    }

    @Override
    public void update(User user) {
        if (user== null) throw new DataWasInsufficient("Data can't be null");

        if (!userRepository.findByUsername(user.getUsername()).isPresent())
            throw new DataNotFoundException("Data not found error");

        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new DataDuplicateException("Someone else have that Name");
        roleRepository.save(modelMapper.map(user, Role.class));

    }

    @Override
    public void delete(String name) {
        Optional<User> userDtoOptional = userRepository.findByUsername(name);
       if (userDtoOptional.isPresent()){
           userRepository.delete(userDtoOptional.get());
       } else throw new DataNotFoundException("Name was not valid or found in system");

    }

    @Override

    public void disableUserByUserName(String username) {
if (username == null) throw new DataWasInsufficient("username was Null");
if (!userRepository.existsByUsername(username)) throw new DataNotFoundException("Username Was Not Found");
userRepository.updateExpiredByUsername(username,true);
    }
}
