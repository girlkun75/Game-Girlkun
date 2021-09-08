/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object;

import com.lai.GameState.GameWorldState;
import java.awt.Graphics2D;

/**
 *
 * @author GirlkuN
 */
public abstract class GameObject {

    protected GameWorldState gameWorld;

    private double posX;
    private double posY;

    public void setPosX(double x) {
        posX = x;
    }

    public void setPosY(double y) {
        posY = y;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g2);
}
