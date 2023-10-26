package com.ted.app.Sprite;

import com.ted.app.Sprite.Sprite;

public class Hero extends Sprite {
    private int hp;

    public Hero(int x){
        super(x);
        super.setDisplay('H');
        setHp(30);
    }

    public boolean isDead(){
        return this.hp <= 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
