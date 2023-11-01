package com.ted.app;

import com.ted.app.ConllisonHandler.*;
import com.ted.app.Sprite.Fire;
import com.ted.app.Sprite.Hero;
import com.ted.app.Sprite.Sprite;
import com.ted.app.Sprite.Water;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorldTest {

    private World world;
    private Scanner scanner;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        world = null;
        scanner = new Scanner(System.in);
    }

    @Test
    void givenHasTwoCoordWith0And5_WhenCollision_ThenWaterAndFireCollide() {
        //Hero, Fire
        world = initWorld();
        int[] nums = new int[] {0, 5};
        whenCollision(nums);
        thenHerotoFire(nums);

        //Hero, Water
        world = initWorld();
        nums = new int[] {0, 10};
        whenCollision(nums);
        thenHerotoWater(nums);

        //Hero, Water
        world = initWorld();
        nums = new int[] {0, 10};
        whenCollision(nums);
        thenWatertoFire(nums);

        //Fire, Hero
        world = initWorld();
        nums = new int[] {5, 0};
        whenCollision(nums);
        thenHerotoFire(nums);

        //Water, Hero
        world = initWorld();
        nums = new int[] {10, 0};
        whenCollision(nums);
        thenHerotoWater(nums);
    }

    private void whenCollision(int[] nums){
        world.moveSprite(nums[0], nums[1]);
    }

    private void thenHerotoFire(int[] nums){
        //火移除 & 英雄移到火的位置
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //扣10滴血
        assertTrue(hero.getHp() == 20);
    }

    private void thenFiretoHero(int[] nums){
        //火移除
        assertNull(world.getSprite(nums[0]));
        //英雄位置不變
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //扣10滴血
        assertTrue(hero.getHp() == 20);
    }

    private void thenHerotoWater(int[] nums){
        //水移除 & 英雄移到水的位置
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //加10滴血
        assertTrue(hero.getHp() == 40);
    }

    private void thenWatertoHero(int[] nums){
        //水移除
        assertNull(world.getSprite(nums[0]));
        //英雄位置不變
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //加10滴血
        assertTrue(hero.getHp() == 40);
    }

    private void thenWatertoFire(int[] nums){
        //水移除
        assertNull(world.getSprite(nums[0]));
        //火移除
        assertNull(world.getSprite(nums[1]));
    }

    private World initWorld() {
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
        return new World(sprites, handler);
    }

    private <T> T assertInstanceOf(Object obj, Class<T> expectedType) {
        assertTrue(expectedType.isInstance(obj));
        return expectedType.cast(obj);
    }
}