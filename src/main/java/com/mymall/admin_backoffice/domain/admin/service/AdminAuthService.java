package com.mymall.admin_backoffice.domain.admin.service;

import com.mymall.admin_backoffice.domain.admin.entity.AdminUser;
import com.mymall.admin_backoffice.domain.admin.enums.UserRole;
import com.mymall.admin_backoffice.domain.admin.repository.AdminUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void adminUserRegister(String username, String password, UserRole role) {
        adminUserRepository.findByUsername(username).ifPresent(u -> {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        });

        String encoded = passwordEncoder.encode(password);
        AdminUser user = AdminUser.create(username, encoded, role);
        adminUserRepository.save(user);
    }

}
