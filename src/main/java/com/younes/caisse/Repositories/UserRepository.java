package com.younes.caisse.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.younes.caisse.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
