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
import com.lai.Object.Rangedame.DarkFire;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author GirlkuN
 */
public class Death extends Mob {

    Animation runR, runL, hurtR, hurtL, attack1R, attack1L, deadR, deadL, attack2R, attack2L;

    private final long timeTeleport = 3000;
    private long timeStartTeleport;
    private final long timeAttackNormal = 3000;
    private long timeStartAttackNormal;
    private final long timeAttackSpecial = 5000;
    private long timeStartAttackSpecial;
    
    private Random rd = new Random();

    public Death(int x, int y, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, 40, 60, y, gameWorld, xMinMove, xMaxMove, yMinMove, yMaxMove);
    }

    @Override
    public void init() {
        super.init();

        setSpeedY(0.05f);
        setHp(100);
        setDame(0);

        runR = new Animation(GameData.getInstance().getAnimation("death_run"));
        hurtR = new Animation(GameData.getInstance().getAnimation("death_hurt"));
        attack1R = new Animation(GameData.getInstance().getAnimation("death_attack"));
        attack2R = new Animation(GameData.getInstance().getAnimation("death_summon"));
        deadR = new Animation(GameData.getInstance().getAnimation("death_dead"));

        runL = new Animation(runR).flipAllImage();
        hurtL = new Animation(hurtR).flipAllImage();
        attack1L = new Animation(attack1R).flipAllImage();
        attack2L = new Animation(attack2R).flipAllImage();
        deadL = new Animation(deadR).flipAllImage();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.red);
//        if (getDirection() == DIR_RIGHT) {
//            g2.drawLine((int) (getPosX() + 100 - gameWorld.camera.getPosX()),
//                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()), (int) (getPosX() + 100 - gameWorld.camera.getPosX()), (int) (getPosY() + getHeight() / 2 - gameWorld.camera.getPosY()));
//            g2.drawLine((int) (getPosX() + 300 - gameWorld.camera.getPosX()),
//                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()), (int) (getPosX() + 300 - gameWorld.camera.getPosX()), (int) (getPosY() + getHeight() / 2 - gameWorld.camera.getPosY()));
//        } else {
//            g2.drawLine((int) (getPosX() - 100 - gameWorld.camera.getPosX()),
//                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()), (int) (getPosX() - 100 - gameWorld.camera.getPosX()), (int) (getPosY() + getHeight() / 2 - gameWorld.camera.getPosY()));
//            g2.drawLine((int) (getPosX() - 300 - gameWorld.camera.getPosX()),
//                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()), (int) (getPosX() - 300 - gameWorld.camera.getPosX()), (int) (getPosY() + getHeight() / 2 - gameWorld.camera.getPosY()));
//        }

