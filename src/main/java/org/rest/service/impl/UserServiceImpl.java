package org.rest.service.impl;

import org.rest.model.User;
import org.rest.repository.hibernate.UserRepositoryImpl;
import org.rest.service.UserService;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    @Override
    public User getById(Integer integer) {
        return userRepository.getById(integer);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);
    }
}
