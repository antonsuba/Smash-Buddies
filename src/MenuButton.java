
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class MenuButton {
    BufferedImage img;

    int x;
    int yOrig;
    int y = yOrig;

    int width;
    int height;

    Timer timer = new Timer(150, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            animate();
        }
    });

    public MenuButton(String buttonName, int x, int y, int width, int height){
        this.x = x;
        yOrig = y;
        this.y = y;
        this.width = width;
        this.height = height;

        String location = "resources/MenuItems/" + buttonName + ".png";

        try {
            img = ImageIO.read(new File(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        g.drawImage(img, x, y, width, height, null);
    }

    public void animate(){
        if(y == yOrig){
            y += 5;
        } else{
            y = yOrig;
        }
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
        y = yOrig;
    }
}
