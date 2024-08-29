package com.api.socialMaker.controllers;

import com.api.socialMaker.dto.PostRequest;
import com.api.socialMaker.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createPost(@PathVariable Long userId, @RequestBody PostRequest postRequest) {
        String response = postService.createPost(userId, postRequest);
        if (response.equals("Gönderi başarıyla oluşturuldu.")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}