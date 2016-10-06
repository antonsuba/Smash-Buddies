
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anton on 3/11/2015.
 *
 */
public class Batman extends Character {

    public Batman(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);
        this.charLocationLeft = "resources/Sprites/Batman(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Batman.png";
        this.specialLocationLeft = "resources/Sprites/Batman(FaceLeft).png";
        this.specialLocationRight = "resources/Sprites/Batman.png";

        this.id = "Batman";
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            canUseSpecial = false;
            tempImmunity = true;

            specialUsed = true;

            LaunchGame.gamePlay.bulletList.add(new Batarang (xBorder, yBorder, scale, xPos, yPos, xPos - 100, yPos + (height/2)));
            LaunchGame.gamePlay.bulletList.add(new Batarang (xBorder, yBorder, scale, xPos, yPos, xPos + width + 100, yPos + (height/2)));
            LaunchGame.gamePlay.bulletList.add(new Batarang (xBorder, yBorder, scale, xPos, yPos, xPos - 50, yPos));
            LaunchGame.gamePlay.bulletList.add(new Batarang (xBorder, yBorder, scale, xPos, yPos, xPos + width + 50, yPos));
            LaunchGame.gamePlay.bulletList.add(new Batarang (xBorder, yBorder, scale, xPos, yPos, width/2, yPos -50));
        }
    }

    @Override
    public void specialSoundClip(){
        //Do nothing
    }
}

class Batarang extends Bullet{

    static BufferedImage batarang1;
    static BufferedImage batarang2;
    static BufferedImage batarang3;
    static BufferedImage batarang4;

    ArrayList<BufferedImage> batarangList;

    int batarangAnimationCounter;
    int batarangFrame;

    static{
        try {
            batarang1 = ImageIO.read(new File("resources/Special Skills/Batarang.png"));
            batarang2 = ImageIO.read(new File("resources/Special Skills/Batarang2.png"));
            batarang3 = ImageIO.read(new File("resources/Special Skills/Batarang3.png"));
            batarang4 = ImageIO.read(new File("resources/Special Skills/Batarang4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Batarang(int xB, int yB, int sc, int xP, int yP, double xT, double yT) {
        super(xB, yB, sc, xP, yP, xT, yT);

        width = 40;
        height = 30;

        batarangList = new ArrayList<BufferedImage>();

        batarangList.add(batarang1);
        batarangList.add(batarang2);
        batarangList.add(batarang3);
        batarangList.add(batarang4);
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(batarangList.get(batarangFrame),xPos,yPos,width,height,null);
    }

    @Override
    public void animate(){
        super.animate();

        batarangAnimationCounter++;

        if(batarangAnimationCounter == 6){
            if(batarangFrame == batarangList.size() - 1){
                batarangFrame = 0;
            } else{
                batarangFrame++;
            }
            batarangAnimationCounter = 0;
        }
    }

    @Override
    public void Collision() {
        for (int i = 0; i < LaunchGame.gamePlay.currentMap.mapFloors.size(); i++) {

            Rectangle object = LaunchGame.gamePlay.currentMap.mapFloors.get(i);

            //Top Collision
            if (object.intersects(getTopBounds())) {

                if (object.intersects(getBottomBounds()) && object.intersects(getLeftBounds())) {
                    xPos = (int) object.getX() + (int) object.getWidth();
                    xVelocity = xVelocity * -1;
                    hitCounter++;
                    bulletHit = true;
                    specialSoundClip();
                } else if (object.intersects(getBottomBounds()) && object.intersects(getRightBounds())) {
                    xPos = ((int) object.getX() - width);
                    xVelocity = xVelocity * -1;
                    hitCounter++;
                    bulletHit = true;
                    specialSoundClip();
                } else {
                    yPos = (int) object.getY() + (int) object.getHeight();
                    yVelocity = yVelocity * -1;
                    hitCounter++;
                    bulletHit = true;
                    specialSoundClip();
                }
            }

            //Bottom Collision
            else if (object.intersects(getBottomBounds())) {
                yPos = (int) object.getY() - height;
                yVelocity = yVelocity * -1;
                hitCounter++;
                bulletHit = true;
                specialSoundClip();
            }
        }
    }

    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/MetalHit.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Sound Error");
        }
    }
}
