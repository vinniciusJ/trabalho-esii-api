package com.project.esii.project_esii.user.repository;

import com.project.esii.project_esii.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("UPDATE User u SET u.isEmailVerified = true WHERE u.id = :userId")
    void verifyUserEmail(@Param("userId") Long userId);
}
