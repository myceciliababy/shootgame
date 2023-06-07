package com.cecilia;

import java.awt.image.BufferedImage;

/**
 * 子弹
 */
public class Bullet extends FlyingObject {

    private int speed; //速度

    public Bullet(int x, int y) {
        super(8, 14, x, y);
        speed = 3;
    }

    /* 子弹重写了父类的飞行方法 */
    public void move() {
        y -= speed;
    }

    @Override
    public BufferedImage getImage() {
        if (isLife()) {
            return Images.bullet;
        } else if (isDead()) {
            state = REMOVE;
        }
        return null;
    }

    public boolean outOfBounds() {
        return y <= -height;
    }
}
