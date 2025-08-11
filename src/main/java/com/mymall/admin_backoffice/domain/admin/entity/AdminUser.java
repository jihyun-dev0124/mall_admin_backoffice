package com.mymall.admin_backoffice.domain.admin.entity;

import com.mymall.admin_backoffice.domain.admin.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_admin_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class AdminUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole userRole;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static AdminUser create(String username, String encodedPassword, UserRole userRole){
        return builder()
                .username(username)
                .password(encodedPassword)
                .userRole(userRole)
                .build();
    }
}
