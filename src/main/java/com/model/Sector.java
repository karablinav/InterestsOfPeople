package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "sector")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(
        name="SEQ_STORE_SECTOR",
        sequenceName="sector_id_seq",
        initialValue = 600,
        allocationSize = 1
)
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE_SECTOR")
    private Long id;

    private String name;

    private Long parentId;
}
