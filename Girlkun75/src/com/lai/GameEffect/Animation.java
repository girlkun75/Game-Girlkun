/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameEffect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GirlkuN
 */
public class Animation {

    private List<FrameImage> frameImages;
    private List<Double> timeDelays;
    private List<Boolean> ignoreFrames;

    private boolean repeat;
    private int currentFrame;
    private long beginTime;
    
    private int countRepeat;

    public Animation() {
        this.frameImages = new ArrayList<FrameImage>();
        this.timeDelays = new ArrayList<Double>();
        this.ignoreFrames = new ArrayList<Boolean>();

        this.repeat = true;
        this.currentFrame = -1;
        this.beginTime = 0;
        this.countRepeat = 0;
    }

    public Animation(Animation ani) {
        this.repeat = true;
        this.currentFrame = 0;
        this.beginTime = 0;
        this.countRepeat = 0;

        timeDelays = new ArrayList<Double>();
        for (Double d : ani.timeDelays) {
            timeDelays.add(d);
        }

        ignoreFrames = new ArrayList<Boolean>();
        for (boolean b : ani.ignoreFrames) {
            ignoreFrames.add(b);
        }

        frameImages = new ArrayList<FrameImage>();
        for (FrameImage f : ani.frameImages) {
            frameImages.add(new FrameImage(f));
        }
    }

    public void addFrame(FrameImage frameImage, double timeDelay) {
        this.currentFrame = 0;
        this.frameImages.add(frameImage);
        this.timeDelays.add(timeDelay);
        this.ignoreFrames.add(false);
    }

    public void setIgnoreFrame(int id) {
        if (id >= 0 && id <= ignoreFrames.size() - 1) {
            ignoreFrames.set(id, true);
        }
    }

    public void unIgnoreFrame(int id) {
        if (id >= 0 && id <= ignoreFrames.size() - 1) {
            ignoreFrames.set(id, false);
        }
    }

    public void setIgnoreALot(int... id) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] >= 0 && id[i] <= ignoreFrames.size() - 1) {
                ignoreFrames.set(id[i], true);
            }
        }
    }
    
    public void unIgnoreALot(int...id){
        for (int i = 0; i < id.length; i++) {
            if (id[i] >= 0 && id[i] <= ignoreFrames.size() - 1) {
                ignoreFrames.set(id[i], false);
            }
        }
    }

    public void unIgnoreAll() {
        for (int i = 0; i < ignoreFrames.size(); i++) {
            ignoreFrames.set(i, false);
        }
    }

    public void update(long deltaTime) {
        if (beginTime == 0) {
            beginTime = deltaTime;
        } else {
            if (deltaTime - beginTime > timeDelays.get(currentFrame)) {
                nextFrame();
                beginTime = deltaTime;
            }
        }
    }

    public boolean isLastFrame() {
        if (currentFrame == frameImages.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    private void nextFrame() {
        if (currentFrame >= frameImages.size() - 1) {
            if (repeat) {
                currentFrame = 0;
                countRepeat++;
            }
        } else {
            currentFrame++;
        }
        if (ignoreFrames.get(currentFrame)) {
            nextFrame();
        }
    }

    public Animation flipAllImage() {
        for (int i = 0; i < frameImages.size(); i++) {
            BufferedImage image = frameImages.get(i).getImage();
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            frameImages.get(i).setImage(image);
        }
        return this;
    }

    private BufferedImage getCurrentFrameImage() {
        return frameImages.get(currentFrame).getImage();
    }

    public void drawImage(int x, int y, Graphics2D g2) {
        BufferedImage image = getCurrentFrameImage();
        g2.drawImage(image, x, y, null);
    }

    public void reset() {
        setCountRepeat(0);
        setCurrentImage(0);
        setRepeat(true);
    }

    //--------------------------------------------------------------------------
    public int getLastIndex(){
        return frameImages.size()-1;
    }
    
    private List<FrameImage> getFrameImages() {
        return frameImages;
    }

    private List<Double> getTimeDelays() {
        return timeDelays;
    }

    private List<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public int getCurrentImage() {
        return currentFrame;
    }

    public void setCurrentImage(int currentImage) {
        this.currentFrame = currentImage;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }
    
    public int getCountRepeat(){
        return countRepeat;
    }
    
    public void setCountRepeat(int countRepeat){
        this.countRepeat = countRepeat;
    }

}
