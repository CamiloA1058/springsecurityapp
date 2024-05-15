package com.rangotech.springsecurityapp.persistence.repository;

import com.rangotech.springsecurityapp.persistence.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rangotech.springsecurityapp.persistence.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserStatus(UserStatus userStatus);

    @Modifying
    @Query(value = "UPDATE User SET userStatus = :userStatus WHERE id = :id")
    void changeUserStatus(@Param("userStatus") UserStatus userStatus, @Param("id") Long id);
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findAllByName(@Param("name") String name);
}
