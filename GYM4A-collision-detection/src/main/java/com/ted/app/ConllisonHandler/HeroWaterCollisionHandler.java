package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

public class HeroWaterCollisionHandler extends CollisionHandler {
    public HeroWaterCollisionHandler(CollisionHandler next) {
        super(next, Hero.class, Water.class);
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        Sprite[] sprites = sortSprites(c1, c2, Hero.class);
        Hero hero = (Hero) sprites[0];
        Water water = (Water) sprites[1];
        //加血
        water.addHp(hero);
        Coord coord = water.getCoord();
        //水移除
        water.removeFromWord();
        if (c1 instanceof Hero) {
            hero.move(coord);
        }
    }
}
