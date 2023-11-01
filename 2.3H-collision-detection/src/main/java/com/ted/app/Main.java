package com.ted.app;

import com.ted.app.ConllisonHandler.*;
import com.ted.app.Sprite.Fire;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Sprite[] sprites = {
                new Hero(0), null, null, null, null,
                new Fire(5), null, null, null, null,
                new Water(10), null, null, null, null,
                new Hero(15), null, null, null, null,
                new Fire(20), null, null, null, null,
                new Water(25), null, null, null, null,
        };

        CollisionHandler handler = new WaterHeroConllisionHandler(
                new WaterFireConllisionHandler(
                        new FireHeroConllisionHandler(
                                new SameSpriteCollisionHandler(null)
                        )
                )
        );

        World world = new World(sprites, handler);
        Scanner scanner = new Scanner(System.in);

        while (true){
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
}
