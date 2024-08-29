package com.api.socialMaker.repositories;

import com.api.socialMaker.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
