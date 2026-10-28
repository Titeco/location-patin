package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/rent")
    public ResponseEntity<List<UserDto>> getUsersWhoRent() {
        List<UserDto> users = userService.getUsersWithSkates();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{licenceNumber}")
    public ResponseEntity<UserDto> createUser(@PathVariable Integer licenceNumber) {
        UserDto userDto = userService.createUser(licenceNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @DeleteMapping("/{licenceNumber}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer licenceNumber) {
        userService.deleteUser(licenceNumber);
        return ResponseEntity.noContent().build();
    }
}
