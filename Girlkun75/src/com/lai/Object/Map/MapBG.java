/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Object.Map;

import com.lai.GameEffect.*;
import com.lai.GameState.GameWorldState;
import com.lai.Object.*;
import java.awt.Graphics2D;

/**
 *
 * @author GirlkuN
 */
public class MapBG extends GameObject {

    private final int tileSize = 32;

    private Background bg;
    private Background cloud;

    private int[][] mapTiled;
    private int[][] mapObject;

    public MapBG(GameWorldState gameWorld) {
        this.gameWorld = gameWorld;
        init();
    }

    @Override
    public void init() {
        setPosX(0);
        setPosY(0);
        mapTiled = GameData.getInstance().getMapTiled();
        mapObject = GameData.getInstance().getMapObject();
        
//        for(int i = 0; i < mapObject.length; i++){
//            for(int j = 0; j < mapObject[i].length; j++){
//                System.out.print(mapObject[i][j] + "--");
//            }
//            System.out.println("");
//        }

        bg = new Background("data//img//bg//Background.png", 1);
        bg.setVector(0, 0);
        cloud = new Background("data//img//bg//cloud.png", 1);
        cloud.setVector(-0.05f, 0);
    }

    @Override
    public void update() {
        bg.update();
        if (gameWorld.mobManager.getMainChar() != null) {
            bg.setVector(-gameWorld.mobManager.getMainChar().getSpeedX() / 20, 0);
        } else {
            bg.setVector(-0.1f, 0);
        }

        cloud.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        bg.drawFullScreen(g2);
        cloud.draw(g2);

        //draw tiled
        Camera camera = gameWorld.camera;
        for (int y = 0; y < mapTiled.length; y++) {
            for (int x = 0; x < mapTiled[y].length; x++) {
                if (mapTiled[y][x] != 0
                        && (camera.getPosX() <= x * tileSize + tileSize)
                        && (camera.getPosY() <= y * tileSize + tileSize)
                        && (camera.getPosX() + camera.getWidth() >= x * tileSize - tileSize)
                        && (camera.getPosY() + camera.getHeight() >= y * tileSize - tileSize)) {
                    g2.drawImage(GameData.getInstance().getFrameImage("tiled" + mapTiled[y][x]).getImage(),
                            (int) (x * tileSize - getPosX() - gameWorld.camera.getPosX()),
                            (int) (y * tileSize - getPosY() - gameWorld.camera.getPosY()),
                            tileSize, tileSize, null);
                }
            }
        }
        for (int y = 0; y < mapObject.length; y++) {
            for (int x = 0; x < mapObject[y].length; x++) {
                if (mapObject[y][x] != 0
                        && (camera.getPosX() <= x * tileSize + tileSize)
                        && (camera.getPosY() <= y * tileSize + tileSize)
                        && (camera.getPosX() + camera.getWidth() >= x * tileSize - tileSize)
                        && (camera.getPosY() + camera.getHeight() >= y * tileSize - tileSize)) {
                    g2.drawImage(GameData.getInstance().getFrameImage("object" + mapObject[y][x]).getImage(),
                            (int) (x * tileSize - getPosX() - gameWorld.camera.getPosX()),
                            (int) (y * tileSize - getPosY() - gameWorld.camera.getPosY()),
                            tileSize, tileSize, null);
                }
            }
        }
    }

}
