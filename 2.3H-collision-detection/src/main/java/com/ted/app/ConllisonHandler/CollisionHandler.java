package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Sprite;

public abstract class CollisionHandler {
    private CollisionHandler next;

    public CollisionHandler(CollisionHandler next){
        setNext(next);
    }

    public void handle(Coord x1, Coord x2, Sprite[] sprites){
        Sprite c1 = sprites[x1.getX()];
        Sprite c2 = sprites[x1.getX()];
        if(match(c1, c2)){
            doHandling(x1, x2, sprites);
        }else if(next != null){
            next.handle(x1, x2, sprites);
        }
    }

    protected abstract boolean match(Sprite c1, Sprite c2);
    protected abstract void doHandling(Coord x1, Coord x2, Sprite[] sprites);

    public CollisionHandler getNext() {
        return next;
    }

    public void setNext(CollisionHandler next) {
        this.next = next;
    }
}
