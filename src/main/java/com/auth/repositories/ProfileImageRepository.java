package com.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.models.ProfileImageModel;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImageModel, Long> {

    @Query ( value = "select * from profile_images where user_id = ?1", nativeQuery = true )
    public ProfileImageModel getByUserId (Long id);
    
}
