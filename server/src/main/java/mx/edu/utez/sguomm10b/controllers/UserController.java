package mx.edu.utez.sguomm10b.controllers;

import mx.edu.utez.sguomm10b.models.User.UserDTO;
import mx.edu.utez.sguomm10b.services.User.UserService;
import mx.edu.utez.sguomm10b.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<APIResponse> create(@Valid @RequestBody UserDTO userDTO) {
        return userService.save(userDTO.toEntity());
    }

    @GetMapping("/")
    public ResponseEntity<APIResponse> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}