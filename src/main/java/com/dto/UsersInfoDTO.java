package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.Sector;
import com.model.User;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class UsersInfoDTO {

    private Long id;


    private User user;

    @NotNull
    private boolean checkbox;

    private Set<Sector> sectors = new HashSet<>();

    public UsersInfoDTO(Long id, User user, boolean checkBox, Set<Sector> sectors) {
        this.id = id;
        this.user = user;
        this.checkbox = checkBox;
        this.sectors = sectors;
    }

    public UsersInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCheckBox() {
        return checkbox;
    }

    @JsonProperty(value = "checkbox")
    public void setCheckBox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }
}
