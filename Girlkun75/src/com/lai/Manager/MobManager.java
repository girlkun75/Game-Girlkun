/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.Manager;

import com.lai.GameState.GameWorldState;
import com.lai.Object.Mobs.MainCharacter;
import com.lai.Object.*;
import java.awt.Graphics2D;
import java.util.*;

/**
 *
 * @author GirlkuN
 */
public class MobManager {

    private GameWorldState gameWorld;
    private List<Mob> lst;

    public MobManager(GameWorldState gameWorld) {
        this.gameWorld = gameWorld;
        this.lst = Collections.synchronizedList(new LinkedList<Mob>());
    }

    public synchronized void add(Mob object) {
        try {
            synchronized (lst) {
                lst.add(object);
            }
        } catch (Exception e) {
        }
    }

    public synchronized void remove(Mob object) {
        try {
            synchronized (lst) {
                for (int id = 0; id < lst.size(); id++) {
                    Mob objectList = lst.get(id);
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
                for (Mob object : lst) {
                    object.update();
                }
            }
        } catch (Exception e) {
        }
    }

    public synchronized void draw(Graphics2D g2) {
        try {
            synchronized (lst) {
                for (Mob object : lst) {
                    if (!object.isOutOfCamera()) {
                        object.draw(g2);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public synchronized boolean checkCollisonWithEnemy(EntityObject object) {
        try {
            synchronized (lst) {
                for (Mob human : lst) {
                    if (!human.isOutOfCamera()
                            && human.getStatus() != EntityObject.HURT
                            && human.getStatus() != EntityObject.DEAD
                            && object.getStatus() != EntityObject.DEAD
                            && object.getTeam() != human.getTeam()
                            && human.isCollisionWithEnemy(object.getBoundMakeDame())) {

                        if (object.getDame() != 0
                                && !human.getMiss()) {
                            human.takeDame(object.getDame());
                        }
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public synchronized MainCharacter getMainChar() {
        try {
            Mob mainChar = lst.get(0);
            if (mainChar instanceof MainCharacter) {
                return (MainCharacter) mainChar;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Mob> getLst() {
        return lst;
    }

}
