import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/8/2015.
 *
 */
public class Bullet {

    BufferedImage bulletImg;
    BufferedImage bulletRing1Img;
    BufferedImage bulletRing2Img;

    int xBorder, yBorder, scale;
    int xPos, yPos;
    int width = 80;
    int height = 60;

    double xSpeed = 30.0;
    double xVelocity;
    double yVelocity;
    double angle;

    double xTarget;
    double yTarget;

    double oppositeLength;
    double adjacentLength;

    int hitCounter;
    boolean bulletHit = false;

    public Bullet(int xB, int yB, int sc, int xP, int yP, double xT, double yT){

        xBorder = xB;
        yBorder = yB;
        scale = sc;

        xPos = xP + (20*scale);
        yPos = yP + (20*scale);

        xTarget = xT;
        yTarget = yT;

        try {
            bulletImg = ImageIO.read(new File("resources/Objects/OrbShine2.png"));
            bulletRing1Img = ImageIO.read(new File("resources/Objects/OrbRing.png"));
            bulletRing2Img = ImageIO.read(new File("resources/Objects/OrbRing2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        computeAngle();
    }

    public void draw(Graphics g){
        if(bulletHit){
            g.drawImage(bulletRing2Img,xPos,yPos,width,height,null);
            bulletHit = false;
        }
        else {
            g.drawImage(bulletImg, xPos, yPos, width, height, null);
        }
    }

    public void animate(){
        xPos += xVelocity;
        if(xPos + width <= 0){
            xPos = (xBorder + 1280)*scale;
        }
        if(xPos >= 1280){
            xPos = (xBorder + (0 - width))*scale;
        }

        yPos += yVelocity;
        if(yPos >= 720){
            yPos = (yBorder + (0 - height))*scale;
        }
        if(yPos + height <= 0) {
            yPos = (yBorder + 720)*scale;
        }

        Collision();
    }

    //Bullet Angle Computation
    public void computeAngle(){
        oppositeLength = yTarget - (double) yPos;
        adjacentLength = xTarget - xPos;

        angle = Math.atan2(oppositeLength,adjacentLength);

        xVelocity = Math.cos(angle)*xSpeed;
        yVelocity = Math.sin(angle)*xSpeed;
    }
    //Bullet Angle Computation

    //Rectangle Bounds
    public Rectangle getBounds(){ return new Rectangle(xPos, yPos, width , height);}
    public Rectangle getTopBounds(){ return new Rectangle(xPos, yPos, width , height/4); }
    public Rectangle getBottomBounds(){ return  new Rectangle(xPos, yPos + ((height/4)*3), width, height/4); }
    public Rectangle getLeftBounds(){ return  new Rectangle(xPos, yPos, width/4, height); }
    public Rectangle getRightBounds(){ return  new Rectangle(xPos + ((width/4)*3), yPos, width/4, height); }
    //Rectangle Bounds

    public void Collision(){
        for(int i = 0; i < LaunchGame.gamePlay.currentMap.mapFloors.size(); i++) {

            Rectangle object = LaunchGame.gamePlay.currentMap.mapFloors.get(i);

            //Top Collision
            if (object.intersects(getTopBounds())) {

                if (object.intersects(getBottomBounds()) && object.intersects(getLeftBounds())) {
                    xPos = (int) object.getX() + (int) object.getWidth();
                    xVelocity = xVelocity * -1;
                    hitCounter ++;
                    bulletHit = true;
                    wallHitSound();
                }
                else if(object.intersects(getBottomBounds()) && object.intersects(getRightBounds())){
                    xPos = ((int) object.getX() - width);
                    xVelocity = xVelocity * -1;
                    hitCounter ++;
                    bulletHit = true;
                    wallHitSound();
                }
                else{
                    yPos = (int) object.getY() + (int) object.getHeight();
                    yVelocity = yVelocity * -1;
                    hitCounter ++;
                    bulletHit = true;
                    wallHitSound();
                }
            }

            //Bottom Collision
            else if (object.intersects(getBottomBounds())) {
                yPos = (int) object.getY() - height;
                yVelocity = yVelocity * -1;
                hitCounter ++;
                bulletHit = true;
            }
        }
    }

    public void wallHitSound(){
        try {

            AudioInputStream wallHit = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/WallHit.wav"));
            Clip wallHitClip = AudioSystem.getClip();
            wallHitClip.open(wallHit);
            wallHitClip.start();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
