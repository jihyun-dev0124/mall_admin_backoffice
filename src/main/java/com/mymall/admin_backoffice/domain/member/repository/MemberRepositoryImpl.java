package com.mymall.admin_backoffice.domain.member.repository;

import com.mymall.admin_backoffice.domain.member.dto.request.MemberSearchCondition;
import com.mymall.admin_backoffice.domain.member.dto.response.MemberResponse;
import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mymall.admin_backoffice.domain.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberResponse> search(MemberSearchCondition condition, Pageable pageable) {
        List<MemberResponse> content = queryFactory
                .select(Projections.fields(MemberResponse.class,
                        member.id,
                        member.username,
                        member.realName,
                        member.phone,
                        member.email,
                        member.status,
                        member.createdAt
                ))
                .from(member)
                .where(
                        usernameEq(condition.getUsername()),
                        realNameEq(condition.getRealName()),
                        phoneEq(condition.getPhone()),
                        emailEq(condition.getEmail())
                )
                .orderBy(member.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.id.count())
                .from(member)
                .where(
                        usernameEq(condition.getUsername()),
                        realNameEq(condition.getRealName()),
                        phoneEq(condition.getPhone()),
                        emailEq(condition.getEmail())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression realNameEq(String realName) {
        return hasText(realName) ? member.realName.eq(realName) : null;
    }

    private BooleanExpression phoneEq(String phone) {
        return hasText(phone) ? member.phone.eq(phone) : null;
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? member.email.eq(email) : null;
    }
}
