package com.example.Saloon.Repository;

import com.example.Saloon.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username= :username")
    User getUserByUsername(@Param("username") String username);

    @Query("select  u from User u where u.email= :email")
    User getUserByEmail(@Param("email") String email);
}
