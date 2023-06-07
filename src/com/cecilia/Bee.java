package com.cecilia;

import java.awt.image.BufferedImage;

/**
 * 小蜜蜂
 */
public class Bee extends FlyingObject implements Award {

    private int xSpeed; //x坐标的移动速度
    private int ySpeed; //y坐标的移动速度
    private int awardType; //奖励类型

    public Bee() {
        super(60, 50);
        xSpeed = (int) (Math.random() * 2) == 0 ? -1 : 1;
        ySpeed = 2;
        awardType = (int) (Math.random() * 2);
    }

    /* 小蜜蜂重写了父类的飞行方法 */
    public void move() {
        x += xSpeed;
        y += ySpeed;
        if (x <= 0 || x >= PlayGame.WIDTH - width) {
            xSpeed *= -1;
        }
    }

    int index = 1;

    @Override
    public BufferedImage getImage() {
        if (isLife()) {
            return Images.bees[0];
        } else if (isDead()) {
            BufferedImage img = Images.bees[index++ % Images.bees.length];
            if (index >= Images.bees.length) {
                state = REMOVE;
            }
            return img;
        }
        return null;
    }

    @Override
    public int getAwardType() {
        return awardType;
    }
}
