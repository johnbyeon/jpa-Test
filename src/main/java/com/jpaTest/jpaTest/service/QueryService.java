package com.jpaTest.jpaTest.service;

import com.jpaTest.jpaTest.dto.MemberDto;
import com.jpaTest.jpaTest.dto.MemberProjection;
import com.jpaTest.jpaTest.entitiy.Member;
import com.jpaTest.jpaTest.entitiy.Team;
import com.jpaTest.jpaTest.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QueryService {
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    public List<Member> dynamicQuery(){
        //jpql 상황에 맞게 사용
        String sql = "SELECT m FROM Member m WHERE m.memberId=:id";
        TypedQuery<Member> query = em.createQuery(sql,Member.class).setParameter("id","lee");
        List<Member> members = query.getResultList();
        return members;
    }

    public List<Team> findAllTeam(){
        String sql = "SELECT t FROM Team t";
        Query query = em.createQuery(sql);
        List<Team> teamList = query.getResultList();
        return teamList;
    }

    public List<Member> findMemberSsirum(){
        String sql = "SELECT m FROM Member m WHERE m.team.teamName LIKE :teamName";
        TypedQuery<Member> query = em.createQuery(sql,Member.class).setParameter("teamName","씨름%");
        List<Member> members = query.getResultList();
        return members;
    }

    public Long teamCount(){
        String sql = "SELECT COUNT(m) FROM Member m "+
                "WHERE m.team.teamName LIKE :teamName";
        Query query = em.createQuery(sql).setParameter("teamName","씨름%");
        return  (Long) query.getSingleResult();
    }
    public List<MemberDto> getMemberDto(){
        String sql = "SELECT NEW " +
                "com.jpaTest.jpaTest.dto.MemberDto(m.name,m.team.teamName)" +
                "FROM Member m";
        TypedQuery<MemberDto> query = em.createQuery(sql, MemberDto.class);
        return query.getResultList();
    }
    public List<MemberProjection> getProjection(){

        return memberRepository.findProjection();
    }

}
