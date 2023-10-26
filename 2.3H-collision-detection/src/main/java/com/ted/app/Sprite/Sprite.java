package com.ted.app.Sprite;

public class Sprite {
    private Coord coord;
    private Character display;

    public Sprite(int x){
        coord = new Coord(x);
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Character getDisplay() {
        return display;
    }

    public void setDisplay(Character display) {
        this.display = display;
    }
}
