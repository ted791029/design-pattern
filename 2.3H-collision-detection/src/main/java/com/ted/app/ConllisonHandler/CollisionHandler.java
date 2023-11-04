package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.Coord;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;

public abstract class CollisionHandler {
    private CollisionHandler next;

    public CollisionHandler(CollisionHandler next) {
        setNext(next);
    }

    public void handle(Sprite c1, Sprite c2) {
        if (match(c1, c2)) {
            doHandling(c1, c2);
        } else if (next != null) {
            next.handle(c1, c2);
        }
    }

    protected abstract boolean match(Sprite c1, Sprite c2);

    protected abstract void doHandling(Sprite c1, Sprite c2);

    protected abstract Sprite[] initSprites(Sprite c1, Sprite c2);

    public CollisionHandler getNext() {
        return next;
    }

    public void setNext(CollisionHandler next) {
        this.next = next;
    }
}
