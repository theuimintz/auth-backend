package com.auth.repositories;

import com.auth.models.ProfileImageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImageModel, Long> {
    
    @Query ( value = "select * from profile_images where user_id = ?1", nativeQuery = true )
    public ProfileImageModel getByUserId( Long id );
}
