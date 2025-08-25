package com.jpaTest.jpaTest.quiz;

import com.jpaTest.jpaTest.dto.Gender;
import com.jpaTest.jpaTest.entitiy.Users;
import com.jpaTest.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import net.minidev.json.JSONUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuizTest {
    @Autowired
    UsersRepository repository;


    @Test
    @DisplayName("Given/When/Then으로 테스트 하기")
    @Transactional
    void assertThatTest() {
        //신규데이터 추가 테스트

        //Given
        Users jin = Users.builder()
                .name("안유진")
                .email("jin@korea.com")
                .gender(Gender.Female)
                .likeColor("Red")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        //When
        repository.save(jin);
        //Then
        //이름으로 검색한 결과와 jin이랑 같으면 성공
        Users result = repository.findByName("안유진").get(0);
        //검사
        Assertions.assertThat(result.getEmail()).isEqualTo(jin.getEmail());
    }

    @Test
    @DisplayName("문제 1. 여성의 이름 중 w또는 m을 포함하는 자료를 검색하시오.")
    void 문제1(){
        //List<Users> usersW = repository.findByGenderAndNameContains(Gender.Female,"w");
        //List<Users> usersM = repository.findByGenderAndNameContains(Gender.Female,"m");
        //List<Users> CombinedUsers = Stream.concat(usersW.stream(), usersM.stream())
        //        .distinct()
        //        .toList();
        //CombinedUsers.forEach(x-> System.out.println(x));
        repository.findByGenderAndNameContainsOrGenderAndNameContains(Gender.Female,"w",Gender.Female,"m")
                .forEach(x-> System.out.println(x));

    }
    @Test
    @DisplayName("문제 2. 이메일에 net을 포함하는 데이터 건수를 출력하시오.")
    void 문제2(){
        Long cnt = repository.findByEmailContains("net")
                .stream().count();
        System.out.println(cnt);
    }

    @Test
    @DisplayName("문제 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 J인 자료를 출력하시오.")
    void 문제3(){
        LocalDateTime date = LocalDateTime.now()
                .minusMonths(1L)
                .toLocalDate()
                .atStartOfDay();
        repository.findByUpdatedAtGreaterThanEqualAndNameStartingWith(date,"J")
                .forEach(x-> System.out.println(x));

    }
    @Test
    @DisplayName("문제 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력하시오.")
    void 문제4(){
        repository.findTop10ByOrderByCreatedAtDesc()
                .forEach(x-> System.out.println(
                                "id : "+x.getId().toString()+
                                ", name : "+x.getName() +
                                ", gender : "+x.getGender().toString() +
                                ", created_at : " + x.getCreatedAt().toString()
                                ));
    }
    @Test
    @DisplayName("문제 5. Red를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력하시오.\n" +
            "(예, apenley2@tripod.com  → apenley2)")
    void 문제5(){
        repository.findByGenderAndLikeColor(Gender.Male,"Red")
                .forEach(x-> System.out.println(" email : "+ x.getEmail().substring(0,x.getEmail().indexOf("@"))));
                ;
    }
    @Test
    @DisplayName("문제 6. 갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.")
    void 문제6(){
        repository.findAll()
                .forEach(x-> System.out.print(x.getCreatedAt().isAfter(x.getUpdatedAt()) ? x.toString()+"\n":""));
    }
    @Test
    @DisplayName("문제 7. 이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.")
    void 문제7(){
        repository.findByGenderAndEmailContainsOrderByCreatedAtDesc(Gender.Female,"edu")
                .forEach(x-> System.out.println(x));
    }
    @Test
    @DisplayName("문제 8. 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력하시오.")
    void 문제8(){
       List<Users> results= repository.findAll(
                Sort.by(Sort.Order.asc("likeColor"),
                        Sort.Order.desc("name"))
        );
                for(int i = 0; i<=20;i++)
                    System.out.println(results.get(i));
    }

    @Test
    @DisplayName("문제 9. 전체 자료를 가장 최근 입력한 자료 순으로 정렬 및 페이징 처리하고 한 페이지당 10건씩 출력하되, 그 중 1번째 페이지를 출력하시오.")
    void 문제9(){
        Sort sort = Sort.by(Sort.Order.desc("updatedAt"));
        Pageable pageable = PageRequest.of(0,10,sort);
        repository.findAll(pageable)
                .getContent()
                .forEach(x-> System.out.println(x));

    }

    @Test
    @DisplayName("문제10. 남성 자료를 ID의 내림차순으로 정렬한 후 한페이당 3건을 출력하되 그 중 2번째 페이지 자료를  출력하시오.")
    void 문제10(){
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable1 = PageRequest.of(1,3,sort);
        repository.findByGender(Gender.Male, pageable1)
                .forEach(x-> System.out.println(x));

    }

    @Test
    @DisplayName("문제11. 지난달의 모든 자료를 검색하여 출력하시오.")
    void 문제11(){

    }


}
