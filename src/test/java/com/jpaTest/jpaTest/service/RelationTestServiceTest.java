package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.entitiy.Member;
import com.jpaTest.jpaTest.entitiy.Parent;
import com.jpaTest.jpaTest.entitiy.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class RelationTestServiceTest {
    @Autowired
    RelationTestService releationTestService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("MemberAndTeam Insert Test")
    void InsertTest() {
        releationTestService.insertMemberAndTeam();
    }

    @Test
    @DisplayName("단방향 연관관계 설정후 팀이름 찾기")
    void findTeamName() {
        //저장 서비스 호출

        Member jang = em.find(Member.class, "jang");

        System.out.println("팀이름 : " + jang.getTeam().getTeamName());

    }

    @Test
    @DisplayName("양방향 연관관계 설정 후 저장하기")
    void saveBothData() {
        releationTestService.insertBothRelation();

    }

    //@Test
    //@DisplayName("ID:jang의 팀이름 찾기 테스트")
    //void findTeamName(){
    //    Member jang = em.find(Member.class,"jang");
    //    Team team = em.find(Team.class,jang.getTeamId());
    //    System.out.println("Team Name : " + team.getTeamName());
    //}
    @Test
    @DisplayName("씨름팀 멤버 출력")
    void 씨름팀_멤버출력(){
        Team team = em.find(Team.class,"씨름");
        List<Member> members = team.getMemberList();
        for(Member m : members){
            System.out.println(m.getName());
        }
    }

    @Test
    @DisplayName("부모와 자식 동시에 저장되는지 테스트")
    void 부모_자식(){
        releationTestService.saveChildren();
    }

    @Test
    @DisplayName("부모삭제시 자식까지 삭제되는지 테스트")
    void 부모삭제(){
        releationTestService.removeParent();
    }

}