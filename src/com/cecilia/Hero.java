package com.cecilia;

import java.awt.image.BufferedImage;

/**
 * 英雄机
 */
public class Hero extends FlyingObject {

    private int life; //生命值
    private int doubleFire; //双倍火力值

    public Hero() {
        super(97, 124, 151, 400);
        life = 3;
        doubleFire = 0;
    }

    /* 英雄机随着鼠标x、y坐标的移动而改变自身的x、y坐标 */
    public void moveTo(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    public void move() {
    }

    int index = 0;

    /* 英雄机获取图片时，通过index来获取英雄机的两张图片，显示成飞行状态 */
    @Override
    public BufferedImage getImage() {
        return Images.heros[index++ % Images.heros.length];
    }

    public Bullet[] shoot() {
        if (doubleFire > 0) {
            int x1 = this.x + width / 4;
            int x2 = this.x + width * 3 / 4;
            int y = this.y - 20;
            Bullet[] bs = new Bullet[2];
            bs[0] = new Bullet(x1, y);
            bs[1] = new Bullet(x2, y);
            doubleFire -= 2;
            return bs;
        } else {
            int x = this.x + width / 2;
            int y = this.y - 20;
            Bullet[] bs = new Bullet[1];
            bs[0] = new Bullet(x, y);
            return bs;
        }
    }

    public void addLife() {
        life++;
    }

    public int getLife() {
        return life;
    }

    public void addDoubleFire() {
        doubleFire += 40;
    }

    public void subtractLife() {
        life--;
    }

    public void clearDoubleFire() {
        doubleFire = 0;
    }
}
