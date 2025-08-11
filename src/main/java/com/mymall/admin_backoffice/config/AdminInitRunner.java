package com.mymall.admin_backoffice.config;

import com.mymall.admin_backoffice.domain.admin.entity.AdminUser;
import com.mymall.admin_backoffice.domain.admin.enums.UserRole;
import com.mymall.admin_backoffice.domain.admin.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitRunner implements CommandLineRunner {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminUserRepository.findByUsername("admin").isEmpty()) {
            AdminUser au = AdminUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("a1234"))
                    .userRole(UserRole.ROLE_ADMIN)
                    .build();
            adminUserRepository.save(au);
        }
    }
}
