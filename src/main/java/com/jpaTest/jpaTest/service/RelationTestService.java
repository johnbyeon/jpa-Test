package com.jpaTest.jpaTest.service;


import com.jpaTest.jpaTest.entitiy.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

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
    public void saveChildren(){
        //자식 생성
        Child c1 = new Child();
        Child c2 = new Child();
        //부모 생성
        Parent p = new Parent();
        //자식에 부모할당
        c1.setParent(p);
        c2.setParent(p);
        //부모의 자식 리스트 만들기
        p.getChildList().add(c1);
        p.getChildList().add(c2);
        em.persist(p);
    }
    public void removeParent(){
        Parent parent = em.find(Parent.class,1L);
        em.remove(parent);
    }

    public void idoll(){
        Entertainment starship = Entertainment.builder()
                .e_id("starship")
                .e_name("스타쉽")
                .build();
        Entertainment yg = Entertainment.builder()
                .e_id("YG")
                .e_name("와이지")
                .build();
        GirlGroup ive = GirlGroup.builder()
                .g_id("ive")
                .g_name("아이브")
                .entertainment(starship)
                .build();
        GirlGroup blackPink = GirlGroup.builder()
                .g_id("blackPink")
                .g_name("블핑")
                .entertainment(yg)
                .build();
        IdolMember ahn = IdolMember.builder()
                .i_id("안유진")
                .i_name("유진")
                .girlGroup(ive)
                .build();
        IdolMember jang = IdolMember.builder()
                .i_id("장원영")
                .i_name("원영")
                .girlGroup(ive)
                .build();
        IdolMember jeny = IdolMember.builder()
                .i_id("제니")
                .i_name("째니")
                .girlGroup(blackPink)
                .build();
        IdolMember jisu = IdolMember.builder()
                .i_id("지수")
                .i_name("지수다")
                .girlGroup(blackPink)
                .build();
    }
}
