package com.mymall.admin_backoffice.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardStats {
    private int todayOrders;
    private int todayOrdersDelta;
    private int todayRevenue;
    private int newMembers;
    private int totalMembers;
    private int lowStockProducts;
}
