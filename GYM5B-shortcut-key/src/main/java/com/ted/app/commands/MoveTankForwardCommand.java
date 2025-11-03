package com.ted.app.commands;

import com.ted.app.Tank;

public class MoveTankForwardCommand implements Command{
    private Tank tank;

    public MoveTankForwardCommand(Tank tank) {
        setTank(tank);
    }

    @Override
    public void execute() {
        tank.moveForward();
    }

    @Override
    public void undo() {
        tank.moveBackward();
    }

    @Override
    public String getName() {
        return "MoveTankForward";
    }


    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }
}
