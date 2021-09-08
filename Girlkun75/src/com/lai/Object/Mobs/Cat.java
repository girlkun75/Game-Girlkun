/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Mobs;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import com.lai.Object.Mob;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class Cat extends Mob {

    Animation runR, runL, hurtR, hurtL, deadR, deadL;

    public Cat(int x, int y, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, 32, 32, 0.05f, gameWorld, xMinMove, xMaxMove, yMinMove, yMaxMove);
    }

    @Override
    public void init() {
        super.init();

        setHp(50);
        setDame(5);

        runR = new Animation(GameData.getInstance().getAnimation("cat_run"));
        hurtR = new Animation(GameData.getInstance().getAnimation("cat_hurt"));
        deadR = new Animation(GameData.getInstance().getAnimation("cat_dead"));

        runL = new Animation(runR).flipAllImage();
        hurtL = new Animation(hurtR).flipAllImage();
        deadL = new Animation(deadR).flipAllImage();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBound(g2);
        switch (getStatus()) {
            case ALIVE:
                if (isRun()) {
                    if (getDirection() == DIR_RIGHT) {
                        runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                                (int) (getPosY() - getHeight()/ 2 - gameWorld.camera.getPosY() + 6), g2);
                        runR.update(System.nanoTime());
                    } else {
                        runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                                (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() + 6), g2);
                        runL.update(System.nanoTime());
                    }
                }
                break;
            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() + 6), g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() + 6), g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() + 6), g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() + 6), g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public void run(int diretion) {
        changeDirection();
        if (diretion == DIR_RIGHT) {
            setSpeedX(0.1f);
        } else {
            setSpeedX(-0.1f);
        }
        setRun(true);
    }

    @Override
    public void stopRun() {
    }

    @Override
    public void jump() {
    }

    @Override
    public void attack_normal() {
        gameWorld.mobManager.checkCollisonWithEnemy(this);
    }

    @Override
    public void attack_special() {
    }

    @Override
    public void dead() {
        if (deadR.isLastFrame() || deadL.isLastFrame()) {
            gameWorld.mobManager.remove(this);
        }
    }

    @Override
    public void follow() {
    }
    
    public Rectangle getBoundMakeDame(){
        return getBoundVertical();
    }

}
