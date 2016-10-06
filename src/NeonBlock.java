import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anton on 3/17/2015.
 *
 */
public class NeonBlock {
    int x;
    int y;
    int w;
    int h;
    String Color;
    String blockType;

    BufferedImage image;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;
    BufferedImage image5;

    ArrayList<BufferedImage> colors = new ArrayList<BufferedImage>();
    int colorFrame = 0;
    boolean hit = false;
    int frames = 0;

    public NeonBlock(int x, int y, int w, int h, String Color, String blockType){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.Color = Color;
        this.blockType = blockType;

        try {
            image = ImageIO.read(new File("resources/Objects/Neon/" + Color + blockType + ".png"));
            if (Color.equals("Green")) {
                image2 = ImageIO.read(new File("resources/Objects/Neon/Magenta" + blockType + ".png"));
                image3 = ImageIO.read(new File("resources/Objects/Neon/Cyan" + blockType + ".png"));
                image4 = ImageIO.read(new File("resources/Objects/Neon/Yellow" + blockType + ".png"));
                image5 = ImageIO.read(new File("resources/Objects/Neon/Orange" + blockType + ".png"));

            } else if (Color.equals("Orange")) {
                image2 = ImageIO.read(new File("resources/Objects/Neon/Green" + blockType + ".png"));
                image3 = ImageIO.read(new File("resources/Objects/Neon/Yellow" + blockType + ".png"));
                image4 = ImageIO.read(new File("resources/Objects/Neon/Cyan" + blockType + ".png"));
                image5 = ImageIO.read(new File("resources/Objects/Neon/Magenta" + blockType + ".png"));

            } else if (Color.equals("Yellow")) {
                image2 = ImageIO.read(new File("resources/Objects/Neon/Cyan" + blockType + ".png"));
                image3 = ImageIO.read(new File("resources/Objects/Neon/Magenta" + blockType + ".png"));
                image4 = ImageIO.read(new File("resources/Objects/Neon/Orange" + blockType + ".png"));
                image5 = ImageIO.read(new File("resources/Objects/Neon/Green" + blockType + ".png"));

            } else if (Color.equals("Cyan")) {
                image2 = ImageIO.read(new File("resources/Objects/Neon/Orange" + blockType + ".png"));
                image3 = ImageIO.read(new File("resources/Objects/Neon/Green" + blockType + ".png"));
                image4 = ImageIO.read(new File("resources/Objects/Neon/Magenta" + blockType + ".png"));
                image5 = ImageIO.read(new File("resources/Objects/Neon/Yellow" + blockType + ".png"));

            } else if (Color.equals("Magenta")) {
                image2 = ImageIO.read(new File("resources/Objects/Neon/Yellow" + blockType + ".png"));
                image3 = ImageIO.read(new File("resources/Objects/Neon/Orange" + blockType + ".png"));
                image4 = ImageIO.read(new File("resources/Objects/Neon/Green" + blockType + ".png"));
                image5 = ImageIO.read(new File("resources/Objects/Neon/Cyan" + blockType + ".png"));

            }
        } catch (IOException ex) {
            System.out.println("Yes");
        }

        colors.add(image);
        colors.add(image2);
        colors.add(image3);
        colors.add(image4);
        colors.add(image5);
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(colors.get(colorFrame), x, y, w, h, null);
    }

    public Rectangle getBounds(){
        if(x == 0){
            if(y == 0){
                return new Rectangle(x - 10, y - 200, w, h + 190);
            } else{
                return new Rectangle(x - 10, y + 10, w, h - 20);
            }
        } else if(y == 0){
            if(blockType.equals("Block3")){
                return new Rectangle(x + 10, y - 200, w, h + 190);
            } else{
                return new Rectangle(x + 10, y - 10, w - 20, h);
            }
        } else{
            if(blockType.equals("Block4") ||
                    blockType.equals("Side2")){
                return new Rectangle(x + 10, y + 10, w, h - 20);
            } else if(blockType.equals("Square")){
                return new Rectangle(x + 15, y + 15, w - 30, h - 30);
            } else if(blockType.equals("Floor")){
                return new Rectangle(x + 10, y + 10, w - 20, h + 200);
            } else if(w == 250){
                return new Rectangle(x + 20, y + 10, w - 40, h - 20);
            }  else{
                return new Rectangle(x + 10, y + 10, w - 20, h - 20);
            }
        }
    }

    public Rectangle getColorBounds(){
        if(x == 0){
            if(y == 0){
                return new Rectangle(x - 10, y - 200, w + 1, h + 190 + 1);
            } else{
                return new Rectangle(x - 10, y + 10 - 1, w + 1, h - 20 + 2);
            }
        } else if(y == 0){
            if(blockType.equals("Block3")){
                return new Rectangle(x + 10 - 1, y - 200, w, h + 190 + 1);
            } else{
                return new Rectangle(x + 10 - 1, y - 10, w - 20 + 2, h + 1);
            }
        } else{
            if(blockType.equals("Block4") ||
                    blockType.equals("Side2")){
                return new Rectangle(x + 10 - 1, y + 10 - 1, w, h - 20 + 2);
            } else if(blockType.equals("Square")){
                return new Rectangle(x + 15 - 1, y + 15 - 1, w - 30 + 2, h - 30 + 2);
            } else if(blockType.equals("Floor")){
                return new Rectangle(x + 10 - 1, y + 10 - 1, w - 20 + 2, h + 200);
            } else if(w == 250){
                return new Rectangle(x + 20 - 1, y + 10 - 1, w - 40 + 2, h - 20 + 2);
            }  else{
                return new Rectangle(x + 10 - 1, y + 10 - 1, w - 20 + 2, h - 20 + 2);
            }
        }
    }

