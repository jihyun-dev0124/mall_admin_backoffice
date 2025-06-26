package com.mymall.admin_backoffice.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {
    // 비밀번호 변경 시 필요한 필드
    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    private String currentPassword;
    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    private String newPassword;
}
