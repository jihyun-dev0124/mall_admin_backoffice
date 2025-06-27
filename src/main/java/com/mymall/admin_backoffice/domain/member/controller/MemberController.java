package com.mymall.admin_backoffice.domain.member.controller;

import com.mymall.admin_backoffice.domain.member.dto.request.MemberSearchCondition;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberSignupRequest;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberUpdateRequest;
import com.mymall.admin_backoffice.domain.member.dto.response.MemberResponse;
import com.mymall.admin_backoffice.domain.member.repository.MemberRepository;
import com.mymall.admin_backoffice.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody MemberSignupRequest request) {
        MemberResponse response = memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 회원 상세 정보 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        MemberResponse response = memberService.getMember(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원 정보 수정
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @Valid @RequestBody MemberUpdateRequest request) {
        MemberResponse response = memberService.updateMember(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Page<MemberResponse> searchMembers(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.search(condition, pageable);
    }

}
