import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Anton on 3/5/2015.
 *
 */
abstract class Map {
    ArrayList<Rectangle> mapFloors = new ArrayList<Rectangle>();
    ArrayList<Orb> orbSpawn = new ArrayList<Orb>();

    int serverSpawnX;
    int serverSpawnY;
    int clientSpawnX;
    int clientSpawnY;

    abstract public void draw(Graphics g);
    abstract public void animate();

    abstract public String getMapID();
}
