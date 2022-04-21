package com.github.wouterman.cache;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("{id}")
  public User getUser(@PathVariable long id) {
    return userService.getUser(id);
  }

  @PutMapping
  public User createUser() {
    return userService.createUser();
  }

  @PatchMapping("{id}")
  public User updateUser(@PathVariable long id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }

}
