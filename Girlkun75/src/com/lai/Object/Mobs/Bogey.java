/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Mobs;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import static com.lai.Object.EntityObject.*;
import com.lai.Object.Mob;
import java.awt.Graphics2D;

/**
 *
 * @author GirlkuN
 */
public class Bogey extends Mob {

    Animation runR, runL, hurtR, hurtL, deadR, deadL, attackR, attackL;

    public Bogey(int x, int y, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, 32, 32, 0.05f, gameWorld, xMinMove, xMaxMove, yMinMove, yMaxMove);
    }

    @Override
    public void init() {
        super.init();

        setHp(70);
        setDame(0);

        runR = new Animation(GameData.getInstance().getAnimation("bogey_run"));
        hurtR = new Animation(GameData.getInstance().getAnimation("bogey_hurt"));
        deadR = new Animation(GameData.getInstance().getAnimation("bogey_dead"));
        attackR = new Animation(GameData.getInstance().getAnimation("bogey_attack"));

        runL = new Animation(runR).flipAllImage();
        hurtL = new Animation(hurtR).flipAllImage();
        deadL = new Animation(deadR).flipAllImage();
        attackL = new Animation(attackR).flipAllImage();
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
                    if (isAttack_normal()) {
                        if (getDirection() == DIR_RIGHT) {
                            attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 10),
                                    (int) (getPosY() - getHeight()/ 2 - gameWorld.camera.getPosY() - 2), g2);
                            attackR.update(System.nanoTime());
                        } else {
                            attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 2), g2);
                            attackL.update(System.nanoTime());
                        }

                    } else {
                        if (getDirection() == DIR_RIGHT) {
                            runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 2), g2);
                            runR.update(System.nanoTime());
                        } else {
                            runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 10),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 2), g2);
                            runL.update(System.nanoTime());
                        }
                    }
                }
                break;
            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 2),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 5), g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 5), g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 10),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 5), g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 10),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 5), g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public void run(int diretion) {
        changeDirection();
        if (!isAttack_normal()) {
            if (diretion == DIR_RIGHT) {
                setSpeedX(0.1f);
            } else {
                setSpeedX(-0.1f);
            }
        } else {
            setSpeedX(0);
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
        if (gameWorld.mobManager.checkCollisonWithEnemy(this)) {
            setAttack_normal(true);
        }

        if (attackR.getCurrentImage() == 10 || attackR.getCurrentImage() == 11
                || attackL.getCurrentImage() == 10 || attackL.getCurrentImage() == 11) {
            setDame(15);
        } else {
            setDame(0);
        }
        if (attackR.isLastFrame() || attackL.isLastFrame()) {
            setAttack_normal(false);
            attackR.reset();
            attackL.reset();
        }
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

}
