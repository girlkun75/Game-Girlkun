/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameState;

/**
 *
 * @author GirlkuN
 */
public abstract class GameState {
    
    protected GameStateManager gsm;
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g2);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
}
