package com.github.wouterman.cache;

import java.util.stream.LongStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner loadTestData(UserRepository repository) {
    return args -> LongStream
        .range(0, 20)
        .mapToObj(id -> User
            .builder()
            .firstName("firstName" + id)
            .lastName("lastName" + id)
            .build())
        .forEach(repository::save);
  }

}
