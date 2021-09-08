/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Rangedame;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import static com.lai.Object.EntityObject.*;
import com.lai.Object.RangeDame;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class Boom extends RangeDame {

    Animation right, left;

    public Boom(int x, int y, int dame, float speedX, float speedY, GameWorldState gameWorld) {
        super(x, y, 12, 12, gameWorld);
        this.setDame(0);
        this.setSpeedX(speedX);
        this.setSpeedY(-1.6f);
        this.setMass(0.02f);
    }

    @Override
    public void init() {
        super.init();
        this.setFriction(0.01f);

        right = new Animation(GameData.getInstance().getAnimation("boom"));
        left = new Animation(right).flipAllImage();

        right.setIgnoreALot(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        left.setIgnoreALot(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBound(g2);
        switch (getDirection()) {
            case DIR_RIGHT:
                right.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 52),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 50), g2);
                right.update(System.nanoTime());
                break;
            case DIR_LEFT:
                left.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 48),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 50), g2);
                left.update(System.nanoTime());
                break;
        }
    }

    @Override
    public void attack() {
        super.attack();
        if (right.getCurrentImage() == 12 || right.getCurrentImage() == 13 || right.getCurrentImage() == 14
                || right.getCurrentImage() == 12 || right.getCurrentImage() == 13 || right.getCurrentImage() == 14) {
            setDame(10);
        } else {
            setDame(0);
        }
    }

    @Override
    public void move() {

        Rectangle vertical = getBoundVertical();
        if (gameWorld.getMap().getCollisionTop(vertical) != null) {
            setPosY(getPosY() + getHeight() / 2);
            setSpeedY(0);
        }
        if (gameWorld.getMap().getCollisionBot(vertical) != null) {
            setMass(0);
            setSpeedY(0);
            setFriction(0.03f);
        } else {
            setMass(0.02f);
        }

        Rectangle horizontal = getBoundHorizontal();
        if (gameWorld.getMap().getCollisionLeft(horizontal) != null
                || gameWorld.getMap().getCollisionRight(horizontal) != null) {
            changeDirection();
            setSpeedX(-getSpeedX());
        }

        if (right.getCountRepeat() == 3 || left.getCountRepeat() == 3) {
            right.unIgnoreAll();
            left.unIgnoreAll();
        }

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
        setSpeedY(getSpeedY() + getMass());
        setPosY(getPosY() + getSpeedY());
    }

    @Override
    public void dead() {
        super.dead();
        if (right.isLastFrame() || left.isLastFrame()) {
            setStatus(DEAD);
        }
    }

    @Override
    public Rectangle getBoundVertical() {
        return new Rectangle((int) (getPosX() - 1), (int) (getPosY() - getHeight() / 2), 2, getHeight());
    }

    @Override
    public Rectangle getBoundHorizontal() {
        return new Rectangle((int) (getPosX() - getWidth() / 2), (int) (getPosY() - 1), getWidth(), 2);
    }
    
    @Override
    public Rectangle getBoundMakeDame(){
        if (right.getCurrentImage() == 12 || right.getCurrentImage() == 13 || right.getCurrentImage() == 14
                || right.getCurrentImage() == 12 || right.getCurrentImage() == 13 || right.getCurrentImage() == 14) {
            return new Rectangle((int)(getPosX() -getWidth()/2 -10), (int)(getPosY() - getHeight()/2 - 10), getWidth() + 20, getHeight() + 10);
        } else {
            return new Rectangle(0,0,0,0);
        }
    }

    private void changeDirection() {
        if (getDirection() == DIR_RIGHT) {
            setDirection(DIR_LEFT);
        } else {
            setDirection(DIR_RIGHT);
        }
    }
}
