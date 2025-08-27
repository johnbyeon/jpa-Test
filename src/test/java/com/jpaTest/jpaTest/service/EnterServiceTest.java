package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.entitiy.IdolMember;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EnterServiceTest {
    @Autowired
    EnterService enterService;

    @Autowired
    EntityManager em;
    @Test
    @DisplayName("엔터만 등록해보기")
    void Enter(){
        enterService.initDate();
    }

}