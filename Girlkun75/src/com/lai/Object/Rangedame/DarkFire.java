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
public class DarkFire extends RangeDame {

    Animation right, left;
    private float speedX;
    private float speedY;

    public DarkFire(int x, int y, float speedX, float speedY, GameWorldState gameWorld) {
        super(x, y, 16, 16, gameWorld);
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void init() {
        super.init();
        this.setDame(10);
        this.setFriction(0.0f);

        right = new Animation(GameData.getInstance().getAnimation("dark_fire"));
        left = new Animation(right).flipAllImage();

        right.setIgnoreALot(5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        left.setIgnoreALot(5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
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
                right.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 24),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 28), g2);
                right.update(System.nanoTime());
                break;
            case DIR_LEFT:
                left.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 24),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 28), g2);
                left.update(System.nanoTime());
                break;
        }
    }

    @Override
    public void attack() {
        super.attack();
    }

    public void move() {
        super.move();

        if (right.getCurrentImage() == 4 || left.getCurrentImage() == 4) {
            right.setIgnoreALot(0, 1, 2, 3, 4);
            left.setIgnoreALot(0, 1, 2, 3, 4);

            right.unIgnoreALot(5, 6, 7, 8, 9, 10);
            left.unIgnoreALot(5, 6, 7, 8, 9, 10);

            //tìm mainChar
            double deltaX = gameWorld.mobManager.getMainChar().getPosX() - getPosX();
            double deltaY = gameWorld.mobManager.getMainChar().getPosY() - getPosY();
            double a = Math.abs(deltaX / deltaY);
            float speed = 1.5f;
            float speedX = (float) Math.sqrt(speed * speed * a * a / (a * a + 1));
            float speedY = (float) Math.sqrt(speed * speed / (a * a + 1));
            if (deltaX < 0) {
                speedX = -speedX;
            }
            if (deltaY < 0) {
                speedY = -speedY;
            }

            setSpeedX(speedX);
            setSpeedY(speedY);
        }

        Rectangle horizontal = getBoundHorizontal();
        Rectangle vertical = getBoundVertical();
        if (left.getCountRepeat() == 15 || right.getCountRepeat() == 15
                || gameWorld.mobManager.checkCollisonWithEnemy(this)) {
            right.unIgnoreAll();
            left.unIgnoreAll();
            setSpeedX(0);
            setSpeedY(0);
        }
    }

    public void dead() {
        super.dead();
        if (right.isLastFrame() || left.isLastFrame()) {
            setStatus(DEAD);
        }
    }
}
