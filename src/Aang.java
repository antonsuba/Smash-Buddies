/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Aang extends Character{
    public Aang(int x, int y, int sc, int xP, int yP, int w, int h){
        super(x, y, sc, xP, yP, w, h);
        this.charLocationRight = "resources/Sprites/Aang.png";
        this.charLocationLeft = "resources/Sprites/Aang(FaceLeft).png";
        this.specialLocationLeft = "resources/Special Skills/Avatar State(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/Avatar State.png";
        this.iconLocation = "resources/Icons/Character1.png";

        this.id = "Aang";
    }

    @Override
    public void specialSkill(int x, int y) {
        if(canUseSpecial){
            specialUsed = true;
            specialActive = true;
            canUseSpecial = false;
            tempImmunityLimit = 620;
            tempImmunity = true;
        }
    }

    @Override
    public void animate(){
        super.animate();

        if(specialActive){
            skillTimeLimit++;
            if(skillTimeLimit >= 600){
                specialActive = false;
                skillTimeLimit = 0;
                tempImmunityLimit = 20;

            }
        }
    }

    public void fireBullet(int x, int y){
        if(canAttack) {
            Bullet bullet = new Bullet(xBorder, yBorder, scale, xPos, yPos, x, y);
            LaunchGame.gamePlay.bulletList.add(bullet);
            canAttack = false;
            tempImmunity = true;
            soundClip("Shoot");
        }
    }

    @Override
    public void specialSoundClip() {

    }
}