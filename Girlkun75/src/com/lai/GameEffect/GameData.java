/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lai.GameEffect;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author GirlkuN
 */
public class GameData {

    private static GameData instance;

    //path map
    private static final String pathMapPhysic = "data//map//mapphysic.txt";
    private static final String pathMapTiled = "data//map//maptiled.txt";
    private static final String pathMapObject = "data//map//mapobject.txt";

    //path image
    private static final String pathObjectMap = "data/img//map//object//objectmap.txt";
    private static final String pathTiled = "data//img//map//tiled//tiledmap.txt";
    private static final String pathMenu = "data//img//menu//menu.txt";
    private static final String pathInfoBar = "data//img//infobar//infobar.txt";
    private static final String pathCharacter = "data//img//character//character.txt";
    private static final String pathRangeDame = "data//img//rangedame//rangedame.txt";
    private static final String pathCat = "data//img//mob//cat//cat.txt";
    private static final String pathBogey = "data//img//mob//bogey//bogey.txt";
    private static final String pathSnakeMonter = "data//img//mob//snakemonster//snakemonster.txt";
    private static final String pathEyeMonster = "data//img//mob//eyemonster//eyemonster.txt";
    private static final String pathBringerOfDeath = "data//img//mob//bringerofdeath//bringerofdeath.txt";
    private static final String pathDeath = "data//img//mob//death//death.txt";
    private static final String pathWorm = "data//img//mob//worm//worm.txt";
    private static final String pathFireOfDeath = "data//img//mob//fireofdeath//fireofdeath.txt";

    //path animation
    private static final String pathCharacterAni = "data//img//character//animation.txt";
    private static final String pathRangeDameAni = "data//img//rangedame//animation.txt";
    private static final String pathCatAni = "data//img//mob//cat//animation.txt";
    private static final String pathBogeyAni = "data//img//mob//bogey//animation.txt";
    private static final String pathSnakeMonsterAni = "data//img//mob//snakemonster//animation.txt";
    private static final String pathEyeMonsterani = "data//img//mob//eyemonster//animation.txt";
    private static final String pathBringerOfDeathAni = "data//img//mob//bringerofdeath//animation.txt";
    private static final String pathDeathAni = "data//img//mob//death//animation.txt";
    private static final String pathWormAni = "data//img//mob//worm//animation.txt";
    private static final String pathFireOfDeathAni = "data//img//mob//fireofdeath//animation.txt";

    //propeties
    private int[][] mapPhysic;
    private int[][] mapTiled;
    private int[][] mapObject;

    private Map<String, FrameImage> frameImages;
    private Map<String, Animation> animations;

    public static GameData getInstance() {
        return instance;
    }

    public static void loadData() {
        if (instance == null) {
            instance = new GameData();
        }
    }

    private GameData() {
        this.frameImages = new Hashtable<>();
        this.animations = new Hashtable<>();
        try {
            //load map
            loadMapPhysic();
            loadMapTiled();
            loadMapObject();

            //load image
            loadFrameImage();

            //load animation
            loadAnimation();
        } catch (Exception e) {
        }
    }

    private void loadMapPhysic() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(pathMapPhysic));
        int h = Integer.parseInt(br.readLine());
        int w = Integer.parseInt(br.readLine());
        mapPhysic = new int[h][w];
        for (int y = 0; y < h; y++) {
            String[] arrLine = br.readLine().split(" ");
            for (int x = 0; x < w; x++) {
                mapPhysic[y][x] = Integer.parseInt(arrLine[x]);
            }
        }
        br.close();
    }

    private void loadMapTiled() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(pathMapTiled));
        int h = Integer.parseInt(br.readLine());
        int w = Integer.parseInt(br.readLine());
        mapTiled = new int[h][w];
        for (int y = 0; y < h; y++) {
            String[] arrLine = br.readLine().split(" ");
            for (int x = 0; x < w; x++) {
                mapTiled[y][x] = Integer.parseInt(arrLine[x]);
            }
        }
        br.close();
    }

    private void loadMapObject() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(pathMapObject));
        int h = Integer.parseInt(br.readLine());
        int w = Integer.parseInt(br.readLine());
        mapObject = new int[h][w];
        for (int y = 0; y < h; y++) {
            String[] arrLine = br.readLine().split(" ");
            for (int x = 0; x < w; x++) {
                mapObject[y][x] = Integer.parseInt(arrLine[x]);
            }
        }
        br.close();
    }

    private void loadFrameImage() throws Exception {

        BufferedReader br;
        
        //load tile
        br = new BufferedReader(new FileReader(pathTiled));
        String tile = br.readLine();
        int countTile = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countTile; i++) {
            BufferedImage image = ImageIO.read(new File(tile + "tiled (" + i + ").png"));
            FrameImage frameImage = new FrameImage(image);
            frameImages.put("tiled" + i, frameImage);
        }
        
        //load object map
        br = new BufferedReader(new FileReader(pathObjectMap));
        String object = br.readLine();
        int countObject = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countObject; i++) {
            BufferedImage image = ImageIO.read(new File(object + "object (" + i + ").png"));
            FrameImage frameImage = new FrameImage(image);
            frameImages.put("object" + i, frameImage);
        }

        //load menu
        br = new BufferedReader(new FileReader(pathMenu));
        String menu = br.readLine();
        int countMenu = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countMenu; i++) {
            BufferedImage image = ImageIO.read(new File(menu + "menu (" + i + ").png"));
            FrameImage frameImage = new FrameImage(image);
            frameImages.put("menu" + i, frameImage);
        }
        
        //load info bar
        br = new BufferedReader(new FileReader(pathInfoBar));
        String infoBar = br.readLine();
        int countInfoBar = Integer.parseInt(br.readLine());
        for(int i = 0; i < countInfoBar; i++){
            String nameBar = br.readLine();
            BufferedImage image = ImageIO.read(new File(infoBar + nameBar + ".png"));
            FrameImage frameImage = new FrameImage(image);
            frameImages.put(nameBar, frameImage);
        }

        //load rangedame
        br = new BufferedReader(new FileReader(pathRangeDame));
        String bullet = br.readLine();
        int countBullet = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countBullet; i++) {
            String[] arrBullet = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrBullet[1]); j++) {
                BufferedImage image = ImageIO.read(new File(bullet + arrBullet[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrBullet[0] + j, frameImage);
            }
        }

        //load character
        br = new BufferedReader(new FileReader(pathCharacter));
        String character = br.readLine();
        int countCharacter = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countCharacter; i++) {
            String[] arrCharacter = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrCharacter[1]); j++) {
                BufferedImage image = ImageIO.read(new File(character + arrCharacter[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrCharacter[0] + j, frameImage);
            }
        }
        
        //load cat
        br = new BufferedReader(new FileReader(pathCat));
        String cat = br.readLine();
        int countCat = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countCat; i++) {
            String[] arrCat = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrCat[1]); j++) {
                BufferedImage image = ImageIO.read(new File(cat + arrCat[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrCat[0] + j, frameImage);
            }
        }
        
        //load bogey
        br = new BufferedReader(new FileReader(pathBogey));
        String bogey = br.readLine();
        int countBogey = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countBogey; i++) {
            String[] arrBogey = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrBogey[1]); j++) {
                BufferedImage image = ImageIO.read(new File(bogey + arrBogey[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrBogey[0] + j, frameImage);
            }
        }
        
        //load snakemonster
        br = new BufferedReader(new FileReader(pathSnakeMonter));
        String snakeMonster = br.readLine();
        int countSnakeMonster = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countSnakeMonster; i++) {
            String[] arrSnakeMonster = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrSnakeMonster[1]); j++) {
                BufferedImage image = ImageIO.read(new File(snakeMonster + arrSnakeMonster[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrSnakeMonster[0] + j, frameImage);
            }
        }
        
        //load eyemonster
        br = new BufferedReader(new FileReader(pathEyeMonster));
        String eyeMonster = br.readLine();
        int countEyeMonster = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countEyeMonster; i++) {
            String[] arrEyeMonster = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrEyeMonster[1]); j++) {
                BufferedImage image = ImageIO.read(new File(eyeMonster + arrEyeMonster[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrEyeMonster[0] + j, frameImage);
            }
        }
        
        //load bringerofdeath
        br = new BufferedReader(new FileReader(pathBringerOfDeath));
        String bringerOfDeath = br.readLine();
        int countBringerOfDeath = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countBringerOfDeath; i++) {
            String[] arrBringerOfDeath = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrBringerOfDeath[1]); j++) {
                BufferedImage image = ImageIO.read(new File(bringerOfDeath + arrBringerOfDeath[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrBringerOfDeath[0] + j, frameImage);
            }
        }
        
        //load death
        br = new BufferedReader(new FileReader(pathDeath));
        String death = br.readLine();
        int countDeath = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countDeath; i++) {
            String[] arrDeath = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrDeath[1]); j++) {
                BufferedImage image = ImageIO.read(new File(death + arrDeath[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrDeath[0] + j, frameImage);
            }
        }
        
        //load worm
        br = new BufferedReader(new FileReader(pathWorm));
        String worm = br.readLine();
        int countWorm = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countWorm; i++) {
            String[] arrWorm = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrWorm[1]); j++) {
                BufferedImage image = ImageIO.read(new File(worm + arrWorm[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrWorm[0] + j, frameImage);
            }
        }
        
        //load fire of death
        br = new BufferedReader(new FileReader(pathFireOfDeath));
        String fireOfDeath = br.readLine();
        int countFireOfDeath = Integer.parseInt(br.readLine());
        for (int i = 1; i <= countFireOfDeath; i++) {
            String[] arrFireOfDeath = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(arrFireOfDeath[1]); j++) {
                BufferedImage image = ImageIO.read(new File(fireOfDeath + arrFireOfDeath[0] + " (" + j + ").png"));
                FrameImage frameImage = new FrameImage(image);
                frameImages.put(arrFireOfDeath[0] + j, frameImage);
            }
        }
        
        
        
        br.close();
