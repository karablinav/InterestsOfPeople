package com.dto;

import com.model.UsersInfo;

import javax.validation.constraints.NotNull;

public class UserDTO {

    private Long id;

    @NotNull
    private String name;

    private UsersInfo usersInfo;

    public UserDTO(Long id, String name, UsersInfo usersInfo) {
        this.id = id;
        this.name = name;
        this.usersInfo = usersInfo;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UsersInfo getUsersInfo() {
        return usersInfo;
    }

    public void setUsersInfo(UsersInfo usersInfo) {
        this.usersInfo = usersInfo;
    }
}
