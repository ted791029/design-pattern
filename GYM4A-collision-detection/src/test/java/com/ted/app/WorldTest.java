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

import static org.junit.jupiter.api.Assertions.*;

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
    void givenHasDifferentSprites_WhenCollision_ThenCollide() {
        int[] nums = null;
        //Hero, Fire
        world = Main.initWorld();
        nums = new int[]{0, 5};
        whenCollision(nums);
        thenHerotoFire(nums);

        //Hero, Water
        world = Main.initWorld();
        nums = new int[]{0, 10};
        whenCollision(nums);
        thenHerotoWater(nums);

        //Water, Fire
        world = Main.initWorld();
        nums = new int[] {5, 10};
        whenCollision(nums);
        thenWatertoFire(nums);

        //Fire, Hero
        world = Main.initWorld();
        nums = new int[] {5, 0};
        whenCollision(nums);
        thenFiretoHero(nums);

        //Water, Hero
        world = Main.initWorld();
        nums = new int[] {10, 0};
        whenCollision(nums);
        thenWatertoHero(nums);
    }

    @Test
    void givenHasSameSprites_WhenCollision_ThenMoveFailed() {
        int[] nums = null;
        Sprite c1 = null;
        Sprite c2 = null;

        //Hero, Hero
        world = Main.initWorld();
        nums = new int[] {0, 15};
        whenCollision(nums);
        c1 = world.getSprite(nums[0]);
        c2 = world.getSprite(nums[1]);
        thenMoveFailed(nums, c1, c2);

        //Fire, Fire
        world = Main.initWorld();
        nums = new int[]{5, 20};
        whenCollision(nums);
        c1 = world.getSprite(nums[0]);
        c2 = world.getSprite(nums[1]);
        thenMoveFailed(nums, c1, c2);

        //Water, Water
        world = Main.initWorld();
        nums = new int[]{10, 25};
        whenCollision(nums);
        c1 = world.getSprite(nums[0]);
        c2 = world.getSprite(nums[1]);
        thenMoveFailed(nums, c1, c2);
    }

    private void whenCollision(int[] nums) {
        world.moveSprite(nums[0], nums[1]);
    }

    private void thenHerotoFire(int[] nums) {
        //火移除 & 英雄移到火的位置
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //扣10滴血
        assertTrue(hero.getHp() == 20);
    }

    private void thenFiretoHero(int[] nums) {
        //火移除
        assertNull(world.getSprite(nums[0]));
        //英雄位置不變
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //扣10滴血
        assertTrue(hero.getHp() == 20);
    }

    private void thenHerotoWater(int[] nums) {
        //水移除 & 英雄移到水的位置
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //加10滴血
        assertTrue(hero.getHp() == 40);
    }

    private void thenWatertoHero(int[] nums) {
        //水移除
        assertNull(world.getSprite(nums[0]));
        //英雄位置不變
        Sprite sprite = world.getSprite(nums[1]);
        Hero hero = assertInstanceOf(sprite, Hero.class);
        //加10滴血
        assertTrue(hero.getHp() == 40);
    }

    private void thenWatertoFire(int[] nums) {
        //水移除
        assertNull(world.getSprite(nums[0]));
        //火移除
        assertNull(world.getSprite(nums[1]));
    }

    private void thenMoveFailed(int[] nums, Sprite c1, Sprite c2){
        Sprite sprite1 = world.getSprite(nums[0]);
        Sprite sprite2 = world.getSprite(nums[1]);
        assertEquals(sprite1, c1);
        assertEquals(sprite2, c2);
    }

    private <T> T assertInstanceOf(Object obj, Class<T> expectedType) {
        assertTrue(expectedType.isInstance(obj));
        return expectedType.cast(obj);
    }
}