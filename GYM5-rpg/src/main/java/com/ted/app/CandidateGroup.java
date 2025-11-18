package com.ted.app;

import com.ted.app.role.Role;

import java.util.ArrayList;
import java.util.List;

public class CandidateGroup {

    private List<Role> roles;

    public CandidateGroup() {
        roles = new ArrayList<>();
    }

    public void add(Role role) {
        roles.add(role);
    }


    //========================================================================

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
