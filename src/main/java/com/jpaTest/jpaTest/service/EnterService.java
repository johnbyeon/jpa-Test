package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.entitiy.Entertainment;
import com.jpaTest.jpaTest.entitiy.GirlGroup;
import com.jpaTest.jpaTest.entitiy.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EnterService {
    @Autowired
    EntityManager em;
    public void initDate(){
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
            starship.getGirlGroupList().add(ive);
            yg.getGirlGroupList().add(blackPink);
            ive.getIdolMemberList().add(ahn);
            ive.getIdolMemberList().add(jang);
            blackPink.getIdolMemberList().add(jeny);
            blackPink.getIdolMemberList().add(jisu);

            em.persist(starship);
            em.persist(yg);
    }
}
