package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.entitiy.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ContextServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ContextService cs;

    @Test
    @DisplayName("1차 캐시 테스트 ")
    void firstCash(){
        Member m = cs.memberInsert();
        System.out.println("=============" + m);
    }
    //
    //@Test
    //@DisplayName("데이터 연속성 보장테스트")
    //void 데이터_연속성_보장테스트(){
    //    Member a_1 = Member.builder()
    //            .memberId("hong")
    //            .name("홍길동").build();
    //    Member b_1 = Member.builder()
    //            .memberId("hong")
    //            .name("홍길동").build();
    //    //Member a_1 = new Member("hong","홍길동");
    //    //Member b_1 = new Member("hong","홍길동");
    //    //Member a = em.find(Member.class,"jang");
    //    //Member b = new Member("jang","장원영");
    //    //System.out.println(a.equals(b));
    //    //System.out.println(a_1.equals(b_1));
    //}
    //
    //@Test
    //@DisplayName("Transaction 쓰기지연 테스트")
    //void transactionTest(){
    //    cs.transactionTest();
    //}
    //
    //@Test
    //@DisplayName("Dirty Checking Test")
    //void dirtyChecking(){
    //    cs.dirtyCheckingTest();
    //}
    //
    //@Test
    //@DisplayName("삭제 테스트")
    //void deleteMember(){
    //    cs.deleteMember();
    //}
}