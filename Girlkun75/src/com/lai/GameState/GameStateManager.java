/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameState;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author GirlkuN
 */
public class GameStateManager{

    public List<GameState> gameStates;
    private int currentState;
    public boolean gameOver;

    public static final int MENUSTATE = 0;
    public static final int GAMEWORLDSTATE = 1;
    public static final int MENUINGAMESTATE = 2;
    
    public MenuState menuState;
    public GameWorldState gameWorldState;
    public MenuInGameState menuInGameState;

    public GameStateManager() {
        gameStates = new ArrayList<>();

        currentState = MENUSTATE;
        gameOver = false;
        
        menuState = new MenuState(this);
        gameWorldState = new GameWorldState(this);
        menuInGameState = new MenuInGameState(this);
        gameStates.add(menuState);
        gameStates.add(gameWorldState);
        gameStates.add(menuInGameState);
    }

    public void setState(int state, boolean reset) {
        if(state == GAMEWORLDSTATE && reset){
            gameStates.set(GAMEWORLDSTATE, new GameWorldState(this));
        }
        currentState = state;
    }
    
    public int getCurrentState(){
        return currentState;
    }
    
    public void setCurrentState(int currentState){
        this.currentState = currentState;
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int key) {
        gameStates.get(currentState).keyPressed(key);
    }

    public void keyReleased(int key) {
        gameStates.get(currentState).keyReleased(key);
    }

}
