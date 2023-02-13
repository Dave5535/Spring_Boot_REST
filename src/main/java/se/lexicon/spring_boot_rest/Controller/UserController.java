package se.lexicon.spring_boot_rest.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_boot_rest.Model.dto.UserDto;
import se.lexicon.spring_boot_rest.Service.UserService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;
    //POST  http://localhost:8080/api/v1/user/
    //request body { username: user, password: 123456, rolses: {id: 1, name: ADMIN,...}}
    //@RequestMapping(path = "/", method = RequestMethod.POST)

    @PostMapping("/")
    @Operation(summary = "sing up an account")
    public ResponseEntity<UserDto> signup(@RequestBody @Valid  UserDto dto){
        System.out.println("USERNAME: " + dto.getUsername());
        UserDto serviceResult = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }


    //GET todo http://localhost:8080/swagger-ui/index.html#/
    //   /{username}    search - path variable - GET
    // todo:
    @GetMapping("/{username}")
    @Operation(summary = "find account by username")
    public ResponseEntity<Map<String, Object>> findByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok().body(userService.findByUserName(username));
    }



    //   /{username}    disable user - path variable - PUT
    // todo:
    @PutMapping("/disable")
    @Operation(summary = "disable account by username")
    public ResponseEntity<Void> disableUserByUsername(@RequestParam("username") String username){
        userService.disableUserByUserName(username);
        return ResponseEntity.noContent().build();
    }


}
