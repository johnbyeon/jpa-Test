package com.jpaTest.jpaTest.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdolMember {
    @Id
    private String i_id;
    private String i_name;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "g_id")
    private GirlGroup girlGroup;
}
