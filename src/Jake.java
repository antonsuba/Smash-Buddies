/**
 * Created by Anton on 3/18/2015.
 *
 */
public class Jake extends Character{
    public Jake(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Jake(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Jake.png";
        this.specialLocationLeft = "resources/Sprites/Jake(FaceLeft).png";
        this.specialLocationRight = "resources/Sprites/Jake.png";

        this.id = "Jake";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}
