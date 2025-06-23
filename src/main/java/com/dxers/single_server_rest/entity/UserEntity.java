package com.dxers.single_server_rest.entity;

import com.dxers.single_server_rest.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name="user_email", unique=true, nullable=false)
    private String userEmail;
    @Column(name="user_password")
    private String userPassword;

    public UserEntity(SignUpRequestDto dto) {
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
    }
  
}