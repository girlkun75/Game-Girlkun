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
public class FireSpecial extends RangeDame {

    Animation right, left;
    private boolean bang = false;

    public FireSpecial(int x, int y, int dame, float speedX, float speedY, GameWorldState gameWorld) {
        super(x, y, 26, 40, gameWorld);
        this.setDame(dame);
        this.setSpeedX(speedX);
        this.setSpeedY(speedY);
    }

    @Override
    public void init() {
        super.init();
        this.setFriction(0.05f);

        right = new Animation(GameData.getInstance().getAnimation("special_fire"));
        left = new Animation(right).flipAllImage();

        right.setIgnoreALot(3, 4, 5, 6, 7, 8);
        left.setIgnoreALot(3, 4, 5, 6, 7, 8);
    }

    @Override
    public void update() {
        super.update();

    }

//    @Override
//    public Rectangle getBoundMakeDame() {
//        Rectangle bound = new Rectangle((int) (getPosX() - getWidth() / 2), (int) (getPosY() - getHeight() / 2), getWidth(), getHeight() + 5);
//        return bound;
//    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBound(g2);
        switch (getDirection()) {
            case DIR_RIGHT:
                right.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 22),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 27), g2);
                right.update(System.nanoTime());
                break;
            case DIR_LEFT:
                left.drawImage((int) (getPosX() - gameWorld.camera.getPosX() - 38),
                        (int) (getPosY() - gameWorld.camera.getPosY() - 27), g2);
                left.update(System.nanoTime());
                break;
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
            if(!bang){
                bang = true;
                right.setCurrentImage(3);
                left.setCurrentImage(3);
            }
        }
        if (right.getCountRepeat() == 2 || left.getCountRepeat() == 2) {
            left.unIgnoreAll();
            right.unIgnoreAll();
        }

        if (left.getCurrentImage() == 3 || right.getCurrentImage() == 3) {
            setSpeedX(0);
        }
    }

    @Override
    public void dead() {
        if (right.isLastFrame() || left.isLastFrame()) {
            setStatus(DEAD);
        }
    }

}
