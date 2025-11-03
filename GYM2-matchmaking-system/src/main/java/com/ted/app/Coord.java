package com.ted.app;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int distance(Coord target){
        return (int)Math.pow((double)target.x - this.x, 2) + (int)Math.pow((double)target.y - this.y, 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
