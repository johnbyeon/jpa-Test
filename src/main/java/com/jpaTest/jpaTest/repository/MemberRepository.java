package com.jpaTest.jpaTest.repository;

import com.jpaTest.jpaTest.dto.MemberProjection;
import com.jpaTest.jpaTest.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
    @Query("SELECT m.name AS memberName, t.teamName AS teamName FROM Member m JOIN m.team t ")
    List<MemberProjection> findProjection();
}
