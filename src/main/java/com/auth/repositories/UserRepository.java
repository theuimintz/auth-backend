package com.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    @Query ( value = "select * from users where username = ?1", nativeQuery = true )
    public UserModel findByUsername( String username );
}
