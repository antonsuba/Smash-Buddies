/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Groot extends Character{
    public Groot(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);
        this.charLocationLeft = "resources/Sprites/Groot(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Groot.png";
        this.specialLocationLeft = "resources/Special Skills/GrootRegenerate(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/GrootRegenerate.png";

        this.id = "Groot";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}
