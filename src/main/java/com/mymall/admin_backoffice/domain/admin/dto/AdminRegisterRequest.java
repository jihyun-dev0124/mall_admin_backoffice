package com.mymall.admin_backoffice.domain.admin.dto;

import com.mymall.admin_backoffice.domain.admin.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRegisterRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 50)
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    @Size(min = 8, max = 50)
    private String passwordConfirm;

    @NotBlank(message = "권한을 선택해주세요.")
    private String userRole;

    public UserRole toUserRole() {
        return UserRole.valueOf(userRole);
    }
}
