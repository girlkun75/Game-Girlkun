/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object;

import com.lai.GameState.GameWorldState;
import com.lai.Object.Mobs.EyeMonster;
import com.lai.Object.Mobs.MainCharacter;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author GirlkuN
 */
public abstract class Mob extends EntityObject {

    private boolean run;
    private boolean jump;

    private boolean attack_normal;
    private boolean attack_special;

    private boolean hurt;

    private long timeHurt = 600;
    private long timeStartHurt;

    private boolean miss;

    private int xMinMove, xMaxMove, yMinMove, yMaxMove;

    public Mob(int x, int y, int width, int height, float mass, GameWorldState gameWorld, int xMinMove, int xMaxMove, int yMinMove, int yMaxMove) {
        super(x, y, width, height, mass, gameWorld);
        this.xMinMove = xMinMove;
        this.xMaxMove = xMaxMove;
        this.yMinMove = yMinMove;
        this.yMaxMove = yMaxMove;
    }

    @Override
    public void init() {
        setStatus(ALIVE);
        setDirection(DIR_RIGHT);
        setTeam(TEAM_KUN);
    }

    public void takeDame(int dame) {
        if (getStatus() != HURT && !isHurt()) {
            timeStartHurt = System.nanoTime();
            setStatus(HURT);
            setHurt(true);
            setHp(getHp() - dame);
        }
    }

    @Override
    public void update() {
        super.update();
        randomMiss();

        if ((System.nanoTime() - timeStartHurt) / 1000000 >= getTimeHurt() && getStatus() != DEAD) {
            setStatus(ALIVE);
            setHurt(false);
            timeStartHurt = System.nanoTime();
        }

        switch (getStatus()) {
            case ALIVE:
                setPosX(getPosX() + getSpeedX());
                setPosY(getPosY() + getSpeedY());

                
                
                Rectangle horizontal = getBoundHorizontal();
                Rectangle left = gameWorld.getMap().getCollisionLeft(horizontal);
                Rectangle right = gameWorld.getMap().getCollisionRight(horizontal);
                if (left != null) {
                    setPosX(left.x + left.width + getWidth() / 2);
                }
                if (right != null) {
                    setPosX(right.x - getWidth() / 2);
                }
                
                
                
                
                Rectangle vertical = getBoundVertical();
                
                
//                check water, ice
                if(gameWorld.getMap().checkCollisionWithWater(vertical)){
                    setHp(getHp()-getHp());
                }
                
                Rectangle top = gameWorld.getMap().getCollisionTop(vertical);
                Rectangle bot = gameWorld.getMap().getCollisionBot(vertical);
                
                
                
                
                if (top != null) {
                    if (!(this instanceof EyeMonster)) {
                        setSpeedY(0);
                    }
                    setPosY(top.y + gameWorld.getMap().getTileSize() + getHeight() / 2 + 1);
                }
                if (bot != null) {
                    if (!(this instanceof EyeMonster)) {
                        setSpeedY(0);
                    }
                    setPosY(bot.y - getHeight() / 2 - 1);
                    setJump(false);
                } else {
                    setSpeedY(getSpeedY() + getMass());
                    if(getSpeedY() >5){
                        setSpeedY(5);
                    }
                    setPosY(getPosY() + getSpeedY());
                    setJump(true);
                }

                if (!(this instanceof MainCharacter)) {
                    follow();
                    attack_normal();
                    attack_special();
                    run(getDirection());
                }

                break;
            case HURT:

                break;
            case DEAD:
                dead();

                break;
        }
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isAttack_normal() {
        return attack_normal;
    }

    public void setAttack_normal(boolean attack_normal) {
        this.attack_normal = attack_normal;
    }

    public boolean isAttack_special() {
        return attack_special;
    }

    public void setAttack_special(boolean attack_special) {
        this.attack_special = attack_special;
    }

    public boolean isHurt() {
        return hurt;
    }

    public void setHurt(boolean nohurt) {
        this.hurt = nohurt;
    }

    public long getTimeHurt() {
        return timeHurt;
    }

    public void setTimeHurt(long timeHurt) {
        this.timeHurt = timeHurt;
    }

    public long getTimeHurtStart() {
        return timeStartHurt;
    }

    public int getxMinMove() {
        return xMinMove;
    }

    public void setxMinMove(int xMinMove) {
        this.xMinMove = xMinMove;
    }

    public int getxMaxMove() {
        return xMaxMove;
    }

    public void setxMaxMove(int xMaxMove) {
        this.xMaxMove = xMaxMove;
    }

    public int getyMinMove() {
        return yMinMove;
    }

    public void setyMinMove(int yMinMove) {
        this.yMinMove = yMinMove;
    }

    public int getyMaxMove() {
        return yMaxMove;
    }

    public void setyMaxMove(int yMaxMove) {
        this.yMaxMove = yMaxMove;
    }

    public void changeDirection() {
        if ((getPosX() <= getxMinMove() && getDirection() == DIR_LEFT) || (getPosX() >= getxMaxMove() && getDirection() == DIR_RIGHT)) {
            if (getDirection() == DIR_RIGHT) {
                setDirection(DIR_LEFT);
            } else {
                setDirection(DIR_RIGHT);
            }
        }
    }

    public boolean getMiss() {
        return miss;
    }

    public void setMiss(boolean miss) {
        this.miss = miss;
    }

    public void randomMiss() {
        miss = new Random().nextInt() % 2 == 0;
    }

    public abstract void run(int diretion);

    public abstract void stopRun();

    public abstract void jump();

    public abstract void follow();

    public abstract void attack_normal();

    public abstract void attack_special();

    public abstract void dead();
}
