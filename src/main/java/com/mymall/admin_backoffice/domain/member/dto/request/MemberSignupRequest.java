package com.mymall.admin_backoffice.domain.member.dto.request;

import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.mymall.admin_backoffice.domain.member.enums.MemberStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //json -> dto 변환시 필요 (기본 생성자 없으면 역직렬화 실패)
@AllArgsConstructor
@Builder
public class MemberSignupRequest {
    @NotBlank(message = "아이디는 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "실명은 필수입니다.")
    private String realName;

    @NotBlank(message = "핸드폰 번호는 필수입니다.")
    private String phone;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    public Member toEntity(String encodingPassword) {
        return Member.builder()
                .username(username)
                .password(encodingPassword)
                .realName(realName)
                .phone(phone)
                .email(email)
                .status(MemberStatus.ACTIVE)
                .build();
    }
}
