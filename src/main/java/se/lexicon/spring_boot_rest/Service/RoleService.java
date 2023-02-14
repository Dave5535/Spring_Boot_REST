package se.lexicon.spring_boot_rest.Service;

import se.lexicon.spring_boot_rest.Model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto findById(Integer roleId);

    RoleDto create(RoleDto roleDto);

    void update(RoleDto roleDto);

    void delete(Integer roleId);

}
