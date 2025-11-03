package com.ted.app.Sprite;

import com.ted.app.Sprite.Sprite;
import com.ted.app.World;

public class Water extends Sprite {
    public Water(int x, World world) {
        super(x, world);
        super.setDisplay('W');
    }

    public void addHp(Hero hero) {
        int add = 10;
        hero.setHp(hero.getHp() + add);
        System.out.printf("%s的生命生增加%d點\n", hero.getDisplay(), add);
    }
}
