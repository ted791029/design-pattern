package com.ted.app.ConllisonHandler;

import com.ted.app.ConllisonHandler.CollisionHandler;
import com.ted.app.Sprite.*;

public class FireHeroConllisionHandler extends CollisionHandler {
    public FireHeroConllisionHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite c1, Sprite c2) {
        if (c1 instanceof Hero && c2 instanceof Fire) {
            return true;
        } else if (c1 instanceof Fire && c2 instanceof Hero) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        Sprite[] sprites = initSprites(c1, c2);
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

    @Override
    protected Sprite[] initSprites(Sprite c1, Sprite c2) {
        if (c1 instanceof Hero) {
            return new Sprite[]{c1, c2};
        } else {
            return new Sprite[]{c2, c1};
        }
    }
}
