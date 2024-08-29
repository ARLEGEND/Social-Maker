package com.api.socialMaker.services;

import com.api.socialMaker.dto.PostRequest;
import com.api.socialMaker.models.Post;
import com.api.socialMaker.models.User;
import com.api.socialMaker.repositories.PostRepository;
import com.api.socialMaker.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;


    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public String createPost(Long userId, PostRequest postRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Post post = new Post(postRequest.getContent(), user);
            postRepository.save(post);
            return "Gönderi başarıyla oluşturuldu.";
        } else {
            return "Kullanıcı bulunamadı.";
        }

    }
}
