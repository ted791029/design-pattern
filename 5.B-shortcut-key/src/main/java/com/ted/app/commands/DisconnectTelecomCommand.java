package com.ted.app.commands;

import com.ted.app.Telecom;

public class DisconnectTelecomCommand implements Command{
    Telecom telecom;

    public DisconnectTelecomCommand(Telecom telecom) {
        setTelecom(telecom);
    }

    @Override
    public void execute() {
        telecom.disconnect();
    }

    @Override
    public void undo() {
        telecom.connect();
    }

    @Override
    public String getName() {
        return "DisconnectTelecom";
    }

    public Telecom getTelecom() {
        return telecom;
    }

    public void setTelecom(Telecom telecom) {
        this.telecom = telecom;
    }
}
