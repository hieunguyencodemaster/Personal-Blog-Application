package com.example.Blog.Web.Repository;

import com.example.Blog.Web.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByName(String name);

    @Query(value = "SELECT * FROM post ORDER BY id DESC LIMIT 3", nativeQuery = true)
    List<Post> findLast3Posts();

    @Query("SELECT p FROM Post p WHERE p.name LIKE %:name%")
    List<Post> findByNameContaining(@Param("name") String name);
}
