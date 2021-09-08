/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameState;

import com.lai.GameEffect.GameData;
import com.lai.GameGui.Game;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author GirlkuN
 */
public class MenuInGameState extends GameState {

    private int currentChoice = 0;
    private BufferedImage[] options = new BufferedImage[3];
    private int[] localY = {300, 370, 440};
    private BufferedImage continue1, continue2, menu1, menu2, quit1, quit2;
    private BufferedImage logo;
    private BufferedImage gameover;

    public MenuInGameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        try {

            continue1 = GameData.getInstance().getFrameImage("menu1").getImage();
            continue2 = GameData.getInstance().getFrameImage("menu2").getImage();
            menu1 = GameData.getInstance().getFrameImage("menu9").getImage();
            menu2 = GameData.getInstance().getFrameImage("menu10").getImage();
            quit1 = GameData.getInstance().getFrameImage("menu7").getImage();
            quit2 = GameData.getInstance().getFrameImage("menu8").getImage();

            options[0] = continue1;
            options[1] = menu1;
            options[2] = quit1;

            logo = ImageIO.read(new File("data//img//bg//logo.png"));
            gameover = ImageIO.read(new File("data//img//bg//gameover.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }
    
    @Override
    public void draw(Graphics2D g2) {
        
        
        
        if (!gsm.gameOver) {
            g2.drawImage(logo, Game.WIDTH_SCREEN / 2 - logo.getWidth() / 2, 50, null);
        } else {
            g2.drawImage(gameover, Game.WIDTH_SCREEN / 2 - gameover.getWidth() / 2, 10, null);
        }

        g2.drawImage(continue2, Game.WIDTH_SCREEN / 2 - continue2.getWidth() / 2, localY[0], null);
        g2.drawImage(menu2, Game.WIDTH_SCREEN / 2 - menu2.getWidth() / 2, localY[1], null);
        g2.drawImage(quit2, Game.WIDTH_SCREEN / 2 - quit2.getWidth() / 2, localY[2], null);
        g2.drawImage(options[currentChoice], Game.WIDTH_SCREEN / 2 - options[currentChoice].getWidth() / 2, localY[currentChoice], null);

    }

    public void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.GAMEWORLDSTATE, false);
        }
        if (currentChoice == 1) {
            gsm.setState(GameStateManager.MENUSTATE, false);
        }
        if (currentChoice == 2) {
            System.exit(0);
        }
        currentChoice = 0;
    }

    public void setCurrentChoice(int currentChoice) {
        this.currentChoice = currentChoice;
    }

    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_ENTER:
                select();
                break;
            case KeyEvent.VK_J:
                select();
                break;
            case KeyEvent.VK_UP:
                currentChoice--;
                if (currentChoice < 0) {
                    currentChoice = options.length - 1;
                }
                break;
            case KeyEvent.VK_W:
                currentChoice--;
                if (currentChoice < 0) {
                    currentChoice = options.length - 1;
                }
                break;
            case KeyEvent.VK_DOWN:
                currentChoice++;
                if (currentChoice > options.length - 1) {
                    currentChoice = 0;
                }
                break;
            case KeyEvent.VK_S:
                currentChoice++;
                if (currentChoice > options.length - 1) {
                    currentChoice = 0;
                }
                break;
        }
    }

    @Override
    public void keyReleased(int k) {
    }

}
