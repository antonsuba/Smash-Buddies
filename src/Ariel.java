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
 * Created by Anton on 3/18/2015.
 *
 */
public class Ariel extends Character{
    BufferedImage Sing;
    BufferedImage Sing2;
    BufferedImage Sing3;

    BufferedImage SingLeft;
    BufferedImage Sing2Left;
    BufferedImage Sing3Left;

    ArrayList<BufferedImage> singing = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> singingLeft = new ArrayList<BufferedImage>();

    int singFrame = 0;
    int frames = 0;

    int enemyParalyzeCounter = 0;

    public Ariel(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Ariel.png";
        this.charLocationLeft = "resources/Sprites/Ariel(FaceLeft).png";
        this.iconLocation = "resources/Icons/Character2.png";

        this.id = "Ariel";

        try{
            Sing = ImageIO.read(new File("resources/Special Skills/ArielSing.png"));
            Sing2 = ImageIO.read(new File("resources/Special Skills/ArielSing2.png"));
            Sing3 = ImageIO.read(new File("resources/Special Skills/ArielSing3.png"));

            SingLeft = ImageIO.read(new File("resources/Special Skills/ArielSing(FaceLeft).png"));
            Sing2Left = ImageIO.read(new File("resources/Special Skills/ArielSing2(FaceLeft).png"));
            Sing3Left = ImageIO.read(new File("resources/Special Skills/ArielSing3(FaceLeft).png"));
        } catch(IOException e){
            System.out.println("Singing image error");
        }

        singing.add(Sing);
        singing.add(Sing2);
        singing.add(Sing3);

        singingLeft.add(SingLeft);
        singingLeft.add(Sing2Left);
        singingLeft.add(Sing3Left);
    }

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
        else if(specialActive && !emptyCharChecker){
            if(facingLeft){
                g.drawImage(singingLeft.get(singFrame), xPos, yPos - 5, (width + 17)*scale, (height + 5)*scale, null);
            }
            else if(facingRight){
                g.drawImage(singing.get(singFrame), xPos, yPos - 5, (width + 17)*scale, (height + 5)*scale, null);
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
            frames++;
            skillTimeLimit++;

            if(frames == 10){
                singFrame++;

                if(facingRight){
                    if(singFrame >= singing.size()){
                        singFrame = 0;
                    }
                } else if(facingLeft){
                    if(singFrame >= singingLeft.size()){
                        singFrame = 0;
                    }
                }

                frames = 0;
            }

            if(skillTimeLimit >= 150){
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;
                canMove = true;

            }
        }

        if(enemyParalyze){
            enemyParalyzeCounter++;

            if(enemyParalyzeCounter >= 350){
                enemyParalyze = false;
                enemyParalyzeCounter = 0;
            }
        }
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 170;
            tempImmunity = true;
            canMove = false;
            enemyParalyze = true;
            specialSoundClip();
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Singing1.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch (Exception e){
            System.out.println("Sound Error");
        }
    }
}
