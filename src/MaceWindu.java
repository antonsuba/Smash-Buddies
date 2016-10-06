import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/25/2015.
 *
 */
public class MaceWindu extends Character{
    ForcePush forcePush = new ForcePush(xBorder, yBorder, scale, xPos, yPos, 0, 0, 50, 50);

    public MaceWindu(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);

        this.charLocationRight = "resources/Sprites/Mace Windu.png";
        this.charLocationLeft = "resources/Sprites/Mace Windu(FaceLeft).png";
        this.specialLocationLeft = "resources/Special Skills/TheForce(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/TheForce.png";
        this.iconLocation = "resources/Icons/Character11.png";

        this.id = "Mace Windu";
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        try {
            charImgLeft = ImageIO.read(new File(charLocationLeft));
            charImgRight = ImageIO.read(new File(charLocationRight));
            specialSkillLeft = ImageIO.read(new File(specialLocationLeft));
            specialSkillRight = ImageIO.read(new File(specialLocationRight));

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
        else if(specialActive && !emptyCharChecker){
            if(facingLeft){
                g.drawImage(specialSkillLeft, xPos, yPos, (width + 20)*scale, height*scale, null);
            }
            else if(facingRight){
                g.drawImage(specialSkillRight, xPos, yPos, (width + 20)*scale, height*scale, null);
            }
        }
        else if(!specialActive && !emptyCharChecker){
            if (facingLeft) {
                g.drawImage(charImgLeft, xPos, yPos, width*scale, height*scale, null);
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
            if(forcePush.forceFrame >= 30){
                LaunchGame.gamePlay.bulletList.remove(forcePush);
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;
                canMove = true;

            }
        }
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 50;
            tempImmunity = true;
            canMove = false;
            forcePush = new ForcePush(xBorder, yBorder, scale, xPos, yPos, 0, 0, 50, 50);
            LaunchGame.gamePlay.bulletList.add(forcePush);
            specialSoundClip();
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/forcedestruct01.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Force error");
        }
    }
}

class ForcePush extends Bullet{
    BufferedImage force;

    int forceFrame = 0;

    public ForcePush(int xB, int yB, int sc, int xP, int yP, double xT, double yT, int w, int h) {
        super(xB, yB, sc, xP, yP, xT, yT);

        width = w;
        height = h;

        try{
            force = ImageIO.read(new File("resources/Special Skills/ForcePush.png"));
        } catch(IOException e){
            System.out.println("Force Push error");
        }
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(force, xPos, yPos, width * scale, height * scale, null);
    }

    @Override
    public void animate(){
        forceFrame++;
        xPos += xVelocity;
        yPos += xVelocity;
        width -= xVelocity - 30;
        height -= xVelocity - 30;
    }

    @Override
    public void Collision(){
        //no collision
    }
}
