package com.mymall.admin_backoffice.domain.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberSignupRequest;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberUpdateRequest;
import com.mymall.admin_backoffice.domain.member.dto.response.MemberResponse;
import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.mymall.admin_backoffice.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired private MockMvc mockMvc; //가짜 HTTP 요청 날려 컨트롤러 테스트
    @Autowired private ObjectMapper objectMapper; // java 객체 <-> json 변환 도구
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;


}