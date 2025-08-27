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
public class Entertainment {
    @Id
    private String e_id;
    private String e_name;

    @OneToMany(mappedBy = "entertainment",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<GirlGroup> girlGroupList = new ArrayList<>();
}
