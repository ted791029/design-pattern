package com.ted.app;

import com.ted.app.ConllisonHandler.CollisionHandler;
import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Sprite;

public class World {
    private Sprite[] sprites;
    private CollisionHandler handler;


    public World(CollisionHandler handler){
        sprites = new Sprite[30];
        setHandler(handler);
    }

    public void addSprite(Sprite sprite){
        sprites[sprite.getCoord().getX()] = sprite;
    }

    public void moveSprite(int x1, int x2){
        Sprite c1 = getSprite(x1);
        Sprite c2 = getSprite(x2);
        if(c2 != null){
            handler.handle(c1, c2);
        }
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
