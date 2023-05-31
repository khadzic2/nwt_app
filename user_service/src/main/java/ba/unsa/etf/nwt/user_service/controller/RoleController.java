package ba.unsa.etf.nwt.user_service.controller;

import ba.unsa.etf.nwt.user_service.model.Role;
import ba.unsa.etf.nwt.user_service.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping("/roles")
    ResponseEntity<List<Role>> all() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @PostMapping("/role")
    ResponseEntity<Role> newUser(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.addRole(role),HttpStatus.CREATED);
    }
    @GetMapping("/role/{id}")
    ResponseEntity<Role> one(@PathVariable Integer id) {
        return new ResponseEntity<>(roleService.getRoleById(id),HttpStatus.OK);
    }
}
