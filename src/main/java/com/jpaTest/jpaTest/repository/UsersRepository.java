package com.jpaTest.jpaTest.repository;

import com.jpaTest.jpaTest.dto.Gender;
import com.jpaTest.jpaTest.entitiy.Users;
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
}
