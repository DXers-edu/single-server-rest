package com.dxers.single_server_rest.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dxers.single_server_rest.provider.JwtProvider;
import com.dxers.single_server_rest.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

        String token = getToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String userEmail = jwtProvider.validate(token);
        if (userEmail == null) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean existUser = userRepository.existsByUserEmail(userEmail);
        if (!existUser) {
            filterChain.doFilter(request, response);
            return;
        }

        setContext(userEmail, request);

        } catch(Exception exception) {
        exception.printStackTrace();
        }

        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        String token = authorization.substring(7);
        return token;

    }

    private void setContext(String userId, HttpServletRequest request) {

        AbstractAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        securityContext.setAuthentication(authenticationToken);

        SecurityContextHolder.setContext(securityContext);

    }
  
}
