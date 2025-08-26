package com.jpaTest.jpaTest.service;


import com.jpaTest.jpaTest.entitiy.Member;
import com.jpaTest.jpaTest.entitiy.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RelationTestService {
    //팀과 멤버를 저장하는 코드
    @Autowired
    EntityManager em;

    public void insertMemberAndTeam(){
        Team team = Team.builder()
                .teamName("ive")
                .teamId("아이브")
                .build();
        em.persist(team);

        Member member = Member.builder()
                .memberId("jang")
                .name("장원영")
                .team(team)
                .build();
        em.persist(member);

    }

    public void insertBothRelation(){
        Team ssirum = Team.builder()
                .teamId("씨름")
                .teamName("씨름팀")
                .build();
        em.persist(ssirum);
        Member lee = Member.builder()
                .memberId("lee")
                .name("이만기")
                .team(ssirum)
                .build();
        em.persist(lee);
        Member kang = Member.builder()
                .memberId("kang")
                .name("강호동")
                .team(ssirum)
                .build();
        em.persist(kang);
        ssirum.getMemberList().add(lee);
        ssirum.getMemberList().add(kang);
    }
}