//            JOptionPane.showMessageDialog(null, i+"", "!23", 1, new ImageIcon(image));
    }

    private void loadAnimation() throws Exception {

        //load character
        BufferedReader br = new BufferedReader(new FileReader(pathCharacterAni));
        int countCharacter = Integer.parseInt(br.readLine());
        for (int i = 0; i < countCharacter; i++) {
            String[] arrCharacter = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrCharacter[1]); j++) {
                ani.addFrame(frameImages.get(arrCharacter[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrCharacter[0], ani);
        }

        //load rangedame
        br = new BufferedReader(new FileReader(pathRangeDameAni));
        int countBullet = Integer.parseInt(br.readLine());
        for (int i = 0; i < countBullet; i++) {
            String[] arrBullet = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrBullet[1]); j++) {
                ani.addFrame(frameImages.get(arrBullet[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrBullet[0], ani);
        }
        
        //load cat
        br = new BufferedReader(new FileReader(pathCatAni));
        int countCat = Integer.parseInt(br.readLine());
        for (int i = 0; i < countCat; i++) {
            String[] arrCat = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrCat[1]); j++) {
                ani.addFrame(frameImages.get(arrCat[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrCat[0], ani);
        }
        
        //load bogey
        br = new BufferedReader(new FileReader(pathBogeyAni));
        int countBogey = Integer.parseInt(br.readLine());
        for (int i = 0; i < countBogey; i++) {
            String[] arrBogey = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrBogey[1]); j++) {
                ani.addFrame(frameImages.get(arrBogey[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrBogey[0], ani);
        }
        
        //load snakemonster
        br = new BufferedReader(new FileReader(pathSnakeMonsterAni));
        int countSnakeMonster = Integer.parseInt(br.readLine());
        for (int i = 0; i < countSnakeMonster; i++) {
            String[] arrSnakeMonster = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrSnakeMonster[1]); j++) {
                ani.addFrame(frameImages.get(arrSnakeMonster[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrSnakeMonster[0], ani);
        }
        
        //load eyemonster
        br = new BufferedReader(new FileReader(pathEyeMonsterani));
        int countEyeMonster = Integer.parseInt(br.readLine());
        for (int i = 0; i < countEyeMonster; i++) {
            String[] arrEyeMonster = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrEyeMonster[1]); j++) {
                ani.addFrame(frameImages.get(arrEyeMonster[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrEyeMonster[0], ani);
        }
        
        //load bringerofdeath
        br = new BufferedReader(new FileReader(pathBringerOfDeathAni));
        int countBringerOfDeath = Integer.parseInt(br.readLine());
        for (int i = 0; i < countBringerOfDeath; i++) {
            String[] arrBringeOfDeath = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrBringeOfDeath[1]); j++) {
                ani.addFrame(frameImages.get(arrBringeOfDeath[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrBringeOfDeath[0], ani);
        }
        
        //load death
        br = new BufferedReader(new FileReader(pathDeathAni));
        int countDeath = Integer.parseInt(br.readLine());
        for (int i = 0; i < countDeath; i++) {
            String[] arrDeath = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrDeath[1]); j++) {
                ani.addFrame(frameImages.get(arrDeath[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrDeath[0], ani);
        }
        
        //load worm
        br = new BufferedReader(new FileReader(pathWormAni));
        int countWorm = Integer.parseInt(br.readLine());
        for (int i = 0; i < countWorm; i++) {
            String[] arrWorm = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrWorm[1]); j++) {
                ani.addFrame(frameImages.get(arrWorm[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrWorm[0], ani);
        }
        
        //load fire of death
        br = new BufferedReader(new FileReader(pathFireOfDeathAni));
        int countFireOfDeath = Integer.parseInt(br.readLine());
        for (int i = 0; i < countFireOfDeath; i++) {
            String[] arrFireOfDeath = br.readLine().split(" ");
            String[] arrTimeDelay = br.readLine().split(" ");
            Animation ani = new Animation();
            for (int j = 1; j <= Integer.parseInt(arrFireOfDeath[1]); j++) {
                ani.addFrame(frameImages.get(arrFireOfDeath[0] + j), Double.parseDouble(arrTimeDelay[j-1]));
            }
            animations.put(arrFireOfDeath[0], ani);
        }

        br.close();
    }

    //--------------------------------------------------------------------------
    public int[][] getMapPhysic() {
        return mapPhysic;
    }

    public int[][] getMapTiled() {
        return mapTiled;
    }
    
    public int[][] getMapObject(){
        return mapObject;
    }

    public FrameImage getFrameImage(String key) {
        return frameImages.get(key);
    }

    public Animation getAnimation(String key) {
        return animations.get(key);
    }
}
