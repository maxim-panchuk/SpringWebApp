package org.panchuk.app.service;


import org.panchuk.app.entity.User;
import org.panchuk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {
    User addUser(User user);
    List<User> getAll();
    String getPassword (String mail);

    void delete(User user);
    User findByName(String name);
    void editUser(User user);
}
