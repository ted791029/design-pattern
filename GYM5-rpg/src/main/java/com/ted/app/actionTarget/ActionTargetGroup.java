package com.ted.app.actionTarget;

public class ActionTargetGroup {

    private ActionTargetType actionTargetType;

    private int targetCount;

    public ActionTargetGroup(ActionTargetType actionTargetType, int targetCount){
        this.actionTargetType = actionTargetType;
        this.targetCount = targetCount;
    }


    //========================================================================

    public ActionTargetType getActionTargetType() {
        return actionTargetType;
    }

    public void setActionTargetType(ActionTargetType actionTargetType) {
        this.actionTargetType = actionTargetType;
    }

    public int getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(int targetCount) {
        this.targetCount = targetCount;
    }
}
