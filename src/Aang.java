/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Aang extends Character{
    public Aang(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Aang(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Aang.png";
        this.specialLocationLeft = "resources/Special Skills/Avatar State(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/Avatar State.png";

        this.id = "Aang";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}