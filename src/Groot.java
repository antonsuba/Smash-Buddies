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
public class Groot extends Character{
    BufferedImage regenerate;
    BufferedImage regenerate2;
    BufferedImage regenerate3;

    BufferedImage regenerateLeft;
    BufferedImage regenerate2Left;
    BufferedImage regenerate3Left;

    ArrayList<BufferedImage> regenerating = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> regeneratingLeft = new ArrayList<BufferedImage>();

    int regenerateFrame = 0;
    int frames = 0;

    boolean finished = false;

    public Groot(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Groot.png";
        this.charLocationLeft = "resources/Sprites/Groot(FaceLeft).png";
        this.specialLocationLeft = "resources/Special Skills/GrootRegenerate(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/GrootRegenerate.png";
        this.iconLocation = "resources/Icons/Character5.png";

        this.id = "Groot";

        try{
            regenerate = ImageIO.read(new File("resources/Special Skills/GrootRegenerate.png"));
            regenerate2 = ImageIO.read(new File("resources/Special Skills/GrootRegenerate2.png"));
            regenerate3 = ImageIO.read(new File("resources/Special Skills/GrootRegenerate3.png"));

            regenerateLeft = ImageIO.read(new File("resources/Special Skills/GrootRegenerate(FaceLeft).png"));
            regenerate2Left = ImageIO.read(new File("resources/Special Skills/GrootRegenerate2(FaceLeft).png"));
            regenerate3Left = ImageIO.read(new File("resources/Special Skills/GrootRegenerate3(FaceLeft).png"));
        } catch(IOException e){
            System.out.println("Regenerate error");
        }

        regenerating.add(regenerate);
        regenerating.add(regenerate2);
        regenerating.add(regenerate3);

        regeneratingLeft.add(regenerateLeft);
        regeneratingLeft.add(regenerate2Left);
        regeneratingLeft.add(regenerate3Left);
    }

    @Override
    public void draw(Graphics g){
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
                g.drawImage(regeneratingLeft.get(regenerateFrame), xPos - (width + 100)/3 * scale, yPos - 25, (width + 100)*scale, (height + 50)*scale, null);
            }
            else if(facingRight){
                g.drawImage(regenerating.get(regenerateFrame), xPos - (width + 100)/3 * scale, yPos - 25, (width + 100)*scale, (height + 50)*scale, null);
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
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 100;
            tempImmunity = true;
            System.out.println(LaunchGame.gamePlay.currentChar.livesLeft);
            LaunchGame.gamePlay.currentChar.livesLeft++;
            System.out.println(LaunchGame.gamePlay.currentChar.livesLeft);
            canMove = false;
            specialSoundClip();
        }
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            frames++;
            skillTimeLimit++;

            if(frames == 10){
                regenerateFrame++;

                if(facingRight){
                    if(regenerateFrame >= regenerating.size()){
                        regenerateFrame = 0;
                        finished = true;
                    }
                } else if(facingLeft){
                    if(regenerateFrame >= regeneratingLeft.size()){
                        regenerateFrame = 0;
                        finished = true;
                    }
                }

                frames = 0;
            }

            if(finished){
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;
                canMove = true;

            }
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/forceabsorb02.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Regenerate sound error");
        }
    }
}
