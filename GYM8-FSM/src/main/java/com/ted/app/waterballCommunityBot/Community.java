package com.ted.app.waterballCommunityBot;

import java.util.List;

public interface Community {
    public List<String> allOnlineMemberId();

    public void botGoBroadcasting();

    public void botStopBroadcasting();

    public boolean isBroadcasting();

    public int totalOnlineMember();
}
