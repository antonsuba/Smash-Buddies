import javax.swing.*;
import java.awt.*;

/**
 * Created by Anton on 2/24/2015.
 */
public class FullScreen {

    GraphicsDevice gd;

    public FullScreen(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode dm, JFrame window){
        window.setUndecorated(true);
        window.setResizable(false);

        gd.setFullScreenWindow(window);

        if(dm!=null && gd.isDisplayChangeSupported()){
            try{
                gd.setDisplayMode(dm);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
    }

    public void closeScreen(){
        Window w = gd.getFullScreenWindow();
        if(w!=null){
            w.dispose();
        }
        gd.setFullScreenWindow(null);
    }
}
