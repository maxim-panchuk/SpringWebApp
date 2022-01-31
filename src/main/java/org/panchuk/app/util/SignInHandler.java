package org.panchuk.app.util;

import org.panchuk.app.entity.User;
import org.panchuk.app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SignInHandler {
    private final User user;
    private final List<User> userList;
    private final UserServiceImpl userService;

    @Autowired
    public SignInHandler(User user, UserServiceImpl userServiceImpl) {
        this.user = user;
        this.userService = userServiceImpl;
        this.userList = userServiceImpl.getAll();
    }

    public boolean signIn() {
        return user.getPass().equals(userService.getPassword(user.getMail())) && existMail();
    }

    public boolean existMail() {
        for (User us : userList) {
            if (user.getMail().equals(us.getMail()))
                return true;
        }
        return false;
    }

}
