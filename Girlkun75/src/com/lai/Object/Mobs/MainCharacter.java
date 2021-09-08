/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Mobs;

import com.lai.GameEffect.*;
import com.lai.GameState.*;
import com.lai.Object.*;
import com.lai.Object.Rangedame.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author GirlkuN
 */
public class MainCharacter extends Mob {

    Animation idleR, idleL, runR, runL, jumpR, jumpL, hurtR, hurtL, deadR, deadL, normal_attackR, normal_attackL, special_attackR, special_attackL;
    Animation testR, testL;

    private final long timeRecoveryHP = 10000;
    private long timeStartRecoveryHP;
    private final long timeRecoveryMP = 1500;
    private long timeStartRecoveryMP;

    private Random rd = new Random();

    public MainCharacter(int x, int y, float mass, int hp, int mp, int dame, GameWorldState gameWorld) {
        super(x, y, 32, 32, mass, gameWorld, 0, 0, 0, 0);
        this.setMaxHP(hp);
        this.setHp(hp);
        this.setMaxMP(mp);
        this.setMp(mp);
        this.setDame(dame);
    }

    @Override
    public void init() {
        super.init();
        setTeam(TEAM_GIRL);

        idleR = new Animation(GameData.getInstance().getAnimation("idle"));
        runR = new Animation(GameData.getInstance().getAnimation("run"));
        jumpR = new Animation(GameData.getInstance().getAnimation("jump"));
        hurtR = new Animation(GameData.getInstance().getAnimation("hurt"));
        deadR = new Animation(GameData.getInstance().getAnimation("dead"));
        normal_attackR = new Animation(GameData.getInstance().getAnimation("normal_attack"));
        special_attackR = new Animation(GameData.getInstance().getAnimation("special_attack"));

        idleL = new Animation(idleR).flipAllImage();
        runL = new Animation(runR).flipAllImage();
        jumpL = new Animation(jumpR).flipAllImage();
        hurtL = new Animation(hurtR).flipAllImage();
        deadL = new Animation(deadR).flipAllImage();
        normal_attackL = new Animation(normal_attackR).flipAllImage();
        special_attackL = new Animation(special_attackR).flipAllImage();
    }

