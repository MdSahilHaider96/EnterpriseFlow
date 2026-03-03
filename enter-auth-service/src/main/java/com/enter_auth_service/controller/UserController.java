package com.enter_auth_service.controller;

import com.enter_auth_service.modal.User;
import com.enter_auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    // REGSITER
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(userService.register(user));
    }
    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            String message = userService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(message);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Credentials");
        }
    }
    // Find User By ID
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUser(id));
    }
    // Delete the USer BYb ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> UserDeleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("User Deleted Successfully");}
}
