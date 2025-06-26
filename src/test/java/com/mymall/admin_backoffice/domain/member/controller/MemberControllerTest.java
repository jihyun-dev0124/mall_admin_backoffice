package com.mymall.admin_backoffice.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberSearchCondition;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberSignupRequest;
import com.mymall.admin_backoffice.domain.member.dto.request.MemberUpdateRequest;
import com.mymall.admin_backoffice.domain.member.entity.Member;
import com.mymall.admin_backoffice.domain.member.enums.MemberStatus;
import com.mymall.admin_backoffice.domain.member.repository.MemberRepository;
import com.mymall.admin_backoffice.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc; //가짜 HTTP 요청 날려 컨트롤러 테스트
    @Autowired private ObjectMapper objectMapper; // java 객체 <-> json 변환 도구
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    public void 회원가입_성공() throws Exception {
        MemberSignupRequest request = MemberSignupRequest.builder()
                .username("member1")
                .password("123qwer!")
                .realName("회원1")
                .phone("01012345678")
                .email("jihyun.dev0124@gmail.com")
                .build();

        mockMvc.perform(post("/api/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("member1"))
                .andExpect(jsonPath("$.realName").value("회원1"));
    }

    @Test
    public void 회원가입_중복_아이디_실패() throws Exception {
        //given
        Member member = Member.builder()
                .username("member1")
                .password("123qwer!")
                .realName("회원1")
                .phone("01012345678")
                .email("jihyun.dev0124@gmail.com")
                .build();
        memberRepository.save(member);

        //when
        MemberSignupRequest request = MemberSignupRequest.builder()
                .username("member1")
                .password("123qwer!")
                .realName("테스트")
                .phone("01011112222")
                .email("jihyun.dev0124@gmail.com")
                .build();

        //then
        mockMvc.perform(post("/api/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("M002"))
                .andExpect(jsonPath("$.message").value("이미 존재하는 아이디입니다."));
    }

    @Test
    public void 회원가입_유효성검사_실패() throws Exception {
        //given
        MemberSignupRequest request = MemberSignupRequest.builder()
                .username("member1")
                .password("123qwer!")
                .realName("테스트")
                .phone("01011112222")
                .email("")
                .build();

        //when & then
        mockMvc.perform(post("/api/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("C001"));
    }

    @WithMockUser(username = "member1", roles = {"USER"}) //인증된 사용자로 요청을 보내도록 도와주는 Spring Security 테스트 지원 애노테이션
    @Test
    public void 회원정보_수정() throws Exception {
        //given
        Member member = Member.builder()
                .username("member1")
                .password("123qwer!")
                .realName("회원1")
                .phone("01012345678")
                .email("jihyun.dev0124@gmail.com")
                .build();
        memberRepository.save(member);

        //when
        MemberUpdateRequest memberInfo = MemberUpdateRequest.builder()
                .realName("회원2")
                .phone("01011112222")
                .email("jihyun.dev0124@gmail.com")
                .build();

        //then
        mockMvc.perform(put("/api/members/"+member.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.realName").value("회원2"))
                .andExpect(jsonPath("$.phone").value("01011112222"));
    }

    @WithMockUser(username = "admin", roles = {"USER"})
    @Test
    public void 전체회원조회() throws Exception {
        saveMember("member1", "encoding", "회원1", "01012345678", "test@gmail.com");
        mockMvc.perform(get("/api/members")
                .param("realName", "회원1")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].username").value("member1"))
                .andExpect(jsonPath("$.content[0].realName").value("회원1"));
    }

    private Long saveMember(String username, String password, String realName, String phone, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .realName(realName)
                .phone(phone)
                .email(email)
                .status(MemberStatus.ACTIVE)
                .build();
        memberRepository.save(member);

        return member.getId();
    }
}