package com.newhrms.userservice.Repository;

import com.newhrms.userservice.Models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserLogin,Long> {
}
