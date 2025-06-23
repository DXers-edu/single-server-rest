package com.dxers.single_server_rest.service;

import org.springframework.http.ResponseEntity;

import com.dxers.single_server_rest.dto.request.auth.SignInRequestDto;
import com.dxers.single_server_rest.dto.request.auth.SignUpRequestDto;
import com.dxers.single_server_rest.dto.response.ResponseDto;
import com.dxers.single_server_rest.dto.response.auth.SignInResponseDto;

public interface AuthService {
    ResponseEntity<ResponseDto> signUp (SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);
}
