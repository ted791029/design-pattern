package com.ted.app.Sprite;

import com.ted.app.Sprite.Sprite;
import com.ted.app.World;

public class Hero extends Sprite {
    private int hp;

    public Hero(int x, World world) {
        super(x, world);
        super.setDisplay('H');
        setHp(30);
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public void move(Coord coord) {
        if (isDead()) return;
        this.setCoord(coord);
        super.getWorld().addSprite(this);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
