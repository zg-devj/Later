package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;


    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
}
