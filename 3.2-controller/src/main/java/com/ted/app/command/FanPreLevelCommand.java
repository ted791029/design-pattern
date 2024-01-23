package com.ted.app.command;

import com.ted.app.Fan;

public class FanPreLevelCommand implements Command{
    private Fan fan;

    public FanPreLevelCommand(Fan fan) {
        setFan(fan);
    }

    @Override
    public void execute() {
        fan.pre();
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }
}
