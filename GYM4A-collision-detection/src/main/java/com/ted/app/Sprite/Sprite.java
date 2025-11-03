package com.ted.app.Sprite;

import com.ted.app.World;

public class Sprite {
    private Coord coord;
    private Character display;

    private World world;

    public Sprite(int x, World world) {
        coord = new Coord(x);
        setWorld(world);
    }

    public void removeFromWord() {
        world.removeSprite(this);
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

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
