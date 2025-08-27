package com.jpaTest.jpaTest.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private  String teamId;
    private  String teamName;


    //팀에 소속된 멤버의 리스트 저장
    //연관관계 설정 :Member 클래스의 Team 정보를 가리킴
    @OneToMany(mappedBy = "team",fetch = FetchType.EAGER)
    @Builder.Default
    private List<Member> memberList = new ArrayList<>();

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
