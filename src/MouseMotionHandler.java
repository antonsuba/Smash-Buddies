import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Anton on 3/3/2015.
 *
 */
public class MouseMotionHandler implements MouseMotionListener {

    static int MouseX;
    static int MouseY;

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseX = e.getX();
        MouseY = e.getY();
    }

    //Unneeded Methods
    @Override
    public void mouseDragged(MouseEvent e) {}
    //Unneeded Methods
}
