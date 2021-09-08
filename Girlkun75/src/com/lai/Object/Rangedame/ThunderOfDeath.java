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
public class ThunderOfDeath extends RangeDame {

    Animation right, left;

    public ThunderOfDeath(int x, int y, float speedX, float speedY, GameWorldState gameWorld) {
        super(x, y, 32, 60, gameWorld);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);
    }

    @Override
    public void init() {
        super.init();
        this.setFriction(0.0f);

        left = new Animation(GameData.getInstance().getAnimation("bringerofdeath_bullet"));
        right = new Animation(left).flipAllImage();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public Rectangle getBoundMakeDame() {
        Rectangle bound = new Rectangle((int) (getPosX() - 8), (int) getPosY() - 14, 16, (int) getHeight()-16);
        return bound;
    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBound(g2);
        switch (getDirection()) {
            case DIR_RIGHT:
                right.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 73),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 62), g2);
                right.update(System.nanoTime());
                break;
            case DIR_LEFT:
                left.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 65),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 62), g2);
                left.update(System.nanoTime());
                break;
        }
    }

    @Override
    public void attack() {
        super.attack();
        if (right.getCurrentImage() == 6 || right.getCurrentImage() == 7 || right.getCurrentImage() == 8
                || right.getCurrentImage() == 9 || right.getCurrentImage() == 10 || right.getCurrentImage() == 11
                || left.getCurrentImage() == 6 || left.getCurrentImage() == 7 || left.getCurrentImage() == 8
                || left.getCurrentImage() == 9 || left.getCurrentImage() == 10 || left.getCurrentImage() == 11) {
            setDame(5);
        } else {
            setDame(0);
        }
    }

    @Override
    public void move() {
        super.move();
        Rectangle horizontal = getBoundHorizontal();
        if (gameWorld.getMap().getCollisionLeft(horizontal) != null
                || gameWorld.getMap().getCollisionRight(horizontal) != null) {
            setSpeedX(0);
        }
    }

    @Override
    public void dead() {
        super.dead();
        if (right.isLastFrame() && right.getCountRepeat() == 10 && right.isLastFrame()
                || left.isLastFrame() && left.getCountRepeat() == 10 && left.isLastFrame()) {
            setStatus(DEAD);
        }
    }
}
