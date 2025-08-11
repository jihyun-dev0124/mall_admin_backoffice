package com.mymall.admin_backoffice.domain.admin.controller;

import com.mymall.admin_backoffice.domain.admin.dto.DashboardStats;
import com.mymall.admin_backoffice.domain.admin.dto.RecentOrderRow;
import com.mymall.admin_backoffice.domain.admin.service.AdminDashboardService;
import com.mymall.admin_backoffice.domain.admin.service.AdminUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {
    private final AdminDashboardService adminDashboardService;

    /**
     * 메인 - 대쉬보드
     * @param user
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal AdminUserDetails user, Model model) {
        DashboardStats stats = new DashboardStats(23, 12, 5_480_000, 17, 100, 3);
        model.addAttribute("stats", stats);

        List<RecentOrderRow> recent = List.of(
                new RecentOrderRow("ORD-20250811-001", "김하나", 12900, "PAID", LocalDateTime.now().minusMinutes(12)),
                new RecentOrderRow("ORD-20250811-002", "둘리", 45000, "PAID", LocalDateTime.now().minusMinutes(35)),
                new RecentOrderRow("ORD-20250811-003", "또치", 20000, "CANCEL", LocalDateTime.now().minusMinutes(2))
        );

        model.addAttribute("recentOrders", recent);

        return "admin/dashboard";
    }
}
