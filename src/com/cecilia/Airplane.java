package com.cecilia;

import java.awt.image.BufferedImage;

/**
 * 小敌机
 */
public class Airplane extends FlyingObject implements Enemy {

    private int speed; //速度

    public Airplane() {
        super(49, 51);
        speed = 3;
    }

    /* 小敌机重写了父类的飞行方法 */
    public void move() {
        y += speed;
    }

    int index = 1;

    @Override
    public BufferedImage getImage() {
        if (isLife()) {
            return Images.airplanes[0];
        } else if (isDead()) {
            BufferedImage img = Images.airplanes[index++ % Images.airplanes.length];
            if (index >= Images.airplanes.length) {
                state = REMOVE;
            }
            return img;
        }
        return null;
    }

    @Override
    public int getScore() {
        return 1;
    }
}
