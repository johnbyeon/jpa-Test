package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.dto.MemberDto;
import com.jpaTest.jpaTest.dto.MemberProjection;
import com.jpaTest.jpaTest.entitiy.Member;
import com.jpaTest.jpaTest.entitiy.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class QueryServiceTest {
    @Autowired
    QueryService queryService;

    @Test
    @DisplayName("이만기를 ID로 찾기")
    void dynamicQuery(){
        List<Member> members = queryService.dynamicQuery();
        members.forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("팀 전체 가져오기")
    void findAllTeam(){
        List<Team> teamList = queryService.findAllTeam();
        teamList.forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("씨름팀 가져오기")
    void 씨름팀(){
        List<Member> memberList = queryService.findMemberSsirum();
        memberList.forEach(x-> System.out.println(x));
    }
    @Test
    @DisplayName("씨름팀 인원수 구하기")
    void 인원구하기(){
        Long count = queryService.teamCount();
        System.out.println("씨름팀 인원수 : "+ count);
    }

    @Test
    @DisplayName("Dto로 데이터 받기")
    void DTO로받기(){
        List<MemberDto> dtoList = queryService.getMemberDto();
        dtoList.forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("Dto 인터페이스로 데이터 받기 리포지토리 활용")
    void DTO인터페이스로_받기(){
        List<MemberProjection> dtoList = queryService.getProjection();
        dtoList.forEach(x-> System.out.println(x));
    }
}