package com.ntk.identityuser.dao;

import com.ntk.identityuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
    boolean existsByUsername(String username);
}
