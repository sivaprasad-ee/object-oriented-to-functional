package com.sivalabs.usermanagement.connectors.userrepository;

interface JpaUserPersistenceQueries {

  boolean existsByEmail(String email);

}
