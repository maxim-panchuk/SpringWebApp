package org.panchuk.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.panchuk.app.entity.AccessToken;
import org.panchuk.app.entity.Audit;
import org.panchuk.app.entity.User;
import org.panchuk.app.service.impl.AccessTokenServiceImpl;
import org.panchuk.app.service.impl.AuditServiceImpl;
import org.panchuk.app.service.impl.UserServiceImpl;
import org.panchuk.app.util.ActionType;
import org.panchuk.app.util.ExpireTime;
import org.panchuk.app.util.SignInHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping()
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    private final UserServiceImpl userServiceImpl;
    private final AuditServiceImpl auditService;
    private final AccessTokenServiceImpl accessTokenService;

    @Autowired
    public MainController(UserServiceImpl userServiceImpl,
                          AuditServiceImpl auditService,
                          AccessTokenServiceImpl accessTokenService) {
        this.userServiceImpl = userServiceImpl;
        this.auditService = auditService;
        this.accessTokenService = accessTokenService;
    }

    @GetMapping()
    public String index(@ModelAttribute("user") User user) {
        return "home";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute("user") User user) {
        userServiceImpl.addUser(user);
        Audit audit = getAction(user, ActionType.REGISTRATION.get());
        auditService.addAction(audit);
        AccessToken accessToken = getAccessToken(audit, ExpireTime.REGISTRATION_TIME.get());
        accessTokenService.addAccessToken(accessToken);
        logger.info("User signed up");
        return "/home";
    }

    @PostMapping("/sign_in")
    public String signIn(@ModelAttribute("user") User user, Model model) {
        SignInHandler signInHandler = new SignInHandler(user, userServiceImpl);
        AccessToken accessToken;
        if (!signInHandler.existMail()) {
            logger.warn("Email doesn't exist, sign in failed");
            return "sign_in_failed";
        }
        user = userServiceImpl.findByMail(user.getMail());
        Audit audit = getAction(user, ActionType.LOGIN_FAIL.get());
        if (signInHandler.signIn()) {
            model.addAttribute("user", user);
            audit = getAction(user, ActionType.LOGIN_PASS.get());
            auditService.addAction(audit);
            accessToken = getAccessToken(audit, ExpireTime.LOGIN_PASS_TIME.get());
            accessTokenService.addAccessToken(accessToken);
            logger.info("User signed in successfully");
            return "/account";
        }
        auditService.addAction(audit);
        accessToken = getAccessToken(audit, ExpireTime.LOGIN_FAIL_TIME.get());
        accessTokenService.addAccessToken(accessToken);
        logger.warn("Email and password doesn't match");
        return "/sign_in_failed";
    }


    @GetMapping("/confirm/{id}")
    public String confirm(Model model, @PathVariable int id) {
        Optional<User> userOptional = userServiceImpl.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Audit audit = getAction(user, ActionType.CHECK_MAIL.get());
            auditService.addAction(audit);
            AccessToken accessToken = getAccessToken(audit, ExpireTime.CHECK_MAIL_TIME.get());
            accessTokenService.addAccessToken(accessToken);
            model.addAttribute("user", user);
            logger.info("Confirmed successfully");
            return "/account";
        }
        logger.warn("Confirmation failed");
        return "/sign_in_failed";
    }

    @GetMapping("/log_out/{id}")
    public String logOut(Model model, @PathVariable int id) {
        Optional<User> userOptional = userServiceImpl.findById(id);
        if (!userOptional.isPresent())
            return "/home";

        User user = userOptional.get();
        Audit audit = getAction(user, ActionType.LOGOUT.get());
        auditService.addAction(audit);
        AccessToken accessToken = getAccessToken(audit, ExpireTime.LOGOUT_TIME.get());
        accessTokenService.addAccessToken(accessToken);
        model.addAttribute("user", user);
        logger.info("Logged out successfully");
        return "/home";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<User> userOptional = userServiceImpl.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
        }
        return "/home";
    }

    private Audit getAction(User user, int actionType) {
        return Audit.builder().
                user_id(user.getId()).
                action_type(actionType).
                action_date(new Timestamp(System.currentTimeMillis())).
                build();
    }

    private AccessToken getAccessToken(Audit audit, long time) {
        Timestamp expireDate = new Timestamp(audit.getAction_date().getTime() + time);
        return AccessToken.builder().
                audit_id(audit.getId()).
                user_id(audit.getUser_id()).
                expire_date(expireDate).
                build();
    }
}
