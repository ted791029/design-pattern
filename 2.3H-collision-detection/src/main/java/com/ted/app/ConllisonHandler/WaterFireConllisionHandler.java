package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Fire;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

public class WaterFireConllisionHandler extends CollisionHandler {
    public WaterFireConllisionHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite c1, Sprite c2) {
        if (c1 instanceof Fire && c2 instanceof Water) {
            return true;
        } else if (c1 instanceof Water && c2 instanceof Fire) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        Sprite[] sprites = initSprites(c1, c2);
        Fire fire = (Fire) sprites[0];
        Water water = (Water) sprites[1];
        fire.removeFromWord();
        water.removeFromWord();
    }

    @Override
    protected Sprite[] initSprites(Sprite c1, Sprite c2) {
        if (c1 instanceof Fire) {
            return new Sprite[]{c1, c2};
        } else {
            return new Sprite[]{c2, c1};
        }
    }
}
