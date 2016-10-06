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
public class BruceLee extends Character {
    FlamingKick flamingKick = new FlamingKick(xBorder, yBorder, scale, xPos, yPos,
            0, 0, width, height, "Right");

    public BruceLee(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Bruce Lee.png";
        this.charLocationLeft = "resources/Sprites/Bruce Lee(FaceLeft).png";
        this.iconLocation = "resources/Icons/Character4.png";

        this.id = "Bruce Lee";
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 360;
            tempImmunity = true;
            canMove = false;
            if(facingRight){
                flamingKick = new FlamingKick(xBorder, yBorder, scale, xPos, yPos,
                        0, 0, width, height, "Right");
            } else{
                flamingKick = new FlamingKick(xBorder, yBorder, scale, xPos, yPos,
                        0, 0, width, height, "Left");
            }
            LaunchGame.gamePlay.bulletList.add(flamingKick);
            specialSoundClip();
        }
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        try {
            charImgLeft = ImageIO.read(new File(charLocationLeft));
            charImgRight = ImageIO.read(new File(charLocationRight));
            icon = ImageIO.read(new File(iconLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!isAlive){
            deathAnimationFrame++;
            if (deathAnimationFrame <= 3) {
                g.drawImage(blood1,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else if (deathAnimationFrame <= 6) {
                g.drawImage(blood2,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else if (deathAnimationFrame <= 9) {
                g.drawImage(blood3,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else if (deathAnimationFrame <= 20) {
                g.drawImage(blood4,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else{
                deathAnimationFinish = true;
            }
        }
        else if(!specialActive && !emptyCharChecker){
            if (facingLeft) {
                g.drawImage(charImgLeft, xPos, yPos, (width)*scale, (height)*scale, null);
            }
            else if (facingRight) {
                g.drawImage(charImgRight, xPos, yPos, width*scale, height*scale, null);
            }
        }

        //Player Name
        g.setFont(new Font("Seravek",Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        if(isAnEnemy){
            int stringLen = (int) g2.getFontMetrics().getStringBounds(enemyName, g2).getWidth();
            int start = width / 2 - stringLen / 2;
            g.drawString(enemyName, start + xPos, yPos - 10);
        }
        else {
            int stringLen = (int) g2.getFontMetrics().getStringBounds(playerName, g2).getWidth();
            int start = width / 2 - stringLen / 2;
            g.drawString(playerName, start + xPos, yPos - 10);
        }
        //Player Name
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            skillTimeLimit++;
            if(flamingKick.kickCounter == 3){
                specialActive = false;
                skillTimeLimit = 0;
                LaunchGame.gamePlay.bulletList.remove(flamingKick);
                tempImmunityLimit = 20;
                canMove = true;

            }
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            //Hiya
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Hiya.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Sound Error");
        }
    }
}

class FlamingKick extends Bullet{
    BufferedImage flamingKick;
    BufferedImage flamingKickLeft;

    String direction;
    int kickCounter = 0;

    boolean faceRight;
    boolean faceLeft;

    public FlamingKick(int xB, int yB, int sc, int xP, int yP, double xT, double yT, int w, int h, String direction) {
        super(xB, yB, sc, xP, yP, xT, yT);

        xVelocity = 30;

        this.direction = direction;
        height = h;
        width = w;

        if (direction.equals("Right")) {
            faceRight = true;
            faceLeft = false;

        } else if (direction.equals("Left")) {
            faceRight = false;
            faceLeft = true;

        }

        try{
            flamingKick = ImageIO.read(new File("resources/Special Skills/FlamingKick.png"));
            flamingKickLeft = ImageIO.read(new File("resources/Special Skills/FlamingKick(FaceLeft).png"));
        } catch(IOException e){
            System.out.println("Flaming Kick Error");
        }
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if(faceRight){
            g2.drawImage(flamingKick, xPos - 30, yPos - 30, (width + 60)*scale, (height + 20)*scale, null);
        } else if(faceLeft){
            g2.drawImage(flamingKickLeft, xPos - 30, yPos - 30, (width + 60)*scale, (height + 20)*scale, null);
        }
    }

    @Override
    public void animate(){
        if(faceRight){

            if(xPos + width*scale < (xBorder + 1280)*scale){
                xPos += xVelocity;
            } else{
                kickCounter++;

                faceRight = false;
                faceLeft = true;
            }
        } else if(faceLeft){
            if(xPos > (xBorder)*scale){
                xPos -= xVelocity;
            } else{
                kickCounter++;

                faceLeft = false;
                faceRight = true;
            }
        }

    }

    @Override
    public void Collision(){
        //no collision
    }
}
