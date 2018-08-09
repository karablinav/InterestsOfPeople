package com.dto;

import com.model.Sector;
import com.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private Long id;

    private User user;

    @NotNull
    private boolean checkbox;

    private Set<Sector> sectors = new HashSet<>();
}
