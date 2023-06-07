package com.cecilia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

/**
 * 开始游戏，直接运行该类中的main方法
 */
public class PlayGame extends JPanel {
    public static final int GAME_OVER = 3;
    public static final int RUNNING = 2;
    public static final int PAUSE = 1;
    public static final int START = 0;

    public static int state = START;

    public static final int WIDTH = 400;
    public static final int HEIGHT = 654;

    public static int SCORE = 0;

    Sky sky = new Sky();
    Hero hero = new Hero();
    FlyingObject[] enemies = {}; //封装所有敌人数组
    Bullet[] bullets = {};


    public static void main(String[] args) {
        JFrame frame = new JFrame("Shoot Game");

        PlayGame game = new PlayGame();
        frame.add(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setIconImage(new ImageIcon("images/icon.jpg").getImage());
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.play();
    }

    @Override
    public void paint(Graphics g) {
        sky.paintObject(g);
        hero.paintObject(g);
        switch (state) {
            case RUNNING:
                for (int i = 0; i < enemies.length; i++) {
                    enemies[i].paintObject(g);
                }
                for (int i = 0; i < bullets.length; i++) {
                    bullets[i].paintObject(g);
                }
                g.drawString("SCORE: " + SCORE, 10, 25); //画分
                g.drawString("LIFE: " + hero.getLife(), 10, 45); //画命
                break;
            case START:
                g.drawImage(Images.start, 0, 0, null);
                break;
            case PAUSE:
                g.drawImage(Images.pause, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(Images.gameover, 0, 0, null);
                break;
        }
    }

    public FlyingObject nextOne() {
        Random rand = new Random();
        int num = rand.nextInt(20);
        if (num < 5) {
            return new Bee();
        } else if (num < 12) {
            return new BigAirplane();
        } else {
            return new Airplane();
        }
    }

    int enterIndex = 0;

    public void enterAction() {
        enterIndex++;
        if (enterIndex % 40 == 0) {
            enemies = Arrays.copyOf(enemies, enemies.length + 1);
            enemies[enemies.length - 1] = nextOne();
        }
    }

    int shootIndex = 0;

    public void shootAction() {
        shootIndex++;
        if (shootIndex % 30 == 0) {
            Bullet[] bs = hero.shoot();
            bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
            System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
        }
    }

    public void moveAction() {
        sky.move();
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].move();
        }
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].move();
        }
    }

    public void outOfBoundsAction() {
        int index = 0;
        FlyingObject[] enemiesLives = new FlyingObject[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            if (!enemies[i].outOfBounds()) {
                enemiesLives[index] = enemies[i];
                index++;
            }
        }
        enemies = Arrays.copyOf(enemiesLives, index);

        index = 0;
        Bullet[] bulletsLives = new Bullet[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            if (!bullets[i].outOfBounds()) {
                bulletsLives[index] = bullets[i];
                index++;
            }
        }
        bullets = Arrays.copyOf(bulletsLives, index);
    }

    public void bulletBangAction() {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            for (int j = 0; j < enemies.length; j++) {
                FlyingObject obj = enemies[j];
                if (b.isLife() && obj.isLife() && b.hit(obj)) {
                    b.goDead();
                    obj.goDead();
                    if (obj instanceof Enemy) {
                        SCORE += ((Enemy) obj).getScore();
                    }
                    if (obj instanceof Award) {
                        switch (((Award) obj).getAwardType()) {
                            case Award.LIFE:
                                hero.addLife();
                                break;
                            case Award.DOUBLE_FIRE:
                                hero.addDoubleFire();
                                break;
                        }
                    }
                }
            }
        }
    }

    public void heroBangAction() {
        for (int i = 0; i < enemies.length; i++) {
            FlyingObject o = enemies[i];
            if (o.isLife() && hero.hit(o)) {
                o.goDead();
                hero.subtractLife();
                hero.clearDoubleFire();
            }
        }
    }

    public void checkGameOverAction() {
        if (hero.getLife() == 0) {
            state = GAME_OVER;
            hero = new Hero();
            SCORE = 0;
            enemies = new FlyingObject[0];
            bullets = new Bullet[0];
        }

    }

    public void play() {
        int interval = 10;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) {
                    enterAction();
                    shootAction();
                    moveAction();
                    outOfBoundsAction();
                    bulletBangAction();
                    heroBangAction();
                    checkGameOverAction();
                }
                repaint();
            }
        }, interval, interval);

        MouseAdapter mouse = new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (state == RUNNING) {
                    hero.moveTo(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case START:
                        state = RUNNING;
                        break;
                    case GAME_OVER:
                        state = START;
                        break;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                state = state == PAUSE ? RUNNING : state;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                state = state == RUNNING ? PAUSE : state;
            }
        };
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }
}
