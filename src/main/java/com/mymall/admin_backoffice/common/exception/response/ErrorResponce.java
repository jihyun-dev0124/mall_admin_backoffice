package com.mymall.admin_backoffice.common.exception.response;

import com.mymall.admin_backoffice.common.exception.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponce {
    private String code;
    private String message;

    public static ErrorResponce from(ErrorCode errorCode) {
        return new ErrorResponce(errorCode.getCode(), errorCode.getMessage());
    }
}