//        drawBound(g2);
        switch (getStatus()) {
            case ALIVE:
                if (isRun()) {
                    if (isAttack_normal()) {
                        if (getDirection() == DIR_RIGHT) {
                            attack1R.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                            attack1R.update(System.nanoTime());
                        } else {
                            attack1L.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 35),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                            attack1L.update(System.nanoTime());
                        }

                    } else if (isAttack_special()) {
                        if (getDirection() == DIR_RIGHT) {
                            attack2R.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                            attack2R.update(System.nanoTime());
                        } else {
                            attack2L.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 35),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                            attack2L.update(System.nanoTime());
                        }
                    } else {
                        if (getDirection() == DIR_RIGHT) {
                            runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                            runR.update(System.nanoTime());
                        } else {
                            runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 35),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                            runL.update(System.nanoTime());
                        }
                    }
                }
                break;
            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 35),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 23), g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 25),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 23, g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 35),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY()) - 23, g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public void run(int diretion) {
        if (!isAttack_normal()) {
            changeDirection();
            if (!isAttack_normal() && !isAttack_special()) {
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
        if (attack1L.getCurrentImage() == 2 || attack1L.getCurrentImage() == 3 || attack1L.getCurrentImage() == 9 || attack1L.getCurrentImage() == 10
                || attack1R.getCurrentImage() == 2 || attack1R.getCurrentImage() == 3 || attack1R.getCurrentImage() == 9 || attack1R.getCurrentImage() == 10) {
            setDame(10);
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
                && Math.abs(mainChar.getPosX() - getPosX()) <= 300 && !isOutOfCamera()) {
            if (mainChar.getPosX() - getPosX() <= 0 && getDirection() == DIR_LEFT
                    || mainChar.getPosX() - getPosX() >= 0 && getDirection() == DIR_RIGHT) {
                setAttack_special(true);
                timeStartAttackSpecial = System.nanoTime();
            }
        }
        if (attack2R.isLastFrame() || attack2L.isLastFrame()) {
            RangeDame darkFire1, darkFire2, darkFire3, darkFire4, darkFire5, darkFire6, darkFire7, darkFire8, darkFire9, darkFire10;
            if (getDirection() == DIR_LEFT) {
                //int x, int y, int width, int height, int dame, float speedX, float speedY, GameWorldState gameWorld)
                darkFire1 = new DarkFire((int) getPosX() - 40, (int) getPosY() - 20, 0, 0, gameWorld);
                darkFire2 = new DarkFire((int) getPosX() - rd.nextInt(20), (int) getPosY() - rd.nextInt(20), 0, 0, gameWorld);
                darkFire3 = new DarkFire((int) getPosX() + rd.nextInt(20), (int) getPosY() + rd.nextInt(20), 0, 0, gameWorld);
                darkFire4 = new DarkFire((int) getPosX() + rd.nextInt(20), (int) getPosY() + rd.nextInt(20), 0, 0, gameWorld);

                darkFire5 = new DarkFire((int) getPosX() - 40, (int) getPosY() - 20, 0, 0, gameWorld);
                darkFire6 = new DarkFire((int) getPosX() - rd.nextInt(10), (int) getPosY() - rd.nextInt(10), 0, 0, gameWorld);
                darkFire7 = new DarkFire((int) getPosX() + rd.nextInt(20), (int) getPosY() + rd.nextInt(20), 0, 0, gameWorld);
                darkFire8 = new DarkFire((int) getPosX() + rd.nextInt(30), (int) getPosY() + rd.nextInt(30), 0, 0, gameWorld);
                darkFire9 = new DarkFire((int) getPosX() + rd.nextInt(40), (int) getPosY() + rd.nextInt(40), 0, 0, gameWorld);
                darkFire10 = new DarkFire((int) getPosX() + rd.nextInt(50), (int) getPosY() + rd.nextInt(50), 0, 0, gameWorld);
            } else {
                darkFire1 = new DarkFire((int) getPosX() + 40, (int) getPosY() - 20, 0, 0, gameWorld);
                darkFire2 = new DarkFire((int) getPosX() + rd.nextInt(20), (int) getPosY() - rd.nextInt(20), 0, 0, gameWorld);
                darkFire3 = new DarkFire((int) getPosX() - rd.nextInt(20), (int) getPosY() + rd.nextInt(20), 0, 0, gameWorld);
                darkFire4 = new DarkFire((int) getPosX() - rd.nextInt(20), (int) getPosY() + rd.nextInt(20), 0, 0, gameWorld);

                darkFire5 = new DarkFire((int) getPosX() + 40, (int) getPosY() - 20, 0, 0, gameWorld);
                darkFire6 = new DarkFire((int) getPosX() + rd.nextInt(10), (int) getPosY() - rd.nextInt(10), 0, 0, gameWorld);
                darkFire7 = new DarkFire((int) getPosX() - rd.nextInt(20), (int) getPosY() + rd.nextInt(20), 0, 0, gameWorld);
                darkFire8 = new DarkFire((int) getPosX() - rd.nextInt(30), (int) getPosY() + rd.nextInt(30), 0, 0, gameWorld);
                darkFire9 = new DarkFire((int) getPosX() - rd.nextInt(40), (int) getPosY() + rd.nextInt(40), 0, 0, gameWorld);
                darkFire10 = new DarkFire((int) getPosX() - rd.nextInt(50), (int) getPosY() + rd.nextInt(50), 0, 0, gameWorld);
            }
            darkFire1.setDirection(getDirection());
            darkFire2.setDirection(getDirection());
            darkFire3.setDirection(getDirection());
            darkFire4.setDirection(getDirection());
            darkFire5.setDirection(getDirection());
            darkFire6.setDirection(getDirection());
            darkFire7.setDirection(getDirection());
            darkFire8.setDirection(getDirection());
            darkFire9.setDirection(getDirection());
            darkFire10.setDirection(getDirection());
            gameWorld.rangeDameManager.add(darkFire1);
            gameWorld.rangeDameManager.add(darkFire2);
            gameWorld.rangeDameManager.add(darkFire3);
            gameWorld.rangeDameManager.add(darkFire4);
            gameWorld.rangeDameManager.add(darkFire5);
            gameWorld.rangeDameManager.add(darkFire6);
            gameWorld.rangeDameManager.add(darkFire7);
            gameWorld.rangeDameManager.add(darkFire8);
            gameWorld.rangeDameManager.add(darkFire9);
            gameWorld.rangeDameManager.add(darkFire10);

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
        MainCharacter mainChar = gameWorld.mobManager.getMainChar();
        if (mainChar != null && !isAttack_normal() && (System.nanoTime() - timeStartTeleport) / 1000000 >= timeTeleport
                && Math.abs(mainChar.getPosX() - getPosX()) <= 100 && Math.abs(mainChar.getPosY() - getPosY()) <= 14) {
            if (mainChar.getPosX() - getPosX() <= 0 && getDirection() == DIR_LEFT) {
                setPosX(mainChar.getPosX() + 30);
                setSpeedX(0);
                timeStartTeleport = System.nanoTime();
            }
            if (mainChar.getPosX() - getPosX() >= 0 && getDirection() == DIR_RIGHT) {
                setPosX(mainChar.getPosX() - 30);
                setSpeedX(0);
                timeStartTeleport = System.nanoTime();
            }
        }
    }

    @Override
    public Rectangle getBoundMakeDame() {
//        setAttack_normal(true);
        if (getDirection() == DIR_LEFT) {
            Rectangle bound = new Rectangle((int) (getPosX() - 30), (int) (getPosY() - getHeight() / 2 - 10), 30, getHeight());
            return bound;
        } else {
            Rectangle bound = new Rectangle((int) (getPosX()), (int) (getPosY() - getHeight() / 2 - 10), 30, getHeight());
            return bound;
        }
    }

}
