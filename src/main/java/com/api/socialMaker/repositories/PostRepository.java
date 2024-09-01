package com.api.socialMaker.repositories;

import com.api.socialMaker.models.Post;
import com.api.socialMaker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    List<Post> findByUserId(Long userId);
}
