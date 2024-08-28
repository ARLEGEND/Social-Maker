package com.api.socialMaker.controllers;

import com.api.socialMaker.dto.LoginRequest;
import com.api.socialMaker.models.User;
import com.api.socialMaker.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest);

        if (user.isPresent()) {
            return ResponseEntity.ok("Giriş başarılı : Hoşgeldiniz " + user.get().getUserName());
        } else {
            return ResponseEntity.status(401).body("Giriş başarısız: Hatalı email veya şifre");
        }
    }
}
