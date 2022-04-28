package com.sivalabs.usermanagement.infra.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
