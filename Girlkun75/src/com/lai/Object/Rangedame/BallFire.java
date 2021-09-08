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
public class BallFire extends RangeDame {

    Animation right, left;
    private boolean bang = false;

    public BallFire(int x, int y, float speedX, float speedY, GameWorldState gameWorld) {
        super(x, y, 12, 12, gameWorld);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);
    }

    @Override
    public void init() {
        super.init();
        this.setDame(15);

        right = new Animation(GameData.getInstance().getAnimation("ball_fire"));
        left = new Animation(right).flipAllImage();

        right.setIgnoreALot(6, 7, 8, 9, 10, 11, 12);
        left.setIgnoreALot(6, 7, 8, 9, 10, 11, 12);
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
                right.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 23),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 22), g2);
                right.update(System.nanoTime());
                break;
            case DIR_LEFT:
                left.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 20),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 22), g2);
                left.update(System.nanoTime());
                break;
        }
    }
    
    @Override
    public void attack(){
        super.attack();
        if(right.getCurrentImage() == 6 || left.getCurrentImage() == 6){
            setDame(1);
        } else {
            setDame(0);
        }
    }

    @Override
    public void move() {
        super.move();
        Rectangle horizontal = getBoundHorizontal();
        if (gameWorld.getMap().getCollisionLeft(horizontal) != null
                || gameWorld.getMap().getCollisionRight(horizontal) != null
                || gameWorld.mobManager.checkCollisonWithEnemy(this)) {
            setSpeedX(0);
            right.unIgnoreAll();
            left.unIgnoreAll();
            if (!bang) {
                bang = true;
                right.setCurrentImage(6);
                left.setCurrentImage(6);
            }
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
