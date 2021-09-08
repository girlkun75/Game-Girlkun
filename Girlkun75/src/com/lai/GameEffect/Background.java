/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameEffect;

import com.lai.GameGui.Game;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author GirlkuN
 */
public class Background {

    private BufferedImage image;
    private float x;
    private float y;
    private float dx;
    private float dy;

    private float moveScale;

    public Background(String url, float moveScale) {
        try {
            image = ImageIO.read(new File(url));
            this.moveScale = moveScale;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(float x, float y) {
        this.x = (x * moveScale) % Game.WIDTH_SCREEN;
        this.y = (y * moveScale) % Game.HEIGHT_SCREEN;
    }

    public void setVector(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
        if (x < -Game.WIDTH_SCREEN || x > Game.WIDTH_SCREEN) {
            x = 0;
        }
    }

    public void drawFullScreen(Graphics2D g2) {
        g2.drawImage(image, (int) x, (int) y, Game.WIDTH_SCREEN, Game.HEIGHT_SCREEN, null);
        if (x < 0) {
            g2.drawImage(image, (int) (x + Game.WIDTH_SCREEN), (int) y, Game.WIDTH_SCREEN, Game.HEIGHT_SCREEN, null);
        }
        if (x > 0) {
            g2.drawImage(image, (int) (x - Game.WIDTH_SCREEN), (int) y, Game.WIDTH_SCREEN, Game.HEIGHT_SCREEN, null);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g2.drawImage(image, (int) (x + Game.WIDTH_SCREEN), (int) y, null);
        }
        if (x > 0) {
            g2.drawImage(image, (int) (x - Game.WIDTH_SCREEN), (int) y, null);
        }
    }
}
