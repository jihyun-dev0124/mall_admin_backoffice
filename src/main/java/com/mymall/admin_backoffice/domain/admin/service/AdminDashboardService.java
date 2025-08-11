package com.mymall.admin_backoffice.domain.admin.service;

import com.mymall.admin_backoffice.domain.admin.repository.AdminDashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {
    private final AdminDashboardRepository adminDashboardRepository;



}
