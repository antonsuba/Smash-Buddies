import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/5/2015.
 *
 */
public class SuperMan extends Character {

    Laser laser;

    public SuperMan(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);
        this.charLocationLeft = "resources/Sprites/Superman(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Superman.png";
        this.specialLocationLeft = "resources/Special Skills/SupermanLaser(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/SupermanLaser.png";
        this.iconLocation = "resources/Icons/Character8.png";

        this.id = "SuperMan";
    }

    public void specialSkill(int x, int y){
        if(canUseSpecial){
            specialUsed = true;
            xPos -= 7;
            width += 7;
            height += 2;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 360;
            tempImmunity = true;
            if(facingRight){
                laser = new Laser(xBorder, yBorder, scale, xPos + width - 20, yPos - 5, 0, 0, 5, 10, "Right");
            } else{
                laser = new Laser(xBorder, yBorder, scale, xPos, yPos - 5, 0, 0, 5, 10, "Left");
            }
            LaunchGame.gamePlay.bulletList.add(laser);
            specialSoundClip();
        }
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            skillTimeLimit++;
            if(skillTimeLimit >= 350){
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;
                LaunchGame.gamePlay.bulletList.remove(laser);
                xPos += 7;
                width -= 7;
                height -= 2;
            }
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Laser.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Sound Error");
        }
    }
}

class Laser extends Bullet{
    String direction;
    BufferedImage laser;

    public Laser(int xB, int yB, int sc, int xP, int yP, double xT, double yT, int w, int h, String direction) {
        super(xB, yB, sc, xP, yP, xT, yT);

        this.direction = direction;
        width = w;
        height = h;
        xVelocity = 100;

        try{
            laser = ImageIO.read(new File("resources/Special Skills/Laser.png"));
        } catch(IOException e){
            System.out.println("Laser Error");
        }
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(laser, xPos*scale, yPos*scale, width*scale, height*scale, null);
    }

    @Override
    public void animate(){
        if(direction.equals("Right")){
            if((xBorder + (xPos + width))*scale < (xBorder + 1280)*scale){
                width += xVelocity;
            }
        } else if(direction.equals("Left")){
            if((xBorder + xPos)*scale > (xBorder)*scale){
                width += xVelocity - 5;
                xPos -= xVelocity;
            }
        }
    }
}
