/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameGui;

import com.lai.GameEffect.GameData;

/**
 *
 * @author GirlkuN
 */
public class Window extends javax.swing.JFrame{

    private Window() {
        super();
        GameData.loadData();
        this.setTitle("GIRLKUN75");
        this.add(new Game());
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
