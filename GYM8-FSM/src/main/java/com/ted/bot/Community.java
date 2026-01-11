package com.ted.bot;

import java.util.List;

public interface Community {

    public int totalOnlineUser();

    public List<String> allOnlineUserId();

    public boolean isBroadcasting();

    public void botGoBroadcasting();
    public void botStopBroadcasting();
}
