package com.ted.app;

import com.ted.app.ConllisonHandler.CollisionHandler;
import com.ted.app.Sprite.Sprite;

public class World {
    private Sprite[] sprites;
    private CollisionHandler handler;


    public World(Sprite[] sprites, CollisionHandler handler){
        setSprites(sprites);
        setHandler(handler);
    }

    public void moveSprite(int x1, int x2){

    }

    public void removeSprite(Sprite sprite){
        sprites[sprite.getCoord().getX()] = null;
    }

    public Sprite getSprite(int x){
        return sprites[x];
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setSprites(Sprite[] sprites) {
        this.sprites = sprites;
    }

    public CollisionHandler getHandler() {
        return handler;
    }

    public void setHandler(CollisionHandler handler) {
        this.handler = handler;
    }
}
