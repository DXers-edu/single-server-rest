package com.dxers.single_server_rest.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxers.single_server_rest.dto.request.auth.SignInRequestDto;
import com.dxers.single_server_rest.dto.request.auth.SignUpRequestDto;
import com.dxers.single_server_rest.dto.response.ResponseDto;
import com.dxers.single_server_rest.dto.response.auth.SignInResponseDto;
import com.dxers.single_server_rest.entity.UserEntity;
import com.dxers.single_server_rest.provider.JwtProvider;
import com.dxers.single_server_rest.repository.UserRepository;
import com.dxers.single_server_rest.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        try {

            boolean isExistUserEmail = userRepository.existsByUserEmail(userEmail);
            if (isExistUserEmail) return ResponseDto.duplicatedEmail();

            String encodedPassword = passwordEncoder.encode(userPassword);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String accessToken = null;

        try {

            String userEmail = dto.getUserEmail();
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) return ResponseDto.signInFailed();

            String userPassword = dto.getUserPassword();
            String encodedPassword = userEntity.getUserPassword();
            boolean isMatch = passwordEncoder.matches(userPassword, encodedPassword);
            if (!isMatch) return ResponseDto.signInFailed();

            accessToken = jwtProvider.create(userEmail);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(accessToken);
        
    }
    
}