    @Override
    public void update() {
        super.update();
        recoveryHP(System.nanoTime());
        recoveryMP(System.nanoTime());
        if (!isJump()) {
            jumpR.reset();
            jumpL.reset();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBound(g2);

        switch (getStatus()) {
            case ALIVE:
                if (isJump()) {
                    if (getDirection() == DIR_RIGHT) {
                        if (isAttack_normal()) {
                            normal_attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            normal_attackR.update(System.nanoTime());
                            if (normal_attackR.isLastFrame()) {
                                creFire1();
                            }
                        } else if (isAttack_special()) {
                            special_attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            special_attackR.update(System.nanoTime());
                            if (special_attackR.isLastFrame()) {
                                creFire2();
                            }
                        } else {
                            jumpR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            jumpR.update(System.nanoTime());
                            if (jumpR.getCountRepeat() >= 1) {
                                jumpR.setRepeat(false);
                                jumpL.setRepeat(false);
                                jumpL.setCurrentImage(jumpL.getLastIndex());
                            }
                        }
                    } else {
                        if (isAttack_normal()) {
                            normal_attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            normal_attackL.update(System.nanoTime());
                            if (normal_attackL.isLastFrame()) {
                                creFire1();
                            }
                        } else if (isAttack_special()) {
                            special_attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 22),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            special_attackL.update(System.nanoTime());
                            if (special_attackL.isLastFrame()) {
                                creFire2();
                            }
                        } else {
                            jumpL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            jumpL.update(System.nanoTime());
                            if (jumpL.getCountRepeat() >= 1) {
                                jumpL.setRepeat(false);
                                jumpR.setRepeat(false);
                                jumpR.setCurrentImage(jumpR.getLastIndex());
                            }
                        }
                    }
                } else if (isRun()) {
                    if (getDirection() == DIR_RIGHT) {
                        if (isAttack_normal()) {
                            normal_attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            normal_attackR.update(System.nanoTime());
                            if (normal_attackR.isLastFrame()) {
                                creFire1();
                            }

                        } else if (isAttack_special()) {
                            special_attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            special_attackR.update(System.nanoTime());
                            if (special_attackR.isLastFrame()) {
                                creFire2();
                            }
                        } else {
                            runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            runR.update(System.nanoTime());
                        }
                    } else {
                        if (isAttack_normal()) {
                            normal_attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            normal_attackL.update(System.nanoTime());
                            if (normal_attackL.isLastFrame()) {
                                creFire1();
                            }

                        } else if (isAttack_special()) {
                            special_attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 22),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            special_attackL.update(System.nanoTime());
                            if (special_attackL.isLastFrame()) {
                                creFire2();
                            }
                        } else {
                            runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            runL.update(System.nanoTime());
                        }
                    }
                } else {
                    if (getDirection() == DIR_RIGHT) {
                        if (isAttack_normal()) {
                            normal_attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            normal_attackR.update(System.nanoTime());
                            if (normal_attackR.isLastFrame()) {
                                creFire1();
                            }

                        } else if (isAttack_special()) {
                            special_attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            special_attackR.update(System.nanoTime());
                            if (special_attackR.isLastFrame()) {
                                creFire2();
                            }
                        } else {
                            idleR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            idleR.update(System.nanoTime());
                        }
                    } else {
                        if (isAttack_normal()) {
                            normal_attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            normal_attackL.update(System.nanoTime());
                            if (normal_attackL.isLastFrame()) {
                                creFire1();
                            }

                        } else if (isAttack_special()) {
                            special_attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 22),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            special_attackL.update(System.nanoTime());
                            if (special_attackL.isLastFrame()) {
                                creFire2();
                            }
                        } else {
                            idleL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                            idleL.update(System.nanoTime());
                        }
                    }
                }
                break;

            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 5),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 22, g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 47),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 45, g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 47),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 45, g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public void run(int direction) {
        if (!isHurt()) {
            setRun(true);
            setDirection(direction);
            if (direction == DIR_LEFT) {
                setSpeedX(gameWorld.getMap().checkCollisionWithIce(getBoundVertical()) ? -0.5f : -1.5f);
//                setSpeedX(gameWorld.getMap().checkCollisionWithIce(getBoundVertical()) ? -3f : -3f);
            } else {
                setSpeedX(gameWorld.getMap().checkCollisionWithIce(getBoundVertical()) ? 0.5f : 1.5f);
//                setSpeedX(gameWorld.getMap().checkCollisionWithIce(getBoundVertical()) ? 3f : 3f);
            }
        }
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        setRun(false);
    }

    @Override
    public void jump() {
        if (!isJump()) {
            setJump(true);
            setSpeedY(-2f);
        }
    }

    @Override
    public void attack_normal() {
        if (!isHurt() && !isAttack_normal() && !isAttack_special()) {
            setAttack_normal(true);
        }
    }

    @Override
    public void attack_special() {
        if (!isHurt() && !isAttack_normal() && !isAttack_special() && getMp() >= 10) {
            setAttack_special(true);
            setMp(getMp() - 10);
        }
    }

    private void creFire1() {
        RangeDame fireNormal;
        if (getDirection() == DIR_RIGHT) {
//            fireNormal = new Boom((int) getPosX(), (int) getPosY(), 30, 2f, 0, gameWorld);
            fireNormal = new FireNormal((int) getPosX(), (int) getPosY(), 10 + rd.nextInt(11), 2.5f, 0, gameWorld);
        } else {
//            fireNormal = new Boom((int) getPosX(), (int) getPosY(), 30, -2f, 0, gameWorld);
            fireNormal = new FireNormal((int) getPosX(), (int) getPosY(), 10 + rd.nextInt(11), -2.5f, 0, gameWorld);
        }
        fireNormal.setTeam(getTeam());
        fireNormal.setDirection(getDirection());
        gameWorld.rangeDameManager.add(fireNormal);
        setAttack_normal(false);
        normal_attackR.reset();
        normal_attackL.reset();
        special_attackL.reset();
        special_attackR.reset();

    }

    private void creFire2() {
        RangeDame fireSpecial;
        if (getDirection() == DIR_RIGHT) {
            fireSpecial = new FireSpecial((int) getPosX(), (int) getPosY(), 20 + rd.nextInt(21), 5, 0, gameWorld);
        } else {
            fireSpecial = new FireSpecial((int) getPosX(), (int) getPosY(), 20 + rd.nextInt(21), -5, 0, gameWorld);
        }
        fireSpecial.setTeam(getTeam());
        fireSpecial.setDirection(getDirection());
        gameWorld.rangeDameManager.add(fireSpecial);
        setAttack_special(false);
        normal_attackR.reset();
        normal_attackL.reset();
        special_attackL.reset();
        special_attackR.reset();
    }

    @Override
    public void changeDirection() {
    }

    @Override
    public Rectangle getBoundMakeDame() {
        return new Rectangle(0, 0, 0, 0);
    }

    @Override
    public Rectangle getBoundTakeDame() {
        return getBoundVertical();
    }

    @Override
    public void dead() {
        if (deadR.isLastFrame() || deadL.isLastFrame()) {
            gameWorld.getGSM().gameOver = true;
            gameWorld.mobManager.remove(this);
            gameWorld.getGameStateManeger().setState(GameStateManager.MENUINGAMESTATE, false);
        }
    }

    @Override
    public void follow() {
    }

    @Override
    public void randomMiss() {
        setMiss(false);
    }

    private void recoveryHP(long time) {
        if ((time - this.timeStartRecoveryHP) / 1000000 >= timeRecoveryHP) {
            if (getHp() < getMaxHP()) {
                setHp(getHp() + 10);
                timeStartRecoveryHP = time;
            }
            if (getHp() > getMaxHP()) {
                setHp(getMaxHP());
            }
        }
    }

    private void recoveryMP(long time) {
        if ((time - this.timeStartRecoveryMP) / 1000000 >= timeRecoveryMP) {
            if (getMp() < getMaxMP()) {
                setMp(getMp() + 5);
                timeStartRecoveryMP = time;
            }
            if (getMp() > getMaxMP()) {
                setMp(getMaxMP());
            }
        }
    }

    @Override
    public Rectangle getBoundVertical() {
        Rectangle bound = new Rectangle((int) (getPosX() - 8), (int) (getPosY() - getHeight() / 2), 16, (int) getHeight() + 3);
        return bound;
    }

}
