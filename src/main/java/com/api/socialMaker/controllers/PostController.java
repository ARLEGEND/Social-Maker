package com.api.socialMaker.controllers;

import com.api.socialMaker.dto.PostDTO;
import com.api.socialMaker.dto.UserDTO;
import com.api.socialMaker.models.User;
import com.api.socialMaker.services.PostService;
import com.api.socialMaker.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    private UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createPost(@PathVariable Long userId, @RequestBody PostDTO postDTO) {
        String response = postService.createPost(userId, postDTO);
        if (response.equals("Gönderi başarıyla oluşturuldu.")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        // Post'ları dönüştürme işlemini daha sade hale getirdik
        List<PostDTO> postDTOs = user.getPosts().stream()
                .map(post -> new PostDTO(post.getId(), post.getContent(), post.getImageUrl(), post.getCreatedAt(), post.getUpdatedAt()))
                .collect(Collectors.toList());

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .posts(postDTOs)
                .build();

        return ResponseEntity.ok(userDTO);
    }



}