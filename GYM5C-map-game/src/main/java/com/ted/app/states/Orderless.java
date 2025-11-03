package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.Roles.Role;

import static com.ted.app.Game.rand;

public class Orderless extends State{
    public Orderless(Role role) {
        super(role);
        setDuration(1);
    }


    @Override
    public void action() {
        Role role = getRole();
        role.move();
        role.setStateEffect(true);
    }

    @Override
    public int[] moveTarget(Map map){
        int[] moveTarget = new int[2];
        Role role = getRole();
        int row = role.getRow();
        int col = role.getCol();

        while (true) {
            int chance = rand.nextInt(4); // 0 ~ 3
            switch (chance) {
                case 0 -> {
                    moveTarget[0] = row - 1;
                    moveTarget[1] = col;
                }
                case 1 -> {
                    moveTarget[0] = row + 1;
                    moveTarget[1] = col;
                }
                case 2 -> {
                    moveTarget[0] = row;
                    moveTarget[1] = col - 1;
                }
                case 3 -> {
                    moveTarget[0] = row;
                    moveTarget[1] = col + 1;

                }
            }

            if (!map.isOutSide(moveTarget[0], moveTarget[1])) {
                break;
            }
        }
        role.setStateEffect(true);
        return moveTarget;
    }

    @Override
    public String show() {
        return "混亂";
    }
}
