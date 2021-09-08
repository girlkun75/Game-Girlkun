/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Manager;

import com.lai.GameState.GameWorldState;
import com.lai.Object.*;
import java.awt.Graphics2D;
import java.util.*;

/**
 *
 * @author GirlkuN
 */
public class RangeDameManager {

    private GameWorldState gameWorld;
    private List<RangeDame> lst;

    public RangeDameManager(GameWorldState gameWorld) {
        this.gameWorld = gameWorld;
        this.lst = Collections.synchronizedList(new LinkedList<RangeDame>());
    }

    public synchronized void add(RangeDame object) {
        try {
            synchronized (lst) {
                lst.add(object);
            }
        } catch (Exception e) {
        }
    }

    public synchronized void remove(RangeDame object) {
        try {
            synchronized (lst) {
                for (int id = 0; id < lst.size(); id++) {
                    RangeDame objectList = lst.get(id);
                    if (object == objectList) {
                        lst.remove(id);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public synchronized void update() {
        try {
            synchronized (lst) {
                for (RangeDame object : lst) {
                    if (object.getStatus() == EntityObject.DEAD) {
                        remove(object);
                    } else {
                        object.update();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public synchronized void draw(Graphics2D g2) {
        try {
            synchronized (lst) {
                for (RangeDame object : lst) {
                    if (!object.isOutOfCamera()) {
                        object.draw(g2);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public List<RangeDame> getLst() {
        return lst;
    }

}
