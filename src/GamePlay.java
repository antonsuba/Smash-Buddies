import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Anton on 3/5/2015.
 *
 */
public class GamePlay {

    int xBorder;
    int yBorder;
    int scale;

    int bulletHitLimit = 10;

    public ArrayList<Character> characterList = new ArrayList<Character>();
    public ArrayList<Character> enemyList = new ArrayList<Character>();
    public ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    public ArrayList<Orb> orbList;
    public ArrayList<Map> mapList = new ArrayList<Map>();

    Character currentChar;
    Character currentEnemy;
    Map currentMap;

    String userName;
    String enemyName;

    boolean playerLoss = false;

    public GamePlay(int x, int y, int a){
        xBorder = x;
        yBorder = y;
        scale = a;

    }

    //Checks for win conditions
    public void updateGame(){
        try {
            if(!currentChar.isAlive) {

                if(LaunchGame.gamePlay.currentChar.deathAnimationFinish) {
                    characterList.remove(0);
                }

                if (characterList.size() < 1) {
                    playerLoss = true;
                    LaunchGame.State = LaunchGame.STATE.WIN;
                }
            }
            if(!currentEnemy.isAlive){
                if(LaunchGame.gamePlay.currentEnemy.deathAnimationFinish) {
                    enemyList.remove(0);
                }

                if (characterList.size() <= 1) {
                }
            }
        }catch (Exception e){
            //Do nothing
        }
    }

    public void draw(Graphics g){
        updateGame();

        currentChar = characterList.get(0);
        try {
            currentEnemy = enemyList.get(0);
        }catch (Exception e){
            System.out.println("Enemy not found");
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

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, (xBorder * 2 + 1280)*scale, (yBorder)*scale);
        g.fillRect(0, 0, (xBorder)*scale, (yBorder * 2 + 720)*scale);
        g.fillRect((xBorder + 1280)*scale, 0, (xBorder)*scale, (yBorder * 2 + 720)*scale);
        g.fillRect(0, (yBorder + 720)*scale, (xBorder * 2 + 1280)*scale, (yBorder)*scale);
    }

    public void animate(){

        currentChar = characterList.get(0);
        try {
            currentEnemy = enemyList.get(0);
        }catch (Exception e){
            System.out.println("Enemy not found");
        }

        currentMap.animate();
        currentChar.animate();

        try {
            currentEnemy.animate();
        }catch(Exception e){
            //Do nothing
        }

        for(int i = 0; i <= orbList.size() - 1; i++){
            orbList.get(i).animate();
        }

        for(int i = 0; i <= bulletList.size() - 1; i++){
            if(bulletList.get(i).hitCounter >= bulletHitLimit){
                bulletList.remove(i);
            }
            else {
                bulletList.get(i).animate();
            }
        }
    }

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
            }

            System.out.println("enemy added");
        }

        for (Character anEnemyList : enemyList) {
            anEnemyList.isAnEnemy = true;
        }
    }
}