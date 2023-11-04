package com.ted.app.ConllisonHandler;

import com.ted.app.Sprite.*;

public abstract class CollisionHandler<T extends Sprite, E extends Sprite>{
    private Class<T> spriteClass1;

    private Class<E> spriteClass2;

    private CollisionHandler next;

    public CollisionHandler(CollisionHandler next, Class<T> spriteClass1, Class<E> spriteClass2) {
        setNext(next);
        this.spriteClass1 = spriteClass1;
        this.spriteClass2 = spriteClass2;
    }

    public void handle(Sprite c1, Sprite c2) {
        if (match(c1, c2)) {
            doHandling(c1, c2);
        } else if (next != null) {
            next.handle(c1, c2);
        }
    }

    protected boolean match(Sprite c1, Sprite c2){
        if (spriteClass1.equals(c1.getClass()) && spriteClass2.equals(c2.getClass())) {
            return true;
        } else if (spriteClass2.equals(c1.getClass()) && spriteClass1.equals(c2.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    protected Sprite[] sortSprites(Sprite c1, Sprite c2, Class<T> spriteClass1){
        if (spriteClass1.equals(c1.getClass())) {
            return new Sprite[]{c1, c2};
        } else {
            return new Sprite[]{c2, c1};
        }
    }

    protected abstract void doHandling(Sprite c1, Sprite c2);

    public CollisionHandler getNext() {
        return next;
    }

    public void setNext(CollisionHandler next) {
        this.next = next;
    }
}
