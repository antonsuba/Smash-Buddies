import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/5/2015.
 *
 */
public class SuperMan extends Character {

    int skillTimeLimit;

    public SuperMan(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);
        this.charLocationLeft = "resources/Sprites/Superman(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Superman.png";
        this.specialLocationLeft = "resources/Special Skills/SupermanLaser(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/Superman.png";

        this.id = "SuperMan";
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            skillTimeLimit++;
            if(skillTimeLimit >= 180){
                specialActive = false;
                skillTimeLimit = 0;
                xPos += 7;
                width -= 7;
                height -= 2;
            }
        }
    }

    public void specialSkill(int x, int y){
        if(canUseSpecial){
            specialActive = true;
            canUseSpecial = false;
            xPos -= 7;
            width += 7;
            height += 2;

            specialUsed = true;

            LaunchGame.gamePlay.bulletList.add(new Laser(xBorder,yBorder,scale,xPos,yPos,xTarget,yTarget));
        }
    }

    @Override
    public void specialSoundClip() {

    }
}

class Laser extends Bullet{

    BufferedImage laserImg;

    public Laser(int xB, int yB, int sc, int xP, int yP, double xT, double yT) {
        super(xB, yB, sc, xP, yP, xT, yT);

        try {
            laserImg = ImageIO.read(new File("resources/Special Skills/Laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(laserImg,xPos,yPos,width,height,null);
    }

    @Override
    public void animate(){

        Collision();
    }
}
