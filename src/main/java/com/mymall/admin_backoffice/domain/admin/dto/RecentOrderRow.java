package com.mymall.admin_backoffice.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RecentOrderRow {
    private String orderNo;
    private String memberName;
    private int amount;
    private String status;
    private LocalDateTime createdAt;
}
