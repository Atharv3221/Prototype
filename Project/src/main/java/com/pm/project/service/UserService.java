package com.pm.project.service;

import com.pm.project.entity.User;
import com.pm.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final Sinks.Many<Long> userCountSink = Sinks.many().multicast().onBackpressureBuffer();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID doRegistration(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return null;
        }
        userRepository.save(user);
        long numberOfUsers = getNumberOfUsers();
        userCountSink.tryEmitNext(numberOfUsers);
        return user.getId();
    }

    public Flux<Long> streamUserCount() {
        return userCountSink.asFlux();
    }

    public long getNumberOfUsers() {
        return userRepository.count();
    }
}
