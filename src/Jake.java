import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Jake extends Character{
    public Jake(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Jake.png";
        this.charLocationLeft = "resources/Sprites/Jake(FaceLeft).png";
        this.specialLocationRight = "resources/Sprites/Jake.png";
        this.specialLocationLeft = "resources/Sprites/Jake(FaceLeft).png";
        this.iconLocation = "resources/Icons/Character6.png";

        this.id = "Jake";
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            width -= 25;
            height -= 30;
            maxXVelocity = 25;
            maxYVelocity = 32;
            tempImmunityLimit = 660;
            tempImmunity = true;
            specialSoundClip();
        }
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            skillTimeLimit++;
            if(skillTimeLimit >= 650){
                width += 25;
                height += 30;
                maxXVelocity = 15;
                maxYVelocity = 22;
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;

            }
        }
    }

    @Override
    public void fireBullet(int x, int y){
        if(canMove && canAttack) {
            xTarget = x;
            yTarget = y;
            bulletFired = true;

            Bullet bullet = new Bullet(xBorder, yBorder, scale, xPos, yPos, xTarget, yTarget);
            LaunchGame.gamePlay.bulletList.add(bullet);
            canAttack = false;
            tempImmunity = true;
        }
    }

    @Override
    public void specialSoundClip() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream
                    (new File("resources/Sound Clips/BaconPancakes.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } catch(Exception e){
            System.out.println("Bacon Pancakes Error");
        }
    }
}
