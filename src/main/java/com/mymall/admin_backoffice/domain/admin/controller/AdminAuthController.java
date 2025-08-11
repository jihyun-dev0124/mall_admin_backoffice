package com.mymall.admin_backoffice.domain.admin.controller;

import ch.qos.logback.core.model.Model;
import com.mymall.admin_backoffice.domain.admin.dto.AdminRegisterRequest;
import com.mymall.admin_backoffice.domain.admin.service.AdminAuthService;
import com.mymall.admin_backoffice.domain.admin.service.AdminUserDetails;
import com.mymall.admin_backoffice.domain.admin.service.AdminUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {
    public final AdminUserDetailsService adminUserDetailsService;
    private final AdminAuthService adminAuthService;

    /**
     * 로그인
     * @return
     */
    @GetMapping("/login")
    public String loginForm() {
        return "admin/login";
    }

    /**
     * 관리자 등록
     * @param form
     * @return
     */
    @GetMapping("/register")
    public String registerForm(@ModelAttribute("form") AdminRegisterRequest form) {
        return "admin/register";
    }

    /**
     * 관리자 등록
     * @param form
     * @return
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> register(@RequestBody @Valid AdminRegisterRequest form) {
        Map<String, Object> result = new HashMap<>();

        if (form.getUsername() == null || form.getUsername().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "아이디를 입력해주세요.");
            return result;
        }

        if (form.getPassword() == null || form.getPassword().trim().length() < 8) {
            result.put("success", false);
            result.put("message", "비밃번호는 8자 이상으로 입력해 주세요.");
            return result;
        }

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            result.put("success", false);
            result.put("message", "비밀번호가 일치하지 않습니다.");
            return result;
        }

        try{
            adminAuthService.adminUserRegister(form.getUsername(), form.getPassword(), form.toUserRole());
        } catch (IllegalArgumentException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        result.put("messge", "관리자 등록이 완료되었습니다.");
        return result;
    }


}
