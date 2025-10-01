package com.ted.app.commands;

import com.ted.app.Tank;

public class MoveTankBackwardCommand implements Command{
    Tank tank;

    public MoveTankBackwardCommand(Tank tank) {
        setTank(tank);
    }

    @Override
    public void execute() {
        tank.moveBackward();
    }

    @Override
    public void undo() {
        tank.moveForward();
    }

    @Override
    public String getName() {
        return "MoveTankBackward";
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }
}
