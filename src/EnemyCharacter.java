/**
 * Created by Anton on 3/11/2015.
 *
 */
abstract class EnemyCharacter extends Character {

    public EnemyCharacter(int x, int y, int sc, int xP, int yP, int w, int h) {
        super(x, y, sc, xP, yP, w, h);
    }

    public void update(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
