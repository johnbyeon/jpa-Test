package com.jpaTest.jpaTest.entitiy;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long c_id;

    @ManyToOne
    @JoinColumn(name = "p_id")
    private Parent parent;
}
