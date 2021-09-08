/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameGui;

import com.lai.GameState.GameStateManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author GirlkuN
 */
public class Game extends javax.swing.JPanel implements Runnable, KeyListener, MouseListener {

    public static final int WIDTH_SCREEN = 960;
    public static final int HEIGHT_SCREEN = 510;

    private final int FPS = 120;
    private final long TIMETARGET = 1000000000 / FPS;

    private Thread gameLoop, gameUpdate;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g2;

    private GameStateManager gsm;

    public Game() {
        super();
        this.setPreferredSize(new java.awt.Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));
        this.setFocusable(true);
        this.requestFocus();
        init();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (gameLoop == null) {
            gameLoop = new Thread(this);
            addKeyListener(this);
            addMouseListener(this);
            gameLoop.start();
            gameUpdate.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH_SCREEN, HEIGHT_SCREEN, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) image.getGraphics();
        running = true;
        gsm = new GameStateManager();

        gameUpdate = new Thread(() -> {
            double start;
            double elapsed;
            double wait;
            while (running) {
                start = System.nanoTime();
                update();
                elapsed = System.nanoTime() - start;
                wait = TIMETARGET - elapsed;
                try {
                    if (wait < 0) {
                        Thread.sleep(2);
                    } else {
                        Thread.sleep((int) (wait / 1000000));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void run() {
        double start;
        double elapsed;
        double wait;

        while (running) {

//            System.out.println(gsm.gameWorldState.mobManager.getLst().size());
            start = System.nanoTime();

//            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = TIMETARGET - elapsed;

//            System.out.println(wait / 1000000);
            try {
                if (wait < 0) {
                    Thread.sleep(2);
                } else {
                    Thread.sleep((int) (wait / 1000000));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
//        double start = System.nanoTime();
        gsm.draw(g2);
//        double end = System.nanoTime();
//        System.out.println((end - start)/1000000);
    }

    private void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

    //--------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {

        switch (gsm.getCurrentState()) {
            case GameStateManager.MENUSTATE:
                if (e.getY() >= 301 && e.getY() <= 358 && e.getX() >= 377 && e.getX() <= 582) {
                    gsm.menuState.setCurrentChoice(0);
                    gsm.menuState.select();
                }

                if (e.getY() >= 371 && e.getY() <= 427 && e.getX() >= 377 && e.getX() <= 582) {
                    gsm.menuState.setCurrentChoice(1);
                    gsm.menuState.select();
                }

                if (e.getY() >= 442 && e.getY() <= 492 && e.getX() >= 377 && e.getX() <= 582) {
                    gsm.menuState.setCurrentChoice(2);
                    gsm.menuState.select();
                }
                break;
            case GameStateManager.GAMEWORLDSTATE:
                System.out.println(e.getX() + "--" + e.getY());

                if (e.getY() >= 10 && e.getY() <= 45 && e.getX() >= 915 && e.getX() <= 950) {
                    gsm.setCurrentState(GameStateManager.MENUINGAMESTATE);
                }

                break;
            case GameStateManager.MENUINGAMESTATE:
                if (e.getY() >= 301 && e.getY() <= 358 && e.getX() >= 377 && e.getX() <= 582) {
                    gsm.menuInGameState.setCurrentChoice(0);
                    gsm.menuInGameState.select();
                }

                if (e.getY() >= 371 && e.getY() <= 427 && e.getX() >= 377 && e.getX() <= 582) {
                    gsm.menuInGameState.setCurrentChoice(1);
                    gsm.menuInGameState.select();
                }

                if (e.getY() >= 442 && e.getY() <= 492 && e.getX() >= 377 && e.getX() <= 582) {
                    gsm.menuInGameState.setCurrentChoice(2);
                    gsm.menuInGameState.select();
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
