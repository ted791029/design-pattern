package com.ted.app.Sprite;

import com.ted.app.World;

public class Fire extends Sprite {
    public Fire(int x, World world) {
        super(x, world);
        super.setDisplay('F');
    }

    public void subHp(Hero hero) {
        int sub = 10;
        hero.setHp(hero.getHp() - sub);
        System.out.printf("%s的生命生減少%d點\n", hero.getDisplay(), sub);
    }
}
