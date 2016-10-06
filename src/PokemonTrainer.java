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
public class PokemonTrainer extends Character{
    BufferedImage charizard1;
    BufferedImage charizard2;
    BufferedImage charizard1Left;
    BufferedImage charizard2Left;

    ArrayList<BufferedImage> charizard = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> charizardLeft = new ArrayList<BufferedImage>();
    int charizardFrame = 0;

    int fireFrame;

    public PokemonTrainer(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Pokemon Trainer.png";
        this.charLocationLeft = "resources/Sprites/Pokemon Trainer(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/CharizardRide.png";
        this.specialLocationLeft = "resources/Special Skills/CharizardRide2.png";
        this.iconLocation = "resources/Icons/Character7.png";

        this.id = "Pokemon Trainer";

        try{
            charizard1 = ImageIO.read(new File("resources/Special Skills/CharizardRide.png"));
            charizard2 = ImageIO.read(new File("resources/Special Skills/CharizardRide2.png"));
            charizard1Left = ImageIO.read(new File("resources/Special Skills/CharizardRide(FaceLeft).png"));
            charizard2Left = ImageIO.read(new File("resources/Special Skills/CharizardRide2(FaceLeft).png"));
        } catch(Exception e){
            System.out.println("Charizard Error");
        }

        charizard.add(charizard1);
        charizard.add(charizard2);
        charizardLeft.add(charizard1Left);
        charizardLeft.add(charizard2Left);
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
        } else if(!specialActive && !emptyCharChecker){
            if (facingLeft) {
                g.drawImage(charImgLeft, xPos, yPos, (width)*scale, (height)*scale, null);
            }
            else if (facingRight) {
                g.drawImage(charImgRight, xPos, yPos, width*scale, height*scale, null);
            }
        } else if(specialActive && !emptyCharChecker){
            if(facingLeft){
                g.drawImage(charizardLeft.get(charizardFrame), xPos, yPos - 20, (width + 100)*scale, (height + 20)*scale, null);
            }
            else if(facingRight){
                g.drawImage(charizard.get(charizardFrame), xPos - 50, yPos - 20, (width + 100)*scale, (height + 20)*scale, null);
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
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 420;
            tempImmunity = true;
        }
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            fireFrame++;
            skillTimeLimit++;

            if(fireFrame == 10){
                if(facingRight){
                    LaunchGame.gamePlay.bulletList.add(new Fireball(xBorder, yBorder, scale, xPos + width + 35, yPos,
                            0, 0, 30, 15, "Right"));
                } else if(facingLeft){
                    LaunchGame.gamePlay.bulletList.add(new Fireball(xBorder, yBorder, scale, xPos - 35, yPos,
                            0, 0, 30, 15, "Left"));
                }

                fireFrame = 0;
            }

            if(skillTimeLimit == 400){
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;
            }
        }
    }

    @Override
    public void specialSoundClip() {

    }
}

class Fireball extends Bullet{
    BufferedImage fireball;
    BufferedImage fireballLeft;

    String direction;

    boolean faceLeft;
    boolean faceRight;

    int fireFrame;

    public Fireball(int xB, int yB, int sc, int xP, int yP, double xT, double yT, int w, int h, String direction) {
        super(xB, yB, sc, xP, yP, xT, yT);

        this.direction = direction;
        width = w;
        height = h;

        if (direction.equals("Right")) {
            faceLeft = false;
            faceRight = true;

        } else if (direction.equals("Left")) {
            faceLeft = true;
            faceRight = false;

        }

        try{
            fireball = ImageIO.read(new File("resources/Special Skills/Fireball.png"));
            fireballLeft = ImageIO.read(new File("resources/Special Skills/Fireball(FaceLeft).png"));
        } catch(IOException e){
            System.out.println("Fireball error");
        }
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if(faceRight){
            g2.drawImage(fireball, xPos + width*scale, yPos, (width)*scale, (height)*scale, null);
        } else if(faceLeft){
            g2.drawImage(fireballLeft, xPos - width*scale, yPos, (width)*scale, (height)*scale, null);
        }
    }

    @Override
    public void animate(){
        fireFrame++;
        if(fireFrame < 2){
            fireSound();
        }
        if(faceRight){
            if(xPos + width*scale < (xBorder + 1280)*scale){
                xPos -= xVelocity;
            } else{
                LaunchGame.gamePlay.bulletList.remove(this);
            }
        } else if(faceLeft){
            if(xPos > xBorder*scale){
                xPos += xVelocity;
            } else{
                LaunchGame.gamePlay.bulletList.remove(this);
            }
        }
    }

    public void fireSound(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new File("resources/Sound Clips/Flamethrower.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Flamethrower error");
        }
    }

    public void Collision(){
        //no collision
    }
}
