package com.mymall.admin_backoffice.common.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // -- Member 관련 --
    MEMBER_NOT_FOUND("M001", "회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_USERNAME("M002", "이미 존재하는 아이디입니다.", HttpStatus.CONFLICT),
    PASSWORD_MISMATCH("M003", "현재 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),

    // -- Auth 관련 --
    LOGIN_FAILE("A001", "아이디 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("A002", "로그인이 필요한 요청입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("A003", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // -- ADMIN 관련 --
    ADMIN_ONLY("AD001", "관리자만 사용할 수 있는 기능입니다.", HttpStatus.FORBIDDEN),

    //공통 에러
    INVALID_INPUT("C001", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
