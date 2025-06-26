package com.mymall.admin_backoffice.domain.member.repository;

import com.mymall.admin_backoffice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    boolean existsByUsername(String username);
    boolean existsByPhone(String phone);
}
