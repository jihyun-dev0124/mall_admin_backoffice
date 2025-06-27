package com.mymall.admin_backoffice.domain.member.dto.response;

import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.mymall.admin_backoffice.domain.member.enums.MemberStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
                .createdAt(member.getCreatedAt())
                .build();
    }
}
