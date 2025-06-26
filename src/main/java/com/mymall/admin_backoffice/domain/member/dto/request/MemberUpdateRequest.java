package com.mymall.admin_backoffice.domain.member.dto.request;

import com.mymall.admin_backoffice.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequest {
    @NotBlank(message = "실명은 필수입니다.")
    private String realName;
    @NotBlank(message = "핸드폰 번호는 필수입니다.")
    private String phone;
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    public Member toEntity(Long id) {
        return Member.builder()
                .id(id)
                .realName(realName)
                .phone(phone)
                .email(email)
                .build();
    }
}
