package se.lexicon.spring_boot_rest.Service;


import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.spring_boot_rest.Exception.DataDuplicateException;
import se.lexicon.spring_boot_rest.Exception.DataNotFoundException;
import se.lexicon.spring_boot_rest.Exception.DataWasInsufficient;
import se.lexicon.spring_boot_rest.Model.Entity.Role;
import se.lexicon.spring_boot_rest.Model.dto.RoleDto;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import se.lexicon.spring_boot_rest.Repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roleList = roleRepository.findAllByOrderByIdDesc();
        /*return roleList.stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList());*/
        return modelMapper.map(roleList, new TypeToken<List<RoleDto>>() {
        }.getType());

    }

    @Override
    public RoleDto findById(Integer roleId) {
        if (roleId == null  || roleId == 0) throw new DataWasInsufficient("Id must have a value above zero");
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) throw new DataNotFoundException("Id was not found");
        Role entity = optionalRole.get();
        return modelMapper.map(entity, RoleDto.class);

    }

    @Override
    public RoleDto create(RoleDto roleDto) {

        if (roleDto == null) throw new IllegalArgumentException("Data can't be null");
        if (roleDto.getId() != 0) throw new IllegalArgumentException("Role id should not be null or zero");

        Optional<Role> name = roleRepository.findByName(roleDto.getName());
        if (name.isPresent()) throw new DataDuplicateException("RoleName is Taken");
        Role createdEntity = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(createdEntity, RoleDto.class);
    }

    @Override
    public void update(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("Data can't be null");
        if (roleDto.getId() == 0) throw new IllegalArgumentException("Role id is not supposed to be zero!");

        if (!roleRepository.findById(roleDto.getId()).isPresent())
            throw new DataNotFoundException("Id has no current data attach to it");

        if (roleRepository.findByName(roleDto.getName()).isPresent())
            throw new DataDuplicateException("Role-name already exist");
        roleRepository.save(modelMapper.map(roleDto, Role.class));
    }

    @Override
    public void delete(Integer roleId) {
        RoleDto roleDto = findById(roleId);
        if (roleDto == null) throw new DataNotFoundException("Id was not valid or found in system");
        roleRepository.deleteById(roleId);
    }
}
