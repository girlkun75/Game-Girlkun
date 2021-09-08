/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameState;

import com.lai.GameGui.Game;
import com.lai.GameEffect.*;
import com.lai.Manager.*;
import com.lai.Object.*;
import com.lai.Object.Map.*;
import com.lai.Object.Mobs.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author GirlkuN
 */
public class GameWorldState extends GameState {

    private Map map;
    private MapBG mapBG;

    public Camera camera;
    public boolean lookCamera;

    public MobManager mobManager;
    public RangeDameManager rangeDameManager;

    BufferedImage pause;

    FrameImage infoBar, hpbar, mpbar;

    public GameWorldState(GameStateManager gsm) {
        this.gsm = gsm;
        gsm.gameOver = false;
        init();
    }

    public Map getMap() {
        return map;
    }

    //--------------------------------------------------------------------------
    @Override
    public void init() {
        map = new Map(this);
        mapBG = new MapBG(this);
        camera = new Camera(this);

        pause = GameData.getInstance().getFrameImage("menu11").getImage();

        mobManager = new MobManager(this);
        rangeDameManager = new RangeDameManager(this);
        initObject();

        infoBar = GameData.getInstance().getFrameImage("hp_mp_bar");
        hpbar = GameData.getInstance().getFrameImage("hp_bar");
        mpbar = GameData.getInstance().getFrameImage("mp_bar");
    }

