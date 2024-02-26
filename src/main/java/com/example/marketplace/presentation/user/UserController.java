package com.example.marketplace.presentation.user;

import com.example.marketplace.application.user.UserService;
import com.example.marketplace.presentation.user.dto.commands.CreateUserCommand;
import com.example.marketplace.presentation.user.dto.commands.UpdateUserCommand;
import com.example.marketplace.presentation.user.dto.queries.UserQuery;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserQuery> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserQuery getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserQuery createUser(@RequestBody CreateUserCommand createUserCommand) {
        return userService.createUser(createUserCommand);
    }

    @PutMapping("/{id}")
    public UserQuery updateUser(@PathVariable Integer id, @RequestBody UpdateUserCommand updateUserCommand) {
        return userService.updateUser(id, updateUserCommand);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
