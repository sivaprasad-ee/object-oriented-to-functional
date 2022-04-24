package com.sivalabs.usermanagement.domain;

public interface UserRepository  {
    boolean existsByEmail(String email);
    User save(User user);
}
