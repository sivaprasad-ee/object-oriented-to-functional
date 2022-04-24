package com.sivalabs.usermanagement.infra;

import com.sivalabs.usermanagement.domain.User;
import com.sivalabs.usermanagement.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
