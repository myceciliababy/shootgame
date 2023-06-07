package com.cecilia;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * 图片工具类
 */
public class Images {
    public static BufferedImage sky; //天空图片
    public static BufferedImage bullet; //子弹图片
    public static BufferedImage[] heros; //英雄机图片
    public static BufferedImage[] airplanes; //小敌机图片
    public static BufferedImage[] bigairplanes; //大敌机图片
    public static BufferedImage[] bees; //小蜜蜂图片

    public static BufferedImage gameover;
    public static BufferedImage start;
    public static BufferedImage pause;

    static { //初始化图片资源
        sky = loadImage("background.png");
        bullet = loadImage("bullet.png");

        heros = new BufferedImage[2]; //2张图片
        heros[0] = loadImage("hero0.png");
        heros[1] = loadImage("hero1.png");

        airplanes = new BufferedImage[5]; //5张图片
        bigairplanes = new BufferedImage[5];
        bees = new BufferedImage[5];
        airplanes[0] = loadImage("airplane.png");
        bigairplanes[0] = loadImage("bigplane.png");
        bees[0] = loadImage("bee.png");
        for (int i = 1; i < airplanes.length; i++) {
            airplanes[i] = loadImage("airplane_ember" + (i - 1) + ".png");
            bigairplanes[i] = loadImage("bigplane_ember" + (i - 1) + ".png");
            bees[i] = loadImage("bee_ember" + (i - 1) + ".png");
        }

        gameover = loadImage("gameover.png");
        start = loadImage("start.png");
        pause = loadImage("pause.png");
    }

    /**
     * 读取图片
     */
    public static BufferedImage loadImage(String fileName) {
        try {
            BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName)); //在同包中读图片
            return img;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}


























