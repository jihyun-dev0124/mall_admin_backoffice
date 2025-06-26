package com.mymall.admin_backoffice.domain.member.entity;

import com.mymall.admin_backoffice.domain.member.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_admin_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
