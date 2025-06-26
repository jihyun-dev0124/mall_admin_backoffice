package com.mymall.admin_backoffice.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberSearchCondition {
    private String username;
    private String realName;
    private String phone;
    private String email;
}
