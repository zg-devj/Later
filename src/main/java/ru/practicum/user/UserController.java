package ru.practicum.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return UserMapper.toUserDto(users);
    }

    @PostMapping
    public UserDto saveNewUser(@RequestBody UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userService.saveUser(user));
    }
}
