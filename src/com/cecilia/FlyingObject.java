package com.cecilia;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author HuangQiang
 * @version 1.0
 * @createTime 2019/11/13 - 11:27
 */
public abstract class FlyingObject {
    public static final int LIFE = 1;
    public static final int DEAD = 2;
    public static final int REMOVE = 3;

    protected int state = LIFE;
    protected int width; //图片的宽
    protected int height; //图片的高
    protected int x; //x坐标
    protected int y; //y坐标

    /* 敌机使用的构造方法 */
    public FlyingObject(int width, int height) {
        this.width = width;
        this.height = height;
        Random rand = new Random();
        this.x = rand.nextInt(PlayGame.WIDTH - this.width);
        this.y = -this.height;
    }

    /* 天空、英雄机、子弹使用的构造方法 */
    public FlyingObject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    /* 飞行物共有的飞行方法 */
    public abstract void move();

    public void paintObject(Graphics g) {
        g.drawImage(getImage(), x, y, null);
    }

    public abstract BufferedImage getImage();

    public boolean isLife() {
        return state == LIFE;
    }

    public boolean isDead() {
        return state == DEAD;
    }

    public boolean isREMOVE() {
        return state == REMOVE;
    }

    public boolean outOfBounds() {
        return y >= PlayGame.HEIGHT;
    }

    public boolean hit(FlyingObject obj) {
        int x1 = this.x - obj.width;
        int x2 = this.x + this.width;
        int y1 = this.y - obj.height;
        int y2 = this.y + obj.height;
        int x = obj.x;
        int y = obj.y;
        return x1 < x && x < x2 && y1 < y && y < y2;
    }

    public void goDead() {
        state = DEAD;
    }
}
