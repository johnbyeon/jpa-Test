package com.jpaTest.jpaTest.repository;

import com.jpaTest.jpaTest.entitiy.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;

public interface UsersRepository extends JpaRepository<Users,Long> {

}
