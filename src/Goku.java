import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Goku extends Character{
    Kamehameha kamehameha;

    public Goku(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Goku.png";
        this.charLocationLeft = "resources/Sprites/Goku(FaceLeft).png";
        this.specialLocationLeft = "resources/Special Skills/SuperSaiyan(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/SuperSaiyan.png";
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 360;
            tempImmunity = true;
            canMove = false;
            if(facingRight){
                kamehameha = new Kamehameha(xBorder, yBorder, scale, xPos + width, yPos - 50,
                        0, 0, 5, height + 100, "Right");
            } else{
                kamehameha = new Kamehameha(xBorder, yBorder, scale, xPos + 5, yPos - 50,
                        0, 0, 5, height + 100, "Left");
            }
            LaunchGame.gamePlay.bulletList.add(kamehameha);
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
                LaunchGame.gamePlay.bulletList.remove(kamehameha);
                tempImmunityLimit = 20;
                canMove = true;

            }
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream =
                    AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Kamehameha.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();

        } catch (Exception e){
            System.out.println("Sound Error");
        }
    }
}

class Kamehameha extends Bullet{
    BufferedImage kamehameha;
    String direction;

    public Kamehameha(int xB, int yB, int sc, int xP, int yP, double xT, double yT, int w, int h,String direction) {
        super(xB, yB, sc, xP, yP, xT, yT);

        width = w;
        height = h;
        this.direction = direction;
        xVelocity = 40;

        try{
            kamehameha = ImageIO.read(new File("resources/Special Skills/Kamehameha.png"));
        } catch(IOException e){

        }
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(kamehameha, xPos*scale, (yPos - 20)*scale, width*scale, height*scale, null);
    }

    @Override
    public void animate(){
        if(direction.equals("Right")){
            if((xBorder + (xPos + width))*scale < (xBorder + 1280)*scale){
                xPos -= 2;
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
