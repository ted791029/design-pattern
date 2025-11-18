package com.ted.app;

import com.ted.app.role.Role;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Troop {

    private String id;

    private List<Role> roles;

    private RPG rpg;

    public Troop(String id, RPG rpg) {
        this.id = id;
        this.rpg = rpg;
        roles = new CopyOnWriteArrayList<>();
    }

    public void add(Role role) {
        roles.add(role);
    }

    public boolean battle() {

        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            role.takeTurn();

            if (role.isDead()) {
                i--;
            }

            if (rpg.isEnd()) {
                return true;
            }

        }

        return false;
    }

    public boolean isAnnihilated() {
        return size() == 0;
    }


    public boolean isDifferent(Role role) {
        Troop troop = role.getTroop();
        return this != troop;
    }

    public boolean isSame(Role role) {
        Troop troop = role.getTroop();
        return isSame(troop);
    }

    private boolean isSame(Troop troop) {
        return this == troop;
    }

    public void remove(Role role) {
        roles.remove(role);
    }

    public int size() {
        return roles.size();
    }

    //========================================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RPG getRpg() {
        return rpg;
    }

    public void setRpg(RPG rpg) {
        this.rpg = rpg;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
