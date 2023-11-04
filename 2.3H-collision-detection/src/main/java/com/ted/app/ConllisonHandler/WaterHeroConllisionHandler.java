package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

public class WaterHeroConllisionHandler extends CollisionHandler {
    public WaterHeroConllisionHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite c1, Sprite c2) {
        if (c1 instanceof Hero && c2 instanceof Water) {
            return true;
        } else if (c1 instanceof Water && c2 instanceof Hero) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        Sprite[] sprites = initSprites(c1, c2);
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

    @Override
    protected Sprite[] initSprites(Sprite c1, Sprite c2) {
        if (c1 instanceof Hero) {
            return new Sprite[]{c1, c2};
        } else {
            return new Sprite[]{c2, c1};
        }
    }
}
