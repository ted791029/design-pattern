package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Sprite;

public class SameSpriteCollisionHandler extends CollisionHandler{

    public SameSpriteCollisionHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite c1, Sprite c2) {
        return false;
    }

    @Override
    protected void doHandling(Coord x1, Coord x2) {

    }
}
