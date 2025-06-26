package com.mymall.admin_backoffice.domain.member.dto.response;

import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.mymall.admin_backoffice.domain.member.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Long id;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private MemberStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .realName(member.getRealName())
                .phone(member.getPhone())
                .email(member.getEmail())
                .status(member.getStatus())
                .build();
    }

    /*public MemberResponse(Long id, String username, String realName, String phone, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }*/
}
