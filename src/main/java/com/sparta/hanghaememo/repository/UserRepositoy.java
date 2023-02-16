package com.sparta.hanghaememo.repository;

import com.sparta.hanghaememo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepositoy extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
