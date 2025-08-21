package com.jpaTest.jpaTest.entitiy;

import com.jpaTest.jpaTest.dto.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name= "like_color")
    private String likeColor;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
