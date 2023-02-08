package se.lexicon.spring_boot_rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_boot_rest.Model.dto.RoleDto;
import se.lexicon.spring_boot_rest.Service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    // http://localhost:8080/api/v1/role/
    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAll() {
        //return ResponseEntity.ok(roleService.getAll()); // 200
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(roleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
    }

    @PostMapping("/")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto dto) {
        RoleDto createdRoleDto = roleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoleDto); // 201
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody RoleDto dto) {
        roleService.update(dto);
        return ResponseEntity.noContent().build();
    }


}