    public boolean Collision(Character a, NeonBlock n){
        if (blockType.equals("Block")) {
            Rectangle b = new Rectangle(x, y + 15 - 1, w - 55 + 1, h + 40);
            Rectangle c = new Rectangle(x, y + 50 - 1, w - 10 + 1, h);
            return b.intersects(a.getBounds()) || c.intersects(a.getBounds());
        } else if (blockType.equals("Block2")) {
            Rectangle b = new Rectangle(x, y - 100, w - 55 + 1, h + 90 + 1);
            Rectangle c = new Rectangle(x, y - 50, w - 10 + 1, h - 5 + 1);
            return b.intersects(a.getBounds()) || c.intersects(a.getBounds());
        } else if (blockType.equals("Block3")) {
            Rectangle b = new Rectangle(x + 55 - 1, y - 100, w, h + 90 + 1);
            Rectangle c = new Rectangle(x + 10 - 1, y - 50, w, h - 5 + 1);
            return b.intersects(a.getBounds()) || c.intersects(a.getBounds());
        } else if (blockType.equals("Block4")) {
            Rectangle b = new Rectangle(x + 55 - 1, y + 15 - 1, w, h + 40);
            Rectangle c = new Rectangle(x + 10 - 1, y + 50 - 1, w, h);
            return b.intersects(a.getBounds()) || c.intersects(a.getBounds());
        } else {
            return a.getBounds().intersects(n.getColorBounds());
        }
    }

    public void animate(){
        if(Collision(LaunchGame.gamePlay.currentChar, this)){
            hit = true;
        } else{
            hit = false;
            frames = 0;
        }
        if(hit){
            frames++;
            if(frames < 2){
                hitSound();
                colorFrame++;

                if(colorFrame >= colors.size()){
                    colorFrame = 0;
                }
            }
        }

    }

    public void hitSound(){
        AudioInputStream greenStream;
        Clip green = null;
        AudioInputStream magentaStream;
        Clip magenta = null;
        AudioInputStream orangeStream;
        Clip orange = null;
        AudioInputStream cyanStream;
        Clip cyan = null;
        AudioInputStream yellowStream;
        Clip yellow = null;

        try{
            greenStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Dubstep1.wav"));
            green = AudioSystem.getClip();
            green.open(greenStream);

            magentaStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Dubstep2.wav"));
            magenta = AudioSystem.getClip();
            magenta.open(magentaStream);

            orangeStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Dubstep6.wav"));
            orange = AudioSystem.getClip();
            orange.open(orangeStream);

            cyanStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Dubstep4.wav"));
            cyan = AudioSystem.getClip();
            cyan.open(cyanStream);

            yellowStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Dubstep5.wav"));
            yellow = AudioSystem.getClip();
            yellow.open(yellowStream);

        } catch(Exception e){
            System.out.println("dubstep sound error");
        }
        if (Color.equals("Green")) {
            if (colorFrame == 0) {
                assert green != null;
                green.start();
            } else if (colorFrame == 1) {
                assert magenta != null;
                magenta.start();
            } else if (colorFrame == 2) {
                assert cyan != null;
                cyan.start();
            } else if (colorFrame == 3) {
                assert yellow != null;
                yellow.start();
            } else if (colorFrame == 4) {
                assert orange != null;
                orange.start();
            }
        } else if (Color.equals("Orange")) {
            if (colorFrame == 0) {
                assert orange != null;
                orange.start();
            } else if (colorFrame == 1) {
                assert green != null;
                green.start();
            } else if (colorFrame == 2) {
                assert yellow != null;
                yellow.start();
            } else if (colorFrame == 3) {
                assert cyan != null;
                cyan.start();
            } else if (colorFrame == 4) {
                assert magenta != null;
                magenta.start();
            }
        } else if (Color.equals("Yellow")) {
            if (colorFrame == 0) {
                assert yellow != null;
                yellow.start();
            } else if (colorFrame == 1) {
                assert cyan != null;
                cyan.start();
            } else if (colorFrame == 2) {
                assert magenta != null;
                magenta.start();
            } else if (colorFrame == 3) {
                assert orange != null;
                orange.start();
            } else if (colorFrame == 4) {
                assert green != null;
                green.start();
            }
        } else if (Color.equals("Cyan")) {
            if (colorFrame == 0) {
                assert cyan != null;
                cyan.start();
            } else if (colorFrame == 1) {
                assert orange != null;
                orange.start();
            } else if (colorFrame == 2) {
                assert green != null;
                green.start();
            } else if (colorFrame == 3) {
                assert magenta != null;
                magenta.start();
            } else if (colorFrame == 4) {
                assert yellow != null;
                yellow.start();
            }
        } else if (Color.equals("Magenta")) {
            if (colorFrame == 0) {
                assert magenta != null;
                magenta.start();
            } else if (colorFrame == 1) {
                assert yellow != null;
                yellow.start();
            } else if (colorFrame == 2) {
                assert orange != null;
                orange.start();
            } else if (colorFrame == 3) {
                assert green != null;
                green.start();
            } else if (colorFrame == 4) {
                assert cyan != null;
                cyan.start();
            }
        }
    }
}
