package com.ted.app.commands;

import com.ted.app.Telecom;

public class ConnectTelecomCommand implements Command {
    private Telecom telecom;

    public ConnectTelecomCommand(Telecom telecom) {
        setTelecom(telecom);
    }

    @Override
    public void execute() {
        telecom.connect();
    }

    @Override
    public void undo() {
        telecom.disconnect();
    }

    @Override
    public String getName() {
        return "ConnectTelecom";
    }

    public Telecom getTelecom() {
        return telecom;
    }

    public void setTelecom(Telecom telecom) {
        this.telecom = telecom;
    }


}
