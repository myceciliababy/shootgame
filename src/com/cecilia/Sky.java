package com.cecilia;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 天空
 */
public class Sky extends FlyingObject {

    private int y2; //图片2的y坐标
    private int speed; //向下移动的速度

    public Sky() {
        super(PlayGame.WIDTH, PlayGame.HEIGHT, 0, 0);
        y2 = -height;
        speed = 1;
    }

    /* 天空重写了父类的方法，该方法用于使天空向下移动 */
    public void move() {
        y += speed;
        y2 += speed;
        if (y == PlayGame.HEIGHT) {
            y = -PlayGame.HEIGHT;
        }
        if (y2 == PlayGame.HEIGHT) {
            y2 = -PlayGame.HEIGHT;
        }
    }

    @Override
    public BufferedImage getImage() {
        return Images.sky;
    }

    public void paintObject(Graphics g) {
        g.drawImage(getImage(), x, y, null);
        g.drawImage(getImage(), x, y2, null);
    }
}
