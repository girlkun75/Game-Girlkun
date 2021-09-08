/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Rangedame;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import com.lai.Object.RangeDame;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class FireNormal extends RangeDame {

    Animation right, left;

    public FireNormal(int x, int y, int dame, float speedX, float speedY, GameWorldState gameWorld) {
        super(x, y, 32, 12, gameWorld);
        this.setDame(dame);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);
    }

    @Override
    public void init() {
        super.init();
        this.setFriction(0.015f);

        right = new Animation(GameData.getInstance().getAnimation("normal_fire"));
        left = new Animation(right).flipAllImage();

        right.setIgnoreALot(3, 4, 5, 6, 7, 8);
        left.setIgnoreALot(3, 4, 5, 6, 7, 8);
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
                right.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 13),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 19), g2);
                right.update(System.nanoTime());
                break;
            case DIR_LEFT:
                left.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 15),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 19), g2);
                left.update(System.nanoTime());
                break;
        }
    }

    @Override
    public void move() {
        super.move();
        Rectangle horizontal = getBoundHorizontal();
        if (gameWorld.getMap().getCollisionLeft(horizontal) != null
                || gameWorld.getMap().getCollisionRight(horizontal) != null) {
            setSpeedX(0);
            right.unIgnoreAll();
            left.unIgnoreAll();
        }

        if (right.getCountRepeat() == 2 || left.getCountRepeat() == 2) {
            right.unIgnoreAll();
            left.unIgnoreAll();
        }
    }

    @Override
    public void dead() {
        super.dead();
        if (right.isLastFrame() || left.isLastFrame()) {
            setStatus(DEAD);
        }
    }

}
