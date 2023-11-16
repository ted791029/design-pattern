package com.ted.app;

import com.ted.app.ConllisonHandler.*;
import com.ted.app.Sprite.Fire;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        World world = initWorld();

        Scanner scanner = new Scanner(System.in);

        int count = 1;

        while (true){
            System.out.printf("第%d次移動\n", count++);
            System.out.println("=========================");
            int[] coords = getCoords(scanner);
            System.out.println("開啟進行移動");
            System.out.println("=========================");
            world.moveSprite(coords[0], coords[1]);
        }
    }

    public static int[] getCoords(Scanner scanner){
        System.out.println("請輸入第一個座標: ");
        int x1 = Integer.parseInt(scanner.nextLine());
        System.out.println("請輸入第二個座標: ");
        int x2 = Integer.parseInt(scanner.nextLine());
        return new int[] {x1, x2};
    }

    public static World initWorld(){
        Sprite[] sprites = {};

        CollisionHandler handler = new HeroWaterCollisionHandler(
                new FireWaterCollisionHandler(
                        new HeroFireCollisionHandler(
                                new SameSpriteCollisionHandler(null)
                        )
                )
        );

        World world = new World(handler);
        initSprites(world);
        return world;
    }

    private static void initSprites(World world){
        world.addSprite(new Hero(0, world));
        world.addSprite(new Fire(5, world));
        world.addSprite(new Water(10, world));
        world.addSprite(new Hero(15, world));
        world.addSprite(new Fire(20, world));
        world.addSprite(new Water(25, world));
    }
}
