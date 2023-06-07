package com.cecilia;

import java.awt.image.BufferedImage;

/**
 * 大敌机
 */
public class BigAirplane extends FlyingObject implements Enemy {

    private int speed; //速度

    public BigAirplane() {
        super(69, 95);
        speed = 2;
    }

    /* 大敌机重写了父类的飞行方法 */
    public void move() {
        y += speed;
    }

    int index = 1;

    @Override
    public BufferedImage getImage() {
        if (isLife()) {
            return Images.bigairplanes[0];
        } else if (isDead()) {
            BufferedImage img = Images.bigairplanes[index++ % Images.bigairplanes.length];
            if (index >= Images.bigairplanes.length) {
                state = REMOVE;
            }
            return img;
        }
        return null;
    }

    @Override
    public int getScore() {
        return 2;
    }
}
