package com.sivalabs.usermanagement.connectors.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaUserPersistence extends JpaUserPersistenceQueries, JpaRepository<JpaUser, Long> {

}