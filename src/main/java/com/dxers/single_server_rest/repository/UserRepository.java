package com.dxers.single_server_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxers.single_server_rest.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    boolean existsByUserEmail(String userEmail);

    UserEntity findByUserEmail(String userEmail);

}
