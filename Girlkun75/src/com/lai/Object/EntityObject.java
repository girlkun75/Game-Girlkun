/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object;

import com.lai.GameState.GameWorldState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public abstract class EntityObject extends GameObject {

    //status
    private int status;
    public static final int ALIVE = 7;
    public static final int DEAD = 5;
    public static final int HURT = 0;

    //direction
    private int direction;
    public static final int DIR_LEFT = 7;
    public static final int DIR_RIGHT = 5;

    //team
    private int team;
    public static final int TEAM_GIRL = 7;
    public static final int TEAM_KUN = 5;

    //body
    private int width;
    private int height;

    //physic
    private float speedX;
    private float speedY;
    private float mass;

    //properties
    private int dame;
    private int maxHP;
    private int hp;
    private int maxMP;
    private int mp;

    public EntityObject(int x, int y, int width, int height, float mass, GameWorldState gameWorld) {
        setPosX(x);
        setPosY(y);
        this.width = width;
        this.height = height;
        this.mass = mass;
        this.gameWorld = gameWorld;
        init();
    }

    public Rectangle getBound() {
        return new Rectangle((int) (getPosX() - getWidth() / 2), (int) (getPosY() - getHeight() / 2), getWidth(), getHeight());
    }

    public Rectangle getBoundVertical() {
        Rectangle bound = new Rectangle((int) (getPosX() - 8), (int) (getPosY() - getHeight() / 2), 16, (int) getHeight() + 3);
        return bound;
    }

    public Rectangle getBoundHorizontal() {
        Rectangle bound = new Rectangle((int) (getPosX() - getWidth() / 2), (int) (getPosY()), getWidth(), 10);
        return bound;
    }

    public Rectangle getBoundMakeDame() {
        return getBoundHorizontal();
    }
    
    public Rectangle getBoundTakeDame(){
        return getBoundVertical();
    }

    public boolean isCollisionWithEnemy(Rectangle enemy) {
        return getBoundTakeDame().intersects(enemy);
    }

    public boolean isOutOfCamera() {
        if (getPosX() - getWidth()/2 - gameWorld.camera.getPosX() > gameWorld.camera.getWidth()
                || (getPosX() + getWidth()/2 + getWidth()) - gameWorld.camera.getPosX() < 0
                || getPosY() - getHeight()/2 - gameWorld.camera.getPosY() > gameWorld.camera.getHeight()
                || (getPosY() + getHeight()/2 + getHeight()) - gameWorld.camera.getPosY() < 0) {
            return true;
        } else {
            return false;
        }
    }

    public void drawBound(Graphics2D g2) {
        //bound cơ thể
        Rectangle bound = getBound();
        //bound kiểm tra chạm tường
        Rectangle vertical = getBoundVertical();
        Rectangle horizontal = getBoundHorizontal();
        //bound tạo sát thương
        Rectangle boundMakeDame = getBoundMakeDame();
        //bound nhận sát thương
        Rectangle boundTakeDame = getBoundTakeDame();

        g2.setColor(Color.YELLOW);
        g2.drawRect((int) (vertical.x - gameWorld.camera.getPosX()), (int) (vertical.y - gameWorld.camera.getPosY()), vertical.width, vertical.height);
        g2.drawRect((int) (horizontal.x - gameWorld.camera.getPosX()), (int) (horizontal.y - gameWorld.camera.getPosY()), horizontal.width, horizontal.height);

        g2.setColor(Color.BLUE);
        g2.drawRect((int) (bound.x - gameWorld.camera.getPosX()), (int) (bound.y - gameWorld.camera.getPosY()), bound.width, bound.height);
        g2.setColor(Color.GREEN);
        g2.drawRect((int) (boundTakeDame.x - gameWorld.camera.getPosX()), (int) (boundTakeDame.y - gameWorld.camera.getPosY()), boundTakeDame.width, boundTakeDame.height);
        g2.setColor(Color.RED);
        g2.drawRect((int) (boundMakeDame.x - gameWorld.camera.getPosX()), (int) (boundMakeDame.y - gameWorld.camera.getPosY()), boundMakeDame.width, boundMakeDame.height);
    }

    @Override
    public void update() {
        if (getHp() <= 0) {
            setStatus(DEAD);
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

}
