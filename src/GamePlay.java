import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anton on 3/5/2015.
 *
 */
public class GamePlay {

    int xBorder;
    int yBorder;
    int scale;

    //int bulletHitLimit = 10;

    public ArrayList<Character> characterList = new ArrayList<Character>();
    public ArrayList<Character> enemyList = new ArrayList<Character>();
    public ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    public ArrayList<Orb> orbList;
    public ArrayList<Map> mapList = new ArrayList<Map>();

    Character currentChar;
    Character currentEnemy;
    Map currentMap;

    int currentPlayerCounter = 0;
    int currentEnemyCounter = 0;

    String userName;
    String enemyName;

    boolean playerLoss = false;
    boolean enemyLoss = false;
    int enemyLives;

    boolean mapLoaded = false;

    BufferedImage healthBar;
    BufferedImage healthBarEnemy;
    BufferedImage healthBarGlow;

    BufferedImage winImg;
    BufferedImage loseImg;

    public GamePlay(int x, int y, int a){
        xBorder = x;
        yBorder = y;
        scale = a;

        try {
            healthBar = ImageIO.read(new File("resources/Objects/Life Bar.png"));
            healthBarEnemy = ImageIO.read(new File("resources/Objects/Life Bar(Left).png"));
            healthBarGlow = ImageIO.read(new File("resources/Objects/Life Bar Glow.png"));

            winImg = ImageIO.read(new File("resources/Objects/You Win.png"));
            loseImg = ImageIO.read(new File("resources/Objects/You Lose.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(LaunchGame.serverRunning){
            for (Character aCharacterList : characterList) {
                aCharacterList.xPos = currentMap.serverSpawnX;
                aCharacterList.yPos = currentMap.serverSpawnY;
            }
        }
        else if(LaunchGame.clientRunning){
            for (Character aCharacterList : characterList) {
                aCharacterList.xPos = currentMap.clientSpawnX;
                aCharacterList.yPos = currentMap.clientSpawnY;
            }
        }
    }

    //Checks for win conditions
    public void updateGame(){
        try {
            if(!currentChar.isAlive) {

                if(LaunchGame.gamePlay.currentChar.deathAnimationFinish) {
                    currentChar.canMove = false;
                    if (currentPlayerCounter >= 2) {
                        playerLoss = true;
                    }else{
                        currentPlayerCounter++;
                    }
                }
            }
            if(!currentEnemy.isAlive){
                if(LaunchGame.gamePlay.currentEnemy.deathAnimationFinish) {
                    currentEnemyCounter++;
                }
            }
        }catch (Exception e){
            //Do nothing
        }
    }

    public void draw(Graphics g){
        updateGame();

        currentChar = characterList.get(currentPlayerCounter);
        try {
            currentEnemy = enemyList.get(currentEnemyCounter);
        }catch (Exception e){
            //Do nothing
        }

        currentMap.draw(g);
        try {
            currentEnemy.draw(g);
        }catch (Exception e){
            //Do nothing
        }
        currentChar.draw(g);

        for(int i = 0; i <= orbList.size() - 1; i++){
            orbList.get(i).draw(g);
        }

        for(int i = 0; i <= bulletList.size() - 1; i++){
            bulletList.get(i).draw(g);
        }

        drawHealthBar(g);

        //Draw if game ends
        if(playerLoss){
            currentChar.canMove = false;

            Color translucentBlack = new Color(0,0,0,130);

            g.setColor(translucentBlack);
            g.fillRect(xBorder * scale, yBorder * scale, 1280 * scale, 720 * scale);
            g.drawImage(loseImg, (xBorder+440)*scale, (yBorder+210)*scale,400*scale,200*scale,null);
        }
        else if(enemyLoss){
            currentChar.tempImmunity = true;

            Color translucentBlack = new Color(0,0,0,130);

            g.setColor(translucentBlack);
            g.fillRect(xBorder * scale, yBorder * scale, 1280 * scale, 720 * scale);
            g.drawImage(winImg, (xBorder+440)*scale, (yBorder+210)*scale,400*scale,200*scale,null);
        }
        //Draw if game ends

        //Letter boxing
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, (xBorder * 2 + 1280)*scale, (yBorder)*scale);
        g.fillRect(0, 0, (xBorder)*scale, (yBorder * 2 + 720)*scale);
        g.fillRect((xBorder + 1280)*scale, 0, (xBorder)*scale, (yBorder * 2 + 720)*scale);
        g.fillRect(0, (yBorder + 720)*scale, (xBorder * 2 + 1280)*scale, (yBorder)*scale);
        //Letter boxing
    }

    public void animate(){

        currentChar = characterList.get(currentPlayerCounter);
        try {
            currentEnemy = enemyList.get(currentEnemyCounter);
        }catch (Exception e){
            //Do nothing
        }

        currentChar.animate();
        try {
            currentEnemy.animate();
        }catch(Exception e){
            //Do nothing
        }
        currentMap.animate();

        for(int i = 0; i <= orbList.size() - 1; i++){
            orbList.get(i).animate();
        }

        for(int i = 0; i <= bulletList.size() - 1; i++){
            if(bulletList.get(i).hitCounter >= bulletList.get(i).bulletHitLimit){
                bulletList.remove(i);
            }
            else {
                bulletList.get(i).animate();
            }
        }
    }

    public void drawHealthBar(Graphics g){

        //Current Player Health Bar
        g.drawImage(currentChar.icon, (xBorder + 20)*scale, (yBorder + 30*scale), 60*scale, 60*scale, null);
        if(currentChar.canUseSpecial){
            for(int i = 0; i < currentChar.livesLeft; i++){
                g.drawImage(healthBarGlow, (xBorder + 80 + (60*i))*scale, (yBorder + 40)*scale, 80*scale, 30*scale,null);
            }
        } else{
            for(int i = 0; i < currentChar.livesLeft; i++){
                g.drawImage(healthBar, (xBorder + 80 + (60*i))*scale, (yBorder + 40)*scale, 80*scale, 30*scale,null);
            }
        }
        //Current Player Health Bar

        try {
            //Enemy Player Health Bar
            g.drawImage(currentEnemy.icon, (xBorder + 1200) * scale, (yBorder + 30 * scale), 60 * scale, 60 * scale, null);
            if (currentEnemy.canUseSpecial) {
                for (int i = 0; i < enemyLives; i++) {
                    g.drawImage(healthBarGlow, (xBorder + 1100 - (60 * i)) * scale, (yBorder + 40) * scale, 80 * scale, 30 * scale, null);
                }
            } else {
                for (int i = 0; i < enemyLives; i++) {
                    g.drawImage(healthBar, (xBorder + 1100 - (60 * i)) * scale, (yBorder + 40) * scale, 80 * scale, 30 * scale, null);
                }
            }
            //Enemy Player Health Bar
        }catch (Exception e){
            //Do nothing
        }
    }

    //Adds enemy characters
    public void addEnemyCharacters(String a, String b, String c){
        ArrayList<String> enemyCharString = new ArrayList<String>();
        enemyCharString.add(a);
        enemyCharString.add(b);
        enemyCharString.add(c);

        for (String enemy : enemyCharString) {
            if (enemy.equalsIgnoreCase("Aang")) {
                enemyList.add(new Aang(xBorder, yBorder, scale, 100, 100, 45, 80));
            } else if (enemy.equalsIgnoreCase("Ariel")) {
                enemyList.add(new Ariel(xBorder, yBorder, scale, 100, 100, 50, 80));
            } else if (enemy.equalsIgnoreCase("Batman")) {
                enemyList.add(new Batman(xBorder, yBorder, scale, 100, 100, 60, 80));
            } else if (enemy.equalsIgnoreCase("Bruce Lee")) {
                enemyList.add(new BruceLee(xBorder, yBorder, scale, 100, 100, 45, 80));
            } else if (enemy.equalsIgnoreCase("Groot")) {
                enemyList.add(new Groot(xBorder, yBorder, scale, 100, 100, 50, 80));
            } else if (enemy.equalsIgnoreCase("Jake")) {
                enemyList.add(new Jake(xBorder, yBorder, scale, 100, 100, 50, 80));
            } else if (enemy.equalsIgnoreCase("Pokemon Trainer")) {
                enemyList.add(new PokemonTrainer(xBorder, yBorder, scale, 100, 100, 45, 80));
            } else if (enemy.equalsIgnoreCase("SuperMan")) {
                enemyList.add(new SuperMan(xBorder, yBorder, scale, 100, 100, 50, 80));
            } else if (enemy.equalsIgnoreCase("Goku")) {
                enemyList.add(new Goku(xBorder, yBorder, scale, 100, 100, 60, 100));
            } else if (enemy.equalsIgnoreCase("Rambo")) {
                enemyList.add(new Rambo(xBorder, yBorder, scale, 100, 100, 45, 80));
            } else if(enemy.equalsIgnoreCase("Mace Windu")){
                enemyList.add(new MaceWindu(xBorder, yBorder, scale, 100, 100, 45, 80));
            }

            System.out.println("enemy added");
        }

        for (Character anEnemyList : enemyList) {
            anEnemyList.isAnEnemy = true;
        }
    }
    //Adds enemy characters

    //Used oly when player is the client
    public void addMap(String mapReceived){
        if(mapReceived.equalsIgnoreCase("BlockRoom")){
            mapList.add(new BlockRoom(xBorder,yBorder,scale));
        }
        else if(mapReceived.equalsIgnoreCase("Dojo")){
            mapList.add(new Dojo(xBorder,yBorder,scale));
        }
        else if(mapReceived.equalsIgnoreCase("GrayRoom")){
            mapList.add(new GrayRoom(xBorder,yBorder,scale));
        }
        else if(mapReceived.equalsIgnoreCase("Neon")){
            mapList.add(new Neon(xBorder,yBorder,scale));
        }
        System.out.println("Map Loaded");
        currentMap = mapList.get(0);
        orbList = currentMap.orbSpawn;
        mapLoaded = true;
    }
    //Used oly when player is the client
}