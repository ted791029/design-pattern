package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Fire;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

public class FireWaterCollisionHandler extends CollisionHandler {
    public FireWaterCollisionHandler(CollisionHandler next) {
        super(next, Fire.class, Water.class);
    }

    @Override
    protected void doHandling(Sprite c1, Sprite c2) {
        Sprite[] sprites = sortSprites(c1, c2, Fire.class);
        Fire fire = (Fire) sprites[0];
        Water water = (Water) sprites[1];
        fire.removeFromWord();
        water.removeFromWord();
    }
}
