
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Background {
    BufferedImage image;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;
    BufferedImage image5;

    ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

    int frames = 0;
    int imageFrame = 0;

    int xBorder, yBorder, scale;

    public Background(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        try {
            image = ImageIO.read(new File("resources/Background/Menu.png"));
            image2 = ImageIO.read(new File("resources/Background/Menu2.png"));
            image3 = ImageIO.read(new File("resources/Background/Menu3.png"));
            image4 = ImageIO.read(new File("resources/Background/Menu4.png"));
            image5 = ImageIO.read(new File("resources/Background/Menu5.png"));
        } catch (IOException ex) {
            System.out.println("No");
        }

        images.add(image);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(images.get(imageFrame), 0, 0, ((xBorder*2) + 1280)*scale, ((yBorder*2) +720)*scale, null);
    }

    public void animate(){
        frames++;

        if(frames == 10){
            imageFrame++;

            if(imageFrame == images.size()){
                imageFrame = 0;
            }

            frames = 0;
        }

    }
}
