package com.dto;

import com.model.UsersInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    private String name;

    private UsersInfo usersInfo;
}
