/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Rambo extends Character{
    public Rambo(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Rambo(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Rambo.png";
        this.specialLocationLeft = "resources/Special Skills/RamboMachineGun(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/RamboMachineGun.png";

        this.id = "Rambo";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}
