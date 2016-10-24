import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Rambo extends Character{

    int specialSkillFrequency;

    public Rambo(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Rambo(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Rambo.png";
        this.specialLocationLeft = "resources/Special Skills/RamboMachineGun(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/RamboMachineGun.png";
        this.iconLocation = "resources/Icons/Character10.png";

        this.id = "Rambo";
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            canUseSpecial = false;

            xPos -= 7;
            width += 7;
            height += 2;

            tempImmunityLimit = 350;
            tempImmunity = true;
            specialUsed = true;
            specialActive = true;

            specialSoundClip();
        }
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            if(specialSkillFrequency > 7) {
                if (facingRight) {
                    LaunchGame.gamePlay.bulletList.add(new machineGunBullet(xBorder, yBorder, scale, xPos, yPos + 20*scale, xTarget, yTarget, "right"));
                } else if (facingLeft) {
                    LaunchGame.gamePlay.bulletList.add(new machineGunBullet(xBorder, yBorder, scale, xPos, yPos + 20*scale, xTarget, yTarget, "left"));
                }
                specialSkillFrequency = 0;
            }
            skillTimeLimit++;
            specialSkillFrequency++;
            if(skillTimeLimit >= 280){
                specialActive = false;
                tempImmunity = false;
                skillTimeLimit = 0;

                xPos += 7;
                width -= 7;
                height -= 2;
            }
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/MachineGun.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Sound Error");
        }
    }
}

class machineGunBullet extends Bullet{

    public machineGunBullet(int xB, int yB, int sc, int xP, int yP, double xT, double yT, String direction) {
        super(xB, yB, sc, xP, yP, xT, yT);

        bulletHitLimit = 1;
        width = 20;
        height = 15;

        try {
            bulletImg = ImageIO.read(new File("resources/Special Skills/RamboBullet.png"));
            bulletRing1Img = ImageIO.read(new File("resources/Special Skills/RamboBullet.png"));
            bulletRing2Img = ImageIO.read(new File("resources/Special Skills/RamboBullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(direction.equalsIgnoreCase("right")) {
            xVelocity = xSpeed;
        }else {
            xVelocity = xSpeed * -1;
        }
        yVelocity = 0;
    }

    @Override
    public void wallHitSound(){
        //Do nothing, no sounds
    }
}
