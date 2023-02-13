package se.lexicon.spring_boot_rest.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_boot_rest.Model.dto.RoleDto;
import se.lexicon.spring_boot_rest.Service.RoleService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")

@Validated
public class RoleController {
    @Autowired
    RoleService roleService;

    // http://localhost:8080/api/v1/role/

    @Operation(summary = "Get all roles")
    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAll() {
        //return ResponseEntity.ok(roleService.getAll()); // 200
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }
    @Operation(summary = "Get a role by its id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Found the role", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable("id") @Positive @Min(1) Integer id) {

        return ResponseEntity.ok(roleService.findById(id));
    }
    @Operation(summary = "delete a role by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
    }
    @Operation(summary = "create a role / Update whit Id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",description = "created the Role",
            content ={ @Content(mediaType = "application/json", schema = @Schema(name = "Example", implementation = RoleDto.class))}),
    @ApiResponse(responseCode = "400",description = "Invalid request body",content = { @Content})
    })
    @PostMapping("/")
    public ResponseEntity<RoleDto> create(@RequestBody @Valid RoleDto dto) {
        RoleDto createdRoleDto = roleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoleDto); // 201
    }
    @Operation(summary = "update a role by its id")
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid  RoleDto dto) {
        roleService.update(dto);
        return ResponseEntity.noContent().build();
    }


}
