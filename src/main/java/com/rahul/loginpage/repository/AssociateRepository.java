package com.rahul.loginpage.repository;

import com.rahul.loginpage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rahul.loginpage.model.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long>{

    Associate findByAssociateIds(String associateIds);
    // User findByAssociateId(String associateId);

}
