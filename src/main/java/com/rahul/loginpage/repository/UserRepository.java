package com.rahul.loginpage.repository;

import com.rahul.loginpage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByAssociateId(String associateId);

    User findByEmail(String email);

    User findByVerificationToken(String token);
}
