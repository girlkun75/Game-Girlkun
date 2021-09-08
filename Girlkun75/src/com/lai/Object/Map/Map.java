/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Map;

import com.lai.GameEffect.GameData;
import com.lai.GameState.GameWorldState;
import com.lai.Object.GameObject;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author GirlkuN
 */
public class Map extends GameObject {

    private final int tileSize = 32;

    private int[][] mapPhysic;

    public Map(GameWorldState gameWorld) {
        this.gameWorld = gameWorld;
        init();
    }

    public int getTileSize() {
        return tileSize;
    }

    //--------------------------------------------------------------------------
    public boolean checkCollisionWithWater(Rectangle o){
        int xStart = o.x / tileSize;
        int xEnd = (o.x + o.width) / tileSize;
        xStart -= 2;
        xEnd += 2;
        if (xStart < 0) {
            xStart = 0;
        }
        if (xEnd > mapPhysic[0].length - 1) {
            xEnd = mapPhysic[0].length - 1;
        }

        int y = (o.y + o.height) / tileSize;
        for (int x = xStart; x <= xEnd; x++) {
            if (mapPhysic[y][x] == 2) {
                Rectangle r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(o)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkCollisionWithIce(Rectangle o){
        int xStart = o.x / tileSize;
        int xEnd = (o.x + o.width) / tileSize;
        xStart -= 2;
        xEnd += 2;
        if (xStart < 0) {
            xStart = 0;
        }
        if (xEnd > mapPhysic[0].length - 1) {
            xEnd = mapPhysic[0].length - 1;
        }

        int y = (o.y + o.height) / tileSize;
        for (int x = xStart; x <= xEnd; x++) {
            if (mapPhysic[y][x] == 3) {
                Rectangle r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(o)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBot(Rectangle o) {
        int xStart = o.x / tileSize;
        int xEnd = (o.x + o.width) / tileSize;
        xStart -= 2;
        xEnd += 2;
        if (xStart < 0) {
            xStart = 0;
        }
        if (xEnd > mapPhysic[0].length - 1) {
            xEnd = mapPhysic[0].length - 1;
        }

        int y = (o.y + o.height) / tileSize;
        for (int x = xStart; x <= xEnd; x++) {
            if (mapPhysic[y][x] != 0) {
                Rectangle r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(o)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Rectangle getCollisionTop(Rectangle o) {
        int xStart = o.x / tileSize;
        int xEnd = (o.x + o.width) / tileSize;
        xStart -= 2;
        xEnd += 2;
        if (xStart < 0) {
            xStart = 0;
        }
        if (xEnd > mapPhysic[0].length - 1) {
            xEnd = mapPhysic[0].length - 1;
        }

        int y = o.y / tileSize;
        for (int x = xStart; x <= xEnd; x++) {
            if (mapPhysic[y][x] != 0) {
                Rectangle r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(o)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Rectangle getCollisionLeft(Rectangle o) {
        int x = o.x / tileSize;
        int yStart = o.y / tileSize;
        int yEnd = (o.y + o.height) / tileSize;
        yStart -= 2;
        yEnd += 2;
        if (yStart < 0) {
            yStart = 0;
        }
        if (yEnd > mapPhysic.length - 1) {
            yEnd = mapPhysic.length - 1;
        }

        for (int y = yStart; y <= yEnd; y++) {
            if (mapPhysic[y][x] != 0) {
                Rectangle r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(o)) {
                    return r;
                }
            }
        }
        return null;
    }

    public Rectangle getCollisionRight(Rectangle o) {
        int x = (o.x + o.width) / tileSize;
        int yStart = o.y / tileSize;
        int yEnd = (o.y + o.height) / tileSize;
        yStart -= 2;
        yEnd += 2;
        if (yStart < 0) {
            yStart = 0;
        }
        if (yEnd > mapPhysic.length - 1) {
            yEnd = mapPhysic.length - 1;
        }

        for (int y = yStart; y <= yEnd; y++) {
            if (mapPhysic[y][x] != 0) {
                Rectangle r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                if (r.intersects(o)) {
                    return r;
                }
            }
        }
        return null;
    }
    //--------------------------------------------------------------------------

    public int getHeightMap() {
        return tileSize * mapPhysic.length;
    }

    public int getWidthMap() {
        return tileSize * mapPhysic[0].length;
    }

    @Override
    public void init() {
        mapPhysic = GameData.getInstance().getMapPhysic();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.red);
//        for (int y = 0; y < mapPhysic1_1.length; y++) {
//            for (int x = 0; x < mapPhysic1_1[y].length; x++) {
//                if(mapPhysic1_1[y][x] != 0){
//                    g2.fillRect(x*2, Game.HEIGHT_SCREEN-100+y*2, 2, 2);
//                }
//            }
//        }
    }
}
