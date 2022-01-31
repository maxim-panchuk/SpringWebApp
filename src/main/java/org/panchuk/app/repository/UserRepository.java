package org.panchuk.app.repository;

import org.panchuk.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.mail = :mail")
    User findByMaili(@Param("mail") String mail);

    @Query("select user from User user where user.mail = :mail")
    User getPassword(@Param("mail") String mail);
}

