/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Ariel extends Character{
    public Ariel(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Ariel(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Ariel.png";
        this.specialLocationLeft = "resources/Special Skills/ArielSing(FaceLeft).png";
        this.specialLocationRight = "resources/Special Skills/ArielSing.png";

        this.id = "Ariel";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}
