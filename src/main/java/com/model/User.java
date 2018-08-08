package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "users")
@Entity
@Data
@ToString(exclude = "userInfo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(
        name = "SEQ_STORE_USERS",
        sequenceName = "users_id_seq",
        initialValue = 10,
        allocationSize = 1
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE_USERS")
    private Long id;

    private String name;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    @JsonBackReference
    private UsersInfo userInfo;
}
