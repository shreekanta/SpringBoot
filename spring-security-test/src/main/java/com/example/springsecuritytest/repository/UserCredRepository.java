package com.example.springsecuritytest.repository;

import com.example.springsecuritytest.entity.UserCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserCredRepository extends JpaRepository<UserCred,Integer> {

    Optional<UserCred> findByName(String username);


}
