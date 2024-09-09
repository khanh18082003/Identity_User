package com.ntk.identityuser.Configuration;

import com.ntk.identityuser.dao.IUserRepository;
import com.ntk.identityuser.dao.RoleRepository;
import com.ntk.identityuser.entity.User;
import com.ntk.identityuser.enums.Roles;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class ApplicationInitConfig {

  PasswordEncoder passwordEncoder;

  @Bean
  ApplicationRunner applicationRunner(IUserRepository userRepository,
      RoleRepository roleRepository) {
    return args -> {
      System.out.println("Application started");
      if (userRepository.findByUsername("admin").isEmpty()) {
        var roles = roleRepository.findAllById(List.of(Roles.ADMIN.name()));
        roles.stream().forEach(System.out::println);
        User user = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .email("n21dccn134@student.ptithcm.edu.vn")
            .phone("0378277559")
            .roles(new HashSet<>(roles))
            .build();
        userRepository.save(user);
        log.info("Admin user created");
      }
    };
  }
}
