/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameEffect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author GirlkuN
 */
public class FrameImage {
    
    private BufferedImage image;

    public FrameImage() {

    }

    public FrameImage(BufferedImage image) {
        this.image = image;
    }

    public FrameImage(FrameImage frameImage) {
        this.image = new BufferedImage(frameImage.image.getWidth(), frameImage.image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) this.image.getGraphics();
        g2.drawImage(frameImage.getImage(), 0, 0, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return this.image;
    }

}
