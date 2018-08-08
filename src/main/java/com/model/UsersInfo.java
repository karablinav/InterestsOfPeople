package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users_info")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(
        name = "SEQ_STORE_USERS_INFO",
        sequenceName = "users_info_id_seq",
        initialValue = 10,
        allocationSize = 1
)
public class UsersInfo {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE_USERS_INFO")
    private Long id;

    private boolean checkbox;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_info_sectors",
            joinColumns = {@JoinColumn(name = "user_info_id")},
            inverseJoinColumns = {@JoinColumn(name = "sector_id")})
    private Set<Sector> sectors = new HashSet<>();
}