    private void initObject() {
        //MainCharacter(int x, int y, int width, int height, float mass, int hp, int mp, int dame, GameWorldState gameWorld)
        //9263.5/111
        //7300
        //12148
        // 16895, 463
        Mob mainChar = new MainCharacter(227, 1287, 0.05f, 200, 100, 0, this);
        mobManager.add(mainChar);

        //---mob---
//        Mob mob = null;
//        mob = new Cat(465, 431, this, 460, 592, 0, 0);
//        mobManager.add(mob);
//        mob = new Cat(365, 495, this, 363, 403, 0, 0);
//        mobManager.add(mob);
//        mob = new Bogey(850, 271, this, 843, 1198, 0, 0);
//        mobManager.add(mob);
//        mob = new SnakeMonster(850, 271, this, 843, 1198, 0, 0);
//        mobManager.add(mob);
//
//        mob = new EyeMonster(900, 200, this, 857, 1196, 200, 271);
//        mobManager.add(mob);
//        mob = new BringerOfDeath(9500, 111, 40, 60, this, 9272, 9671, 200, 271);
//        mobManager.add(mob);
//        mob = new Death(9500, 111, this, 9417, 9671, 200, 271);
//        mobManager.add(mob);
//
//        mob = new Worm(7350, 495, this, 7340, 7500, 0, 271);
//        mobManager.add(mob);
//
//        mob = new FireOfDeath(12400, 111, this, 12379, 12700, 0, 271);
//        mobManager.add(mob);
        //--monster--
        //cat
        mobManager.add(new Cat(660, 1391, this, 656, 782, 0, 0));
        mobManager.add(new Cat(890, 1519, this, 880, 1007, 0, 0));
        mobManager.add(new Cat(455, 1295, this, 453, 534, 0, 0));
        mobManager.add(new Cat(455, 1103, this, 453, 534, 0, 0));

        mobManager.add(new Cat(1690, 655, this, 1673, 1814, 0, 0));
        mobManager.add(new Cat(1900, 687, this, 1840, 1915, 0, 0));
        mobManager.add(new Cat(1500, 687, this, 1426, 1648, 0, 0));

        //bogey
        mobManager.add(new Bogey(400, 431, this, 398, 622, 0, 0));
        mobManager.add(new Bogey(500, 431, this, 398, 622, 0, 0));
        mobManager.add(new Bogey(855, 463, this, 845, 979, 0, 0));

        mobManager.add(new Bogey(2600, 911, this, 2583, 2871, 0, 0));
        mobManager.add(new Bogey(2700, 911, this, 2583, 2871, 0, 0));
        mobManager.add(new Bogey(2800, 911, this, 2583, 2871, 0, 0));

        mobManager.add(new Bogey(1900, 239, this, 1838, 1936, 0, 0));
        mobManager.add(new Bogey(2000, 207, this, 1952, 2066, 0, 0));
        mobManager.add(new Bogey(2100, 239, this, 2096, 2201, 0, 0));

        mobManager.add(new Bogey(3700, 1423, this, 3641, 3758, 0, 0));
        mobManager.add(new Bogey(3800, 1455, this, 3792, 3894, 0, 0));
        mobManager.add(new Bogey(4000, 1487, this, 3920, 4058, 0, 0));
        mobManager.add(new Bogey(3950, 1487, this, 4133, 4274, 0, 0));
        mobManager.add(new Bogey(4350, 1487, this, 4343, 4464, 0, 0));

        //eye monster
        mobManager.add(new EyeMonster(410, 300, this, 280, 629, 267, 367));
        mobManager.add(new EyeMonster(450, 250, this, 280, 629, 267, 367));
        mobManager.add(new EyeMonster(600, 250, this, 280, 629, 267, 367));
        mobManager.add(new EyeMonster(300, 350, this, 280, 629, 267, 367));

        mobManager.add(new EyeMonster(1300, 1050, this, 1205, 1460, 1003, 1103));
        mobManager.add(new EyeMonster(1350, 1050, this, 1205, 1460, 1003, 1103));
        mobManager.add(new EyeMonster(1210, 1050, this, 1205, 1460, 1003, 1103));

        mobManager.add(new EyeMonster(1000, 400, this, 990, 1416, 363, 436));
        mobManager.add(new EyeMonster(1250, 370, this, 990, 1416, 363, 436));
        mobManager.add(new EyeMonster(1200, 410, this, 990, 1416, 363, 436));
        mobManager.add(new EyeMonster(1300, 390, this, 990, 1416, 363, 436));

        mobManager.add(new EyeMonster(1850, 200, this, 1807, 2122, 107, 207));
        mobManager.add(new EyeMonster(1900, 150, this, 1807, 2122, 107, 207));
        mobManager.add(new EyeMonster(2000, 180, this, 1807, 2122, 107, 207));
        mobManager.add(new EyeMonster(2100, 110, this, 1807, 2122, 107, 207));

        mobManager.add(new EyeMonster(2600, 851, this, 2499, 2916, 811, 911));
        mobManager.add(new EyeMonster(2700, 871, this, 2499, 2916, 811, 911));
        mobManager.add(new EyeMonster(2800, 901, this, 2499, 2916, 811, 911));

        mobManager.add(new EyeMonster(4050, 719, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(4050, 719, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(3800, 700, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(4100, 650, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(4200, 630, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(3900, 400, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(3700, 329, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(3800, 400, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(4111, 550, this, 3711, 4251, 300, 719));
        mobManager.add(new EyeMonster(4200, 330, this, 3711, 4251, 300, 719));

        //worm
        mobManager.add(new Worm(1370, 1263, this, 1359, 1482, 0, 0));
        mobManager.add(new Worm(1730, 1423, this, 1710, 1969, 0, 0));
        mobManager.add(new Worm(1930, 1423, this, 1710, 1969, 0, 0));
        mobManager.add(new Worm(1830, 1423, this, 1710, 1969, 0, 0));

        mobManager.add(new Worm(2300, 303, this, 2271, 2537, 0, 0));
        mobManager.add(new Worm(2400, 303, this, 2271, 2537, 0, 0));
        mobManager.add(new Worm(2500, 303, this, 2271, 2537, 0, 0));

        mobManager.add(new Worm(3850, 719, this, 3711, 4251, 0, 0));
        mobManager.add(new Worm(3950, 719, this, 3711, 4251, 0, 0));

        //snake monster
        mobManager.add(new SnakeMonster(875, 1135, this, 873, 1259, 0, 0));
        mobManager.add(new SnakeMonster(1205, 1135, this, 873, 1259, 0, 0));

        mobManager.add(new SnakeMonster(2300, 303, this, 2271, 2537, 0, 0));
        mobManager.add(new SnakeMonster(2600, 335, this, 2597, 2918, 0, 0));
        mobManager.add(new SnakeMonster(2700, 335, this, 2597, 2918, 0, 0));
        mobManager.add(new SnakeMonster(2900, 335, this, 2597, 2918, 0, 0));

        //bringer of death
        mobManager.add(new BringerOfDeath(3850, 719, this, 3711, 4251, 0, 0));
        mobManager.add(new BringerOfDeath(3950, 719, this, 3711, 4251, 0, 0));
        mobManager.add(new BringerOfDeath(4050, 719, this, 3711, 4251, 0, 0));

    }

    public GameStateManager getGameStateManeger() {
        return gsm;
    }

    @Override
    public void update() {
        camera.update();
        mapBG.update();
        mobManager.update();
        rangeDameManager.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        mapBG.draw(g2);
        mobManager.draw(g2);
        rangeDameManager.draw(g2);
        g2.drawImage(pause, Game.WIDTH_SCREEN - pause.getWidth() - 10, 10, null);
        map.draw(g2);

        //draw info character
        if (mobManager.getMainChar() != null) {
            g2.drawImage(infoBar.getImage(), Game.WIDTH_SCREEN / 2 - infoBar.getImage().getWidth() / 2, 0, null);
            if (mobManager.getMainChar().getHp() > 0) {
                BufferedImage hp = hpbar.getImage().getSubimage(0, 0,
                        (int) (hpbar.getImage().getWidth() * ((float) mobManager.getMainChar().getHp() / mobManager.getMainChar().getMaxHP())),
                        hpbar.getImage().getHeight());
                g2.drawImage(hp, (int) (321 + hpbar.getImage().getWidth() * (1 - (float) mobManager.getMainChar().getHp() / mobManager.getMainChar().getMaxHP())), 20, null);

            }
            if (mobManager.getMainChar().getMp() > 0) {
                BufferedImage mp = mpbar.getImage().getSubimage((int) (mpbar.getImage().getWidth() - mpbar.getImage().getWidth() * ((float) mobManager.getMainChar().getMp() / mobManager.getMainChar().getMaxMP())),
                        0, (int) (mpbar.getImage().getWidth() * ((float) mobManager.getMainChar().getMp() / mobManager.getMainChar().getMaxMP())), mpbar.getImage().getHeight());
                g2.drawImage(mp, 496, 20, null);
            }
        }
    }

    @Override
    public void keyPressed(int k) {
        try {
            switch (k) {
                case KeyEvent.VK_UP:
                    camera.setPosY(camera.getPosY() - 10);
                    break;
                case KeyEvent.VK_DOWN:
                    camera.setPosY(camera.getPosY() + 10);
                    break;
                case KeyEvent.VK_LEFT:
                    camera.setPosX(camera.getPosX() - 10);
                    break;
                case KeyEvent.VK_RIGHT:
                    camera.setPosX(camera.getPosX() + 10);
                    break;

                case KeyEvent.VK_J:
                    mobManager.getMainChar().attack_normal();
                    break;
                case KeyEvent.VK_K:
                    mobManager.getMainChar().attack_special();
                    break;

                case KeyEvent.VK_W:
                    mobManager.getMainChar().jump();
                    break;
                case KeyEvent.VK_S:
                    break;
                case KeyEvent.VK_A:
                    mobManager.getMainChar().run(EntityObject.DIR_LEFT);
                    break;
                case KeyEvent.VK_D:
                    mobManager.getMainChar().run(EntityObject.DIR_RIGHT);
                    break;
                case KeyEvent.VK_U:
                    System.out.println(mobManager.getMainChar().getPosX() + "/" + mobManager.getMainChar().getPosY());
                    break;
                case KeyEvent.VK_H:
                    mobManager.getMainChar().setMass(0);
                    mobManager.getMainChar().setPosY(mobManager.getMainChar().getPosY() - 100);
                    break;
                case KeyEvent.VK_B:
                    mobManager.getMainChar().setMass(0.05f);
                    mobManager.getMainChar().setPosY(mobManager.getMainChar().getPosY() + 100);
                    break;
                case KeyEvent.VK_L:
                    lookCamera = !lookCamera;
                    break;
                case KeyEvent.VK_I:
                    System.out.println(mobManager.getLst().size() - 1);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Nhân vật không tồn tại!");
        }
    }

    @Override
    public void keyReleased(int k) {
        try {
            switch (k) {
                case KeyEvent.VK_W:

                    break;
                case KeyEvent.VK_S:

                    break;
                case KeyEvent.VK_A:
                    mobManager.getMainChar().stopRun();
                    break;
                case KeyEvent.VK_D:
                    mobManager.getMainChar().stopRun();
                    break;

                case KeyEvent.VK_P:
                    gsm.setState(GameStateManager.MENUINGAMESTATE, false);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Nhân vật không tồn tại!");
        }
    }

    public GameStateManager getGSM() {
        return gsm;
    }

}
