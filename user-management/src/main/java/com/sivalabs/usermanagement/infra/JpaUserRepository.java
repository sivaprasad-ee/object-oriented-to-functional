package com.sivalabs.usermanagement.infra;

import com.sivalabs.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface JpaUserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
