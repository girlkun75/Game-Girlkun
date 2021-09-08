/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Mobs;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import static com.lai.Object.EntityObject.*;
import com.lai.Object.*;
import com.lai.Object.Rangedame.BallFire;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class Worm extends Mob {

    Animation runR, runL, hurtR, hurtL, attackR, attackL, deadR, deadL;
    private final long timeAttack = 1000;
    private long timeStartAttack;
    private boolean attacked = false;

    public Worm(int x, int y, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, 55, 35, 0.05f, gameWorld, xMinMove, xMaxMove, yMinMove, yMaxMove);
    }

    @Override
    public void init() {
        super.init();

        setHp(100);
        setDame(0);

        runR = new Animation(GameData.getInstance().getAnimation("worm_run"));
        hurtR = new Animation(GameData.getInstance().getAnimation("worm_hurt"));
        attackR = new Animation(GameData.getInstance().getAnimation("worm_attack"));
        deadR = new Animation(GameData.getInstance().getAnimation("worm_dead"));

        runL = new Animation(runR).flipAllImage();
        hurtL = new Animation(hurtR).flipAllImage();
        attackL = new Animation(attackR).flipAllImage();
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
                    if (isAttack_normal()) {
                        if (getDirection() == DIR_RIGHT) {
                            attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 22), g2);
                            attackR.update(System.nanoTime());
                        } else {
                            attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 20),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 22), g2);
                            attackL.update(System.nanoTime());
                        }

                    } else {
                        if (getDirection() == DIR_RIGHT) {
                            runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 22), g2);
                            runR.update(System.nanoTime());
                        } else {
                            runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 20),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 22), g2);
                            runL.update(System.nanoTime());
                        }
                    }
                }
                break;
            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 22), g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 20),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 22), g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 20),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public Rectangle getBoundTakeDame() {
        return getBoundVertical();
    }

    @Override
    public void run(int diretion) {
        changeDirection();
        if (!isAttack_normal()) {
            if (diretion == DIR_RIGHT) {
                setSpeedX(0.3f);
            } else {
                setSpeedX(-0.3f);
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
        if (Math.abs(gameWorld.mobManager.getMainChar().getPosX() - getPosX()) <= 200
                && Math.abs(gameWorld.mobManager.getMainChar().getPosY() - getPosY()) <= 10
                && (System.nanoTime() - timeStartAttack) / 1000000 >= timeAttack) {
            if (gameWorld.mobManager.getMainChar().getPosX() - getPosX() <= 0 && getDirection() == DIR_LEFT) {
                setAttack_normal(true);
                timeStartAttack = System.nanoTime();
            }
            if (gameWorld.mobManager.getMainChar().getPosX() - getPosX() >= 0 && getDirection() == DIR_RIGHT) {
                setAttack_normal(true);
                timeStartAttack = System.nanoTime();
            }
        }

        if ((attackR.getCurrentImage() == 11 || attackL.getCurrentImage() == 11) && !attacked) {
            attacked = true;
            RangeDame ballFire = null;
            if (getDirection() == DIR_RIGHT) {
                ballFire = new BallFire((int) getPosX() + 30, (int) getPosY(), 5, 0, gameWorld);
            } else {
                ballFire = new BallFire((int) getPosX() - 30, (int) getPosY(), -5, 0, gameWorld);
            }
            ballFire.setDirection(getDirection());
            gameWorld.rangeDameManager.add(ballFire);
        }

        if (attackR.isLastFrame() || attackL.isLastFrame()) {
            attacked = false;
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

    public Rectangle getBoundMakeDame() {
        return new Rectangle(0, 0, 0, 0);
    }

}
