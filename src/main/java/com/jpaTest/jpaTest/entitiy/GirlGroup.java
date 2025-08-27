package com.jpaTest.jpaTest.entitiy;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GirlGroup {
    @Id
    private String g_id;
    private String g_name;


    @OneToMany(mappedBy = "girlGroup",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<IdolMember> idolMemberList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "e_id")
    private Entertainment entertainment;
}
