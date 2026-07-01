package com.example.myroblox.userservice.controller;

import com.example.myroblox.userservice.dto.UserRequest;
import com.example.myroblox.userservice.dto.UserResponse;
import com.example.myroblox.userservice.mapper.UserMapper;
import com.example.myroblox.userservice.model.User;
import com.example.myroblox.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService service;
    UserMapper mapper;

    public UserController(UserService service,
                          UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest userRequest) {
        User user = mapper.toEntity(userRequest);
        User createdUser = service.create(user);
        return mapper.toResponse(createdUser);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll() {
        List<User> userList = service.findAll();
        return userList.stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable Long id) {
        User user = service.findById(id);
        return mapper.toResponse(user);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse searchByUsername(@RequestParam String username) {
        User user = service.findByUsername(username);
        return mapper.toResponse(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        User user = mapper.toEntity(userRequest);
        User userUpdated = service.update(user, id);
        return mapper.toResponse(userUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
