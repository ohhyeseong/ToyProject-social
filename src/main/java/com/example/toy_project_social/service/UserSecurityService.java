package com.example.toy_project_social.service;

import com.example.toy_project_social.entity.SiteUser;
import com.example.toy_project_social.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final SiteUserRepository siteUserRepository;

    // UserDetailsService의 핵심 메서드.
    // 스프링 시큐리티가 로그인 시 이 메서드를 호출하여 사용자 정보를 조회합니다.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<SiteUser> _user = this.siteUserRepository.findByEmail(email);
        if (_user.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        SiteUser user = _user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 여기서는 모든 사용자에게 "USER" 권한을 부여합니다.
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}