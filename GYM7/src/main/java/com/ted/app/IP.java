package com.ted.app;

public class IP {

    private String address;

    private boolean isEnable;

    private Long disableTime;

    public IP(String address) {
        this.address = address;
        isEnable = true;
    }

    public boolean checkStatus(){

        if (isEnable){
            return true;
        }

        long now = System.currentTimeMillis();
        long diffMillis = now - disableTime;
        long diffMinute = diffMillis / (1000 * 60);
        boolean isOverTime = diffMinute >= 10;
        return isOverTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Long getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Long disableTime) {
        this.disableTime = disableTime;
    }
}
