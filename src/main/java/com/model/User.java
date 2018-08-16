package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
@ToString(exclude = "userInfo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE_USERS")
    @SequenceGenerator(
            name = "SEQ_STORE_USERS",
            sequenceName = "users_id_seq",
            initialValue = 10,
            allocationSize = 1
    )
    private Long id;

    private String name;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    @JsonBackReference
    private UserInfo userInfo;
}
