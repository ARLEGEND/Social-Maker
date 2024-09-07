package com.api.socialMaker.services;

import com.api.socialMaker.dto.LoginRequest;
import com.api.socialMaker.dto.UserProfileUpdateRequest;
import com.api.socialMaker.models.User;
import com.api.socialMaker.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> loginUser(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            return userOpt;
        }

        return Optional.empty();
    }

    public void updateUserProfile(Long userId, UserProfileUpdateRequest userProfileUpdateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        user.setFullName(userProfileUpdateRequest.getFullName());
        user.setBio(userProfileUpdateRequest.getBio());
        user.setProfilePictureUrl(userProfileUpdateRequest.getProfilePictureUrl());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
