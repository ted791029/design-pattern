package com.ted.app.command;

import com.ted.app.Fan;

public class FanNextLevelCommand implements Command{
    private Fan fan;

    public FanNextLevelCommand(Fan fan) {
        setFan(fan);
    }

    @Override
    public void execute() {
        fan.next();
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }
}
