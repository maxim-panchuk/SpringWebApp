package org.panchuk.app.repository;

import org.panchuk.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query("select user from User user where user.name = :name")
    User findByName(@Param("name") String name);

    @Query("select user from User user where user.mail = :mail")
    User getPassword(@Param("mail") String mail);
}

