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
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class EyeMonster extends Mob {

    Animation runR, runL, hurtR, hurtL, attackR, attackL, deadR, deadL;
    private final long timeAttack = 3000;
    private final long timeFollow = 1500;
    private long timeStartAttack;
    private long timeStartFollow;

    public EyeMonster(int x, int y, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, 40, 25, 0, gameWorld, xMinMove, xMaxMove, yMinMove, yMaxMove);
    }

    @Override
    public void init() {
        super.init();

        setSpeedY(0.05f);
        setHp(50);
        setDame(0);

        runR = new Animation(GameData.getInstance().getAnimation("eyemonster_fly"));
        hurtR = new Animation(GameData.getInstance().getAnimation("eyemonster_hurt"));
        attackR = new Animation(GameData.getInstance().getAnimation("eyemonster_attack"));
        deadR = new Animation(GameData.getInstance().getAnimation("eyemonster_dead"));

        runL = new Animation(runR).flipAllImage();
        hurtL = new Animation(hurtR).flipAllImage();
        attackL = new Animation(attackR).flipAllImage();
        deadL = new Animation(deadR).flipAllImage();

        deadR.setIgnoreALot(4, 5);
        deadL.setIgnoreALot(4, 5);
    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBound(g2);
//        g2.setColor(Color.red);
//        g2.drawRect(getxMinMove() - (int)gameWorld.camera.getPosX(), getyMinMove() - (int)gameWorld.camera.getPosY(), getxMaxMove()-getxMinMove(), getyMaxMove()-getyMinMove());
        
        switch (getStatus()) {
            case ALIVE:
                if (isRun()) {
                    if (isAttack_normal()) {
                        if (getDirection() == DIR_RIGHT) {
                            attackR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 7),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                            attackR.update(System.nanoTime());
                        } else {
                            attackL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                            attackL.update(System.nanoTime());
                        }

                    } else {
                        if (getDirection() == DIR_RIGHT) {
                            runR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 7),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                            runR.update(System.nanoTime());
                        } else {
                            runL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 3),
                                    (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                            runL.update(System.nanoTime());
                        }
                    }
                }
                break;
            case HURT:
                if (getDirection() == DIR_RIGHT) {
                    hurtR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 7),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                    hurtR.update(System.nanoTime());
                } else {
                    hurtL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                    hurtL.update(System.nanoTime());
                }
                break;
            case DEAD:
                if (getDirection() == DIR_RIGHT) {
                    deadR.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX() - 7),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                    deadR.update(System.nanoTime());
                } else {
                    deadL.drawImage((int) (getPosX() - getWidth() / 2 - gameWorld.camera.getPosX()),
                            (int) (getPosY() - getHeight() / 2 - gameWorld.camera.getPosY() - 13), g2);
                    deadL.update(System.nanoTime());
                }
                break;
        }
    }

    @Override
    public Rectangle getBoundMakeDame() {
        return new Rectangle((int) getPosX() - 10, (int) getPosY(), 20, getHeight() / 2);
    }

    @Override
    public Rectangle getBoundTakeDame() {
        return new Rectangle((int) (getPosX() - 10), (int) (getPosY() - getHeight() / 2), 20, (int) getHeight());
    }

    @Override
    public void run(int diretion) {
        changeDirection();
        if (!isAttack_normal()) {
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
    int ixxx = 1;

    @Override
    public void attack_normal() {

        if (gameWorld.mobManager.checkCollisonWithEnemy(this) && (System.nanoTime() - timeStartAttack) / 1000000 >= timeAttack) {
            setAttack_normal(true);
            timeStartAttack = System.nanoTime();
        }

        if (attackR.getCurrentImage() == 6 || attackL.getCurrentImage() == 6
                || attackR.getCurrentImage() == 7 || attackL.getCurrentImage() == 7) {
            setDame(10);
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
    public void changeDirection() {
        if ((getPosX() <= getxMinMove() && getDirection() == DIR_LEFT) || (getPosX() >= getxMaxMove() && getDirection() == DIR_RIGHT)) {
            if (getDirection() == DIR_RIGHT) {
                setDirection(DIR_LEFT);
            } else {
                setDirection(DIR_RIGHT);
            }
        }
        if (getPosY() <= getyMinMove() && getSpeedY() < 0 || getPosY() >= getyMaxMove() && getSpeedY() > 0) {
            setSpeedY(-getSpeedY());
        }
    }

    @Override
    public void follow() {
        MainCharacter mainChar = gameWorld.mobManager.getMainChar();
        if (mainChar != null && mainChar.getPosX() >= getxMinMove()
                && mainChar.getPosX() <= getxMaxMove()) {
            if ((System.nanoTime() - timeStartFollow) / 1000000 >= timeFollow) {
                if (mainChar.getPosX() - getPosX() < 0) {
                    setDirection(DIR_LEFT);
                } else {
                    setDirection(DIR_RIGHT);
                }
                timeStartFollow = System.nanoTime();
            }
        }
    }

    @Override
    public void dead() {
        if (deadR.getCurrentImage() == 3 || deadL.getCurrentImage() == 3) {
            setSpeedY(1.2f);
            setPosY(getPosY() + getSpeedY());
            deadR.setIgnoreALot(0, 1, 2);
            deadL.setIgnoreALot(0, 1, 2);
        }
        if (gameWorld.getMap().getCollisionBot(getBoundVertical()) != null) {
            deadR.unIgnoreAll();
            deadL.unIgnoreAll();
        }
        if ((deadR.isLastFrame() || deadL.isLastFrame()) || (getStatus() ==DEAD && isOutOfCamera())) {
            gameWorld.mobManager.remove(this);
        }
    }
}
