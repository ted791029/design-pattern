package com.ted.app.ConllisonHandler;

import com.ted.app.ConllisonHandler.CollisionHandler;
import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Sprite;

public class FireHeroConllisionHandler extends CollisionHandler {
    public FireHeroConllisionHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite c1, Sprite c2) {
        return false;
    }

    @Override
    protected void doHandling(Coord x1, Coord x2, Sprite[] sprites) {

    }
}
