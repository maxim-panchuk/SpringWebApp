package org.panchuk.app.service.impl;

import org.panchuk.app.entity.User;
import org.panchuk.app.repository.UserRepository;
import org.panchuk.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Override
    public String getPassword(String mail) {
        return userRepository.getPassword(mail).getPass();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByMail(String mail) {
        return userRepository.findByMaili(mail);
    }

    @Override
    public void editUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
