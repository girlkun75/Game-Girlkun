/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object;

import com.lai.GameGui.Game;
import com.lai.GameState.GameWorldState;
import com.lai.Object.Mobs.MainCharacter;
import java.awt.Graphics2D;

/**
 *
 * @author GirlkuN
 */
public final class Camera extends GameObject {

    private int width;
    private int height;

    private final int rangeTop = Game.HEIGHT_SCREEN / 2 - 50;
    private final int rangeBot = Game.HEIGHT_SCREEN / 2 + 50;
    private final int rangeLeft = Game.WIDTH_SCREEN / 2 - 200;
    private final int rangeRight = Game.WIDTH_SCREEN / 2 + 100;

    private boolean islook;

    public Camera(GameWorldState gameWorld) {
        this.gameWorld = gameWorld;
        init();
    }

    @Override
    public void init() {
        this.width = Game.WIDTH_SCREEN;
        this.height = Game.HEIGHT_SCREEN;
    }

    @Override
    public void update() {
        if (!gameWorld.lookCamera) {
            MainCharacter character = gameWorld.mobManager.getMainChar();
            if (character != null) {
                if (character.getPosX() - getPosX() >= rangeRight && gameWorld.getMap().getWidthMap() - getPosX() > Game.WIDTH_SCREEN) {
                    setPosX(character.getPosX() - rangeRight);
                } else if (character.getPosX() - getPosX() <= rangeLeft && getPosX() > 0) {
                    setPosX(character.getPosX() - rangeLeft);
                }
                if (character.getPosY() - getPosY() >= rangeBot && gameWorld.getMap().getHeightMap() - getPosY() > Game.HEIGHT_SCREEN + 10) {
                    setPosY(character.getPosY() - rangeBot);
                } else if (character.getPosY() - getPosY() <= rangeTop && getPosY() > 0) {
                    setPosY(character.getPosY() - rangeTop);
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

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

}
