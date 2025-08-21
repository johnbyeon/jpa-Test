package com.jpaTest.jpaTest.repository;

import com.jpaTest.jpaTest.dto.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository repository;

    @Test
    @DisplayName("1. findByName Test")
    void findByNameTest(){
        String name = "Zak Brevetor";
        repository
                .findByName(name)
                .stream()
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("2. pink 상위 3개 가져오기")
    void findTop3ByColorTest(){
        String color = "Pink";
        repository
                .findTop3ByLikeColor(color)
                .forEach(x -> System.out.println(x));

    }

    @Test
    @DisplayName("3. 여자이면서 레드를 좋아하는 사람 찾기")
    void findByGenderAndLikeColor(){
        repository
                .findByGenderAndLikeColor(Gender.Female,"Red")
                .forEach(x-> System.out.println(x));
    }

    @Test
    @DisplayName("4. 범위테스트")
    void findByCreatedAtAfter(){
        repository
                .findByCreatedAtAfter(LocalDateTime.now().minusDays(1L))
                .stream()
                .forEach(x -> System.out.println(x));

    }
}