package com.mymall.admin_backoffice.domain.member.service;

import com.mymall.admin_backoffice.common.exception.BusinessException;
import com.mymall.admin_backoffice.common.exception.enums.ErrorCode;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberSignupRequest;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberUpdateRequest;
import com.mymall.admin_backoffice.domain.member.dto.response.MemberResponse;
import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.mymall.admin_backoffice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponse signup(MemberSignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        if(memberRepository.existsByUsername(request.getUsername())){
            throw new BusinessException(ErrorCode.DUPLICATE_USERNAME);
        }
        if(memberRepository.existsByPhone(request.getPhone())){
            throw new BusinessException(ErrorCode.DUPLICATE_USERNAME);
        }
        Member member = request.toEntity(encodedPassword);
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.from(member);
    }

    public MemberResponse updateMember(Long id, MemberUpdateRequest request) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
        findMember.changeMemberInfo(request.getRealName(), request.getPhone(), request.getEmail());

        return MemberResponse.from(findMember);
    }
}
