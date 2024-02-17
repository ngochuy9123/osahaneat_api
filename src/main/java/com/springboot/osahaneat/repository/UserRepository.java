package com.springboot.osahaneat.repository;

import com.springboot.osahaneat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);
}
