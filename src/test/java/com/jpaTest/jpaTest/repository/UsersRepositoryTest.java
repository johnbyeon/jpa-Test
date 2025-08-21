package com.jpaTest.jpaTest.repository;

import com.jpaTest.jpaTest.dto.Gender;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        LocalDate yesterDay= LocalDate.now().minusDays(1L);
        LocalDateTime yesterday = yesterDay.atTime(23,59,59);
        repository
                .findByCreatedAtAfter(LocalDateTime.now()
                                        .minusDays(1L)
                                        .toLocalDate()
                                        .atStartOfDay()
                                        .minusSeconds(1))
                .stream()
                .forEach(x -> System.out.println(x));

    }

    @Test
    @DisplayName("최근 한달 자료 찾기(오늘포함)")
    void findByCreatedAtBetween(){
        LocalDate baseDate = LocalDate.now().minusMonths(1L);
        System.out.println(baseDate);
        //LocalDateTime start = baseDate.atTime(0,0,0);
        LocalDateTime start = baseDate.atStartOfDay();
        LocalDateTime end = LocalDateTime.now();
        repository.findByCreatedAtBetween(start,end)
                .forEach(x-> System.out.println(x));


    }

    @Test
    @DisplayName("여러가지 좋아하는 색상 검색하기")
    void findByLikeColorIn(){

        repository.findByLikeColorIn(Lists.newArrayList("Red","Yellow"))
                .forEach(x-> System.out.println(x));
    }
}