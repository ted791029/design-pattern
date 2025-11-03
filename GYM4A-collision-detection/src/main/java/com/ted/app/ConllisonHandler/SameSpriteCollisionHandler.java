package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Fire;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

public class SameSpriteCollisionHandler extends CollisionHandler {

    public SameSpriteCollisionHandler(CollisionHandler next) {
        super(next, null, null);
    }

    @Override
    protected boolean match(Sprite c1, Sprite c2){
        if (c1.getClass() == c2.getClass()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        System.out.println("移動失敗");
    }
}
