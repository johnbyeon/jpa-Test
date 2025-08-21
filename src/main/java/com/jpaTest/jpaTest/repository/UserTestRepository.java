package com.jpaTest.jpaTest.repository;


import com.jpaTest.jpaTest.entitiy.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest,Long> {
}
