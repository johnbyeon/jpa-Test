package com.jpaTest.jpaTest.repository;

import com.jpaTest.jpaTest.dto.Gender;
import com.jpaTest.jpaTest.entitiy.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {

    /** 쿼리메서드생성
     * 이름으로검색하기
     * Select * from users where name = '장원영'
     * findByName(String searchName)
     * */
    List<Users> findByName(String searchName);

    /** 2. 상위3개 같은 색상 정보 찾기
     * Select * From users where color='pink' limit 3;
     * */
    List<Users> findTop3ByLikeColor(String color);

    /** 3. 성별이 여자이고 좋아하는 색상이 Red인 자료
     * select * from user where gender = 'Female' and like_color= 'Red';
     * */
    List<Users> findByGenderAndLikeColor(Gender gender,String color);

    /** 4. 범위 검색(날짜, 시간)
     * 어제 이후 모든 자료 검색하기
     * select * from users where created_at >= yesterday
     * */
    List<Users> findByCreatedAtAfter(LocalDateTime searchDate);


    /** 시작부터 끝 범위의 데이터 가져오기
     * */
    List<Users> findByCreatedAtBetween(LocalDateTime StartDay,LocalDateTime EndDay);

    /** in 구문에는 List를 인자로 준다
     * */
    List<Users> findByLikeColorIn(List<String> colorList);


    /** 7.아이디가 91번 이상인 자료를 찾아줍니다
     * >=:GraterThanEqual, <=:LessThanEqual
     * >:After, Before
     * */
    List<Users> findByIdGreaterThanEqual(Long id);

    /** 8. 문자열 관련 메서드 함수
     *  StartingWith : 주어진 문자열로 시작하는 데이터
     *  EndingWith : 주어진 문자열로 끝나는 데이터
     *  Contains : 포함된 자료
     *  Like : 사용사 넘겨주는 인자 값 양쪽에 %를 붙여주어야 한다.
     *  8.1 이름이 D로 시작하는 데이터 전체 출력
     * */
    //select * form users where name like 'D%'
    List<Users> findByNameStartingWith(String x);
    //select * form users where name like '%s'
    List<Users> findByNameEndingWith(String x);
    //
    List<Users> findByEmailContains(String x);

    List<Users> findByEmailLike(String x);

    /** 9.정렬
     * select * from users where id between 1 to 10 oder by name desc
     */
    List<Users> findByIdBetweenOrderByNameDesc(Long start,Long end);

    //퀴즈
    //Orange 색상 중 상위 Gender 오름차순, CreatedAt의 내림차순으로 상위 10개 검색
    //Select * From users where LikeColor= 'Orange' order by gender, order by CreatedAt desc limit 10;
    List<Users> findTop10ByLikeColorOrderByGenderAscCreatedAtDesc(String Color);

    //문제 1. 여성의 이름 중 w또는 m을 포함하는 자료를 검색하시오.
    //select * from users where gender='Female' and (name like '%w%' OR name like '%m%');
    //괄호처리를 몰라서 그냥 2번 나눠서 처리함
    //select * from users where gender='Female' and name like '%w%';
    //select * from users where gender='Female' and name like '%m%';
    List<Users> findByGenderAndNameContainsOrGenderAndNameContains(Gender gender1, String nameCharacter1,Gender gender2,String nameCharacter2);

    //문제 2. 이메일에 net을 포함하는 데이터 건수를 출력하시오.
    //select * from users where email like '%net%';
    //List<Users> findByEmailContains(String emailKeyword);

    //문제 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 J인 자료를 출력하시오.
    //select * from users where update_at >=
    List<Users> findByUpdatedAtGreaterThanEqualAndNameStartingWith(LocalDateTime dateTime,String StartingCharacter);

    //문제 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력하시오.
    //
    List<Users> findTop10ByOrderByCreatedAtDesc();


    List<Users> findByGenderAndEmailContainsOrderByCreatedAtDesc(Gender gender,String email);

    List<Users> findByGender(Gender gender, Pageable pageable);
}
