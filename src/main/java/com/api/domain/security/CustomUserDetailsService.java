package com.api.domain.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //이메일을 이용해 사용자 정보를 DB에서 조회
        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.")
        );

        //User 엔티티를 Spring Security의 UserDetails로 변환하여 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}