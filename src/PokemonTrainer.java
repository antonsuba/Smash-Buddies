/**
 * Created by Anton on 3/18/2015.
 *
 */
public class PokemonTrainer extends Character{
    public PokemonTrainer(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);

        this.charLocationLeft = "resources/Sprites/Pokemon Trainer(FaceLeft).png";
        this.charLocationRight = "resources/Sprites/Pokemon Trainer.png";
        this.specialLocationLeft = "resources/Sprites/Pokemon Trainer(FaceLeft).png";
        this.specialLocationRight = "resources/Sprites/Pokemon Trainer.png";

        this.id = "Pokemon Trainer";
    }

    @Override
    public void specialSkill(int x, int y) {

    }

    @Override
    public void specialSoundClip() {

    }
}
