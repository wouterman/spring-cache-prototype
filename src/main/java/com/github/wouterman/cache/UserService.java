package com.github.wouterman.cache;

import java.util.List;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Cacheable("users")
  public List<User> getUsers() {
    log.info("Fetching all users.");
    return StreamSupport
        .stream(userRepository.findAll().spliterator(), false)
        .toList();
  }

  public User getUser(long id) {
    log.info("Fetching user with id {}", id);
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Unknown user: " + id));
  }

  @CacheEvict("users")
  public User createUser() {
    log.info("Creating user");
    User user = User
        .builder()
        .firstName("someFirstName")
        .lastName("someLastName")
        .build();
    return userRepository.save(user);
  }


  @Transactional
  @CacheEvict(value = "users", allEntries = true)
  public User updateUser(long id, User user) {
    log.info("Updating user with id {}", id);
    var found = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Unknown user: " + id));
    found.setFirstName(user.getFirstName());
    found.setLastName(user.getLastName());
    return found;
  }
}
