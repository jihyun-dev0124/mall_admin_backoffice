package com.mymall.admin_backoffice.domain.member.repository;

import com.mymall.admin_backoffice.domain.member.dto.request.MemberSearchCondition;
import com.mymall.admin_backoffice.domain.member.dto.response.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

public interface MemberRepositoryCustom {
    Page<MemberResponse> search(MemberSearchCondition memberSearchCondition, Pageable pageable);
}
