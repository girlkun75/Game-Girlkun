/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameState;

import com.lai.GameEffect.*;
import com.lai.GameGui.Game;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author GirlkuN
 */
public class MenuState extends GameState {

    private Background bg;
    private Background cloud;

    private int currentChoice = 0;
    private BufferedImage[] options = new BufferedImage[3];
    private int[] localY = {300, 370, 440};
    private BufferedImage start1, start2, help1, help2, quit1, quit2;
    private BufferedImage logo;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        try {
            bg = new Background("data//img//bg//Background.png", 1);
            bg.setVector(-0.8f, 0);
            cloud = new Background("data//img//bg//cloud.png", 1);
            cloud.setVector(-1.5f, 0);

            start1 = GameData.getInstance().getFrameImage("menu3").getImage();
            start2 = GameData.getInstance().getFrameImage("menu4").getImage();
            help1 = GameData.getInstance().getFrameImage("menu5").getImage();
            help2 = GameData.getInstance().getFrameImage("menu6").getImage();
            quit1 = GameData.getInstance().getFrameImage("menu7").getImage();
            quit2 = GameData.getInstance().getFrameImage("menu8").getImage();

            options[0] = start1;
            options[1] = help1;
            options[2] = quit1;

            logo = ImageIO.read(new File("data//img//bg//logo.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        bg.update();
        cloud.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        bg.drawFullScreen(g2);
        cloud.draw(g2);

        g2.drawImage(logo, Game.WIDTH_SCREEN / 2 - logo.getWidth() / 2, 50, null);
        
        g2.drawImage(start2, Game.WIDTH_SCREEN / 2 - start2.getWidth() / 2, localY[0], null);
        g2.drawImage(help2, Game.WIDTH_SCREEN / 2 - help2.getWidth() / 2, localY[1], null);
        g2.drawImage(quit2, Game.WIDTH_SCREEN / 2 - quit2.getWidth() / 2, localY[2], null);
        g2.drawImage(options[currentChoice], Game.WIDTH_SCREEN / 2 - options[currentChoice].getWidth() / 2, localY[currentChoice], null);
    }

    public void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.GAMEWORLDSTATE, true);
        }
        if (currentChoice == 1) {
            JOptionPane.showMessageDialog(null, "Help con cặc tự mò mà chơi!...");
        }
        if (currentChoice == 2) {
            System.exit(0);
        }
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
