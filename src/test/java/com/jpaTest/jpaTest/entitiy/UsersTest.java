package com.jpaTest.jpaTest.entitiy;

import com.jpaTest.jpaTest.dto.Gender;
import com.jpaTest.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersTest {
    @Autowired
    UsersRepository repository;

    @Test
    @DisplayName("새로운 유저 입력하기 테스트")
    void userInputTest() {
        //현재의 레코드수 출력
        System.out.println("데이터 추가 이전 : " + repository.count());
        //빌더를 이용한 클래스 생성
        Users users = Users.builder()
                .name("장원영")
                .email("jang@email.net")
                .gender(Gender.Female)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .likeColor("Pink")
                .build();

        repository.save(users);
        //입력후 레코드 수 출력
        System.out.println("데이터 추가 이후 : " + repository.count());
    }

    @Test
    @DisplayName("전체 레코드 수 출력 id = 2L 인 데이터 존재 확인")
    void UserCountAndExistTest() {
        //전체 레코드 수 출력
        long count = repository.count();
        System.out.println("전체 레코드 수 확인 : " + count);
        //2L 존재하는지 확인
        boolean exist = repository.existsById(2L);
        System.out.println("2번 레코드 존재 확인 : " + exist);

    }

    @Test
    @DisplayName("id=1L 삭제 후 존재 확인 하기")
    @Transactional
    void userDeleteAndExistTest(){
        repository.deleteById(1L);
        boolean exist = repository.existsById(1L);
        System.out.println("1번 레코드 존재 확인 : " + exist);
    }

}