package org.panchuk.app.service;


import org.panchuk.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void addUser(User user);
    List<User> getAll();
    String getPassword (String mail);
    Optional<User> findById (long id);
    void delete(User user);
    User findByMail(String mail);
    void editUser(User user);
}
