package com.example.toy_project_social.service;

import com.example.toy_project_social.entity.SiteUser;
import com.example.toy_project_social.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String email, String password, String name, String phone) {
        if (siteUserRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
        SiteUser user = new SiteUser();
        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        return this.siteUserRepository.save(user);
    }

    public SiteUser getUser(String email) {
        return this.siteUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. email=" + email));
    }
}