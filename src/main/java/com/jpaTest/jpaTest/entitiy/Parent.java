package com.jpaTest.jpaTest.entitiy;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_id;

    @OneToMany(mappedBy = "parent",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Child> childList = new ArrayList<>();
}

