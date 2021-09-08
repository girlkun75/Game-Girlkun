/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object;

import com.lai.GameState.GameWorldState;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public abstract class RangeDame extends EntityObject {

    private float friction;

    public RangeDame(int x, int y, int width, int height, GameWorldState gameWorld) {
        super(x, y, width, height, 0, gameWorld);
        init();
    }
    
    @Override
    public void init(){
        setTeam(TEAM_KUN);
    }

    @Override
    public void update() {
        setSpeedY(getSpeedY() + getMass());
        setPosY(getPosY() + getSpeedY());
        attack();
        move();
        dead();
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public void move() {
        if (getDirection() == DIR_RIGHT) {
            setSpeedX(getSpeedX() - getFriction());
            if (getSpeedX() < 0) {
                setSpeedX(0);
            }
        } else {
            setSpeedX(getSpeedX() + getFriction());
            if (getSpeedX() > 0) {
                setSpeedX(0);
            }
        }
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
    }

    public void dead() {
        if (isOutOfCamera()) {
            setStatus(DEAD);
        }
    }

    public void attack() {
        gameWorld.mobManager.checkCollisonWithEnemy(this);
    }
    
    @Override
    public Rectangle getBoundMakeDame(){
        return getBound();
    }
    
    @Override
    public Rectangle getBoundTakeDame() {
        return new Rectangle(0, 0, 0, 0);
    }
}
