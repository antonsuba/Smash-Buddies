/**
 * Created by Anton on 3/18/2015.
 *
 */
public class BruceLee extends Character {
    public BruceLee(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Bruce Lee(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Bruce Lee.png";
        this.specialLocationLeft = "resources/Special Skills/FlamingKick(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/FlamingKick.png";

        this.id = "Bruce Lee";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}
