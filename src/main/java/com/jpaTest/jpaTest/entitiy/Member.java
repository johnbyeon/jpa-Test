package com.jpaTest.jpaTest.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    private String memberId;
    private String name;

    //팀정보를 갖는 연관관계
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", team=" + team.getTeamName() +
                '}';
    }
}
