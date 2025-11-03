package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.*;

public class HeroFireCollisionHandler extends CollisionHandler {
    public HeroFireCollisionHandler(CollisionHandler next) {
        super(next, Hero.class, Fire.class);
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        Sprite[] sprites = sortSprites(c1, c2, Hero.class);
        Hero hero = (Hero) sprites[0];
        Fire fire = (Fire) sprites[1];
        //加血
        fire.subHp(hero);
        Coord coord = fire.getCoord();
        //火移除
        fire.removeFromWord();
        if (hero.isDead()) {
            hero.removeFromWord();
        }
        if (c1 instanceof Hero) {
            hero.move(coord);
        }
    }
}
