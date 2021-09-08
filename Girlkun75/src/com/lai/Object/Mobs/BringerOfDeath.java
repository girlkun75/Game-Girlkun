/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Mobs;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import com.lai.Object.Mob;
import com.lai.Object.Rangedame.ThunderOfDeath;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class BringerOfDeath extends Mob {

    Animation runR, runL, hurtR, hurtL, attack1R, attack1L, deadR, deadL, attack2R, attack2L;

    private final long timeAttackNormal = 1500;
    private long timeStartAttackNormal;
    private final long timeAttackSpecial = 10000;
    private long timeStartAttackSpecial;

    public BringerOfDeath(int x, int y, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, 40, 60, y, gameWorld, xMinMove, xMaxMove, yMinMove, yMaxMove);
    }

    @Override
    public void init() {
        super.init();

        setSpeedY(0.05f);
        setHp(300);
        setDame(0);

        runL = new Animation(GameData.getInstance().getAnimation("bringerofdeath_run"));
        hurtL = new Animation(GameData.getInstance().getAnimation("bringerofdeath_hurt"));
        attack1L = new Animation(GameData.getInstance().getAnimation("bringerofdeath_attack"));
        attack2L = new Animation(GameData.getInstance().getAnimation("bringerofdeath_summon"));
        deadL = new Animation(GameData.getInstance().getAnimation("bringerofdeath_dead"));

        runR = new Animation(runL).flipAllImage();
        hurtR = new Animation(hurtL).flipAllImage();
        attack1R = new Animation(attack1L).flipAllImage();
        attack2R = new Animation(attack2L).flipAllImage();
        deadR = new Animation(deadL).flipAllImage();
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
                            attack1R.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                            attack1R.update(System.nanoTime());
                        } else {
                            attack1L.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 84),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                            attack1L.update(System.nanoTime());
                        }

                    } else if (isAttack_special()) {
                        if (getDirection() == DIR_RIGHT) {
                            attack2R.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                            attack2R.update(System.nanoTime());
                        } else {
                            attack2L.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 84),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                            attack2L.update(System.nanoTime());
                        }
                    } else {
                        if (getDirection() == DIR_RIGHT) {
                            runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                            runR.update(System.nanoTime());
                        } else {
                            runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 84),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                            runL.update(System.nanoTime());
                        }
                    }
                }
                break;
            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 84),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 32), g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 15),
                            (int) (getPosY() - getWidth() / 2 - gameWorld.camera.getPosY()) - 42, g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 84),
                            (int) (getPosY() - getWidth() / 2 - gameWorld.camera.getPosY()) - 42, g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public void run(int diretion) {
        changeDirection();
        if (!isAttack_normal() && !isAttack_special()) {
            if (diretion == DIR_RIGHT) {
                setSpeedX(0.5f);
            } else {
                setSpeedX(-0.5f);
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
        if (gameWorld.mobManager.checkCollisonWithEnemy(this) && !isAttack_special() && (System.nanoTime() - timeStartAttackNormal) / 1000000 >= timeAttackNormal) {
            setAttack_normal(true);
            timeStartAttackNormal = System.nanoTime();
        }
        if (attack1L.getCurrentImage() == 4 || attack1L.getCurrentImage() == 5 || attack1L.getCurrentImage() == 6 || attack1L.getCurrentImage() == 7
                || attack1R.getCurrentImage() == 4 || attack1R.getCurrentImage() == 5 || attack1R.getCurrentImage() == 6 || attack1R.getCurrentImage() == 7) {
            setDame(30);
        } else {
            setDame(0);
        }
        if (attack1L.isLastFrame() || attack1R.isLastFrame()) {
            setAttack_normal(false);
            attack1R.reset();
            attack1L.reset();
        }
    }

    @Override
    public void attack_special() {
        MainCharacter mainChar = gameWorld.mobManager.getMainChar();
        if (mainChar != null && !isAttack_normal() && (System.nanoTime() - timeStartAttackSpecial) / 1000000 >= timeAttackSpecial
                && Math.abs(mainChar.getPosX() - getPosX()) <= 200 && Math.abs(mainChar.getPosY() - getPosY()) <= 20) {
            if (mainChar.getPosX() - getPosX() <= 0 && getDirection() == DIR_LEFT
                    || mainChar.getPosX() - getPosX() >= 0 && getDirection() == DIR_RIGHT) {
                setAttack_special(true);
                timeStartAttackSpecial = System.nanoTime();
            }
        }
        if (attack2R.isLastFrame() || attack2L.isLastFrame()) {
            ThunderOfDeath thunderOfDeath = null;
            if (getDirection() == DIR_LEFT) {
                //int x, int y, int width, int height, int dame, float speedX, float speedY, GameWorldState gameWorld)
                thunderOfDeath = new ThunderOfDeath((int) getPosX() - 30, (int) getPosY(), -0.5f, 0, gameWorld);
            } else {
                thunderOfDeath = new ThunderOfDeath((int) getPosX() + 30, (int) getPosY(), 0.5f, 0, gameWorld);
            }
            thunderOfDeath.setDirection(getDirection());
            gameWorld.rangeDameManager.add(thunderOfDeath);

            setAttack_special(false);
            attack2R.reset();
            attack2L.reset();
        }
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

    @Override
    public Rectangle getBoundMakeDame() {
//        setAttack_normal(true);
//        setDirection(DIR_LEFT);
        if (getDirection() == DIR_LEFT) {
            Rectangle bound = new Rectangle((int) (getPosX() - getWidth() / 2 - 80), (int) (getPosY() - getHeight() / 2), 60, getHeight());
            return bound;
        } else {
            Rectangle bound = new Rectangle((int) (getPosX() + getWidth() / 2 + 20), (int) (getPosY() - getHeight() / 2), 60, getHeight());
            return bound;
        }
    }

}
