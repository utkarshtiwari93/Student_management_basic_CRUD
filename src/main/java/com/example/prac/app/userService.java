package com.example.prac.app;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // ✅ Correct constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================= CREATE =================
    public User createUser(User user) {
        System.out.println(user.getEmail());
        return userRepository.save(user); // DB insert
    }

    // ================= UPDATE =================
    public User updateUser(User user) {
        if (!userRepository.existsById((long) user.getId())) {
            return null;
        }
        return userRepository.save(user); // DB update
    }

    // ================= DELETE =================
    public boolean deleteUser(int id) {
        if (!userRepository.existsById((long) id)) {
            return false;
        }
        userRepository.deleteById((long) id);
        return true;
    }

    // ================= READ ALL =================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ================= READ BY ID =================
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById((long) id);
        return user.orElse(null);
    }
}
