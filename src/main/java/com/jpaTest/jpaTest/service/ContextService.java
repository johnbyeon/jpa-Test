package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.entitiy.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //서비스에 걸면 문제가 생길때 되돌아감
public class ContextService {
    @Autowired
    EntityManager em;
    //저장작업 수행
    public Member memberInsert(){

        Member jang = Member.builder()
                .memberId("jang")
                .name("장원영").build();
        //Member jang = new Member("jang","장원영");
        //연속성 공간에 저장하는 명령
        em.persist(jang);

        Member won = em.find(Member.class,"jang");
        return won;
    }

    public void transactionTest(){
        Member ahn = Member.builder()
                .memberId("jin")
                .name("안유진")
                .build();

        Member carina = Member.builder()
                .memberId("carina")
                .name("카리나")
                .build();

        em.persist(ahn);
        em.persist(carina);

        em.flush();

    }
    public void dirtyCheckingTest(){
        //조회
        Member c = em.find(Member.class,"carina");
        //Setter 만으로 수정
        c.setName("까리나에요");
    }

    public void deleteMember(){
        Member jin = em.find(Member.class,"jin");
        em.remove(jin);
    }
}
