package com.example.Blog.Web.Repository;

import com.example.Blog.Web.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String hashedPassword);
}
