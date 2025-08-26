package com.skala.springbootsample.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skala.springbootsample.model.User;
// import com.sk.skala.myfirstapp.repository.FileUserRepository;

import com.skala.springbootsample.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(Optional<String> name) {
        Collection<User> users = userRepository.findAll();

        if (name.isPresent()) {
            return users.stream()
                    .filter(user -> user.getName().equalsIgnoreCase(name.get()))
                    .toList();
        }
        log.debug("유저 {}", users);
        return new ArrayList<>(users);
    }

    public Optional<User> findByID(long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> update(long id, User updated) {
        try {
            return Optional.of(userRepository.save(updated));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}