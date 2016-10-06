import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Anton on 3/14/2015.
 *
 */
public class Orb {

    static BufferedImage orb1;
    static BufferedImage orb2;
    static BufferedImage orb3;

    static {
        try {
            orb1 = ImageIO.read(new File("resources/Objects/OrbShine.png"));
        } catch (IOException ex) {
            System.out.println("Yes");
        }

        try {
            orb2 = ImageIO.read(new File("resources/Objects/OrbShine2.png"));
        } catch (IOException ex) {
            System.out.println("Yes");
        }

        try {
            orb3 = ImageIO.read(new File("resources/Objects/OrbShine3.png"));
        } catch (IOException ex) {
            System.out.println("Yes");
        }
    }

    int animationCounter = 0;
    int orbFrame = 0;
    ArrayList<BufferedImage> orbs = new ArrayList<BufferedImage>();

    int xOrig;
    int yOrig;
    int x;
    int y;
    int w;
    int h;

    int frames = 0;
    boolean pickedUp = false;

    public Orb(int x, int y){
        this.x = x;
        this.y = y;
        xOrig = x;
        yOrig = y;
        w = 80;
        h = 60;

        orbs.add(orb1);
        orbs.add(orb2);
        orbs.add(orb3);

        Collections.shuffle(orbs);

    }

    public void draw(Graphics g){
        g.drawImage(orbs.get(orbFrame), x, y, w, h, null);
    }


    public void animate(){
        try {
            if (pickedUp(LaunchGame.gamePlay.currentEnemy, this)) {
                if (!LaunchGame.gamePlay.currentEnemy.canAttack) {
                    absorbed();
                    frames = 0;
                    LaunchGame.gamePlay.currentEnemy.canAttack = true;
                }
            }
        }catch (Exception e){
            //Do nothing
        }

        if(pickedUp(LaunchGame.gamePlay.currentChar, this)) {
            if (!LaunchGame.gamePlay.currentChar.canAttack) {
                absorbed();
                frames = 0;
                LaunchGame.gamePlay.currentChar.canAttack = true;
            }
        }

        frames ++;

        if(pickedUp){
            orbRespawnTimer();
        }

        if(frames == 300){
            frames = 0;
        }

        animationCounter++;

        if(animationCounter == 5){
            if(orbFrame == orbs.size() - 1){
                orbFrame = 0;
            } else{
                orbFrame++;
            }
            animationCounter = 0;
        }
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x - 10, y, w + 10, h);
    }

    public void orbRespawnTimer(){
        if(frames == 300){
            x = xOrig;
            y = yOrig;

            pickedUp = false;
        }
    }

    public void absorbed(){
        if(!pickedUp){
            x = -100;
            y = -100;

            pickedUp = true;
        }
    }

    public boolean pickedUp(Character a, Orb o){
        return a.getBounds().intersects(o.getBounds());
    }
}