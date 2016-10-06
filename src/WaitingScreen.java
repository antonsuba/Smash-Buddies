import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/21/2015.
 *
 */
public class WaitingScreen {
    BufferedImage background;
    BufferedImage message;
    BufferedImage aang;
    BufferedImage groot;

    int xBorder, yBorder, scale;

    int aangX, grootX, messageX;

    int xVelocity = 0;
    final int xSpeed = 1;
    final int maxXVelocity = 5;

    int frames = 0;
    boolean moving = false;
    boolean start = true;
    boolean soundStart = true;

    public WaitingScreen(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        aangX = 10;
        messageX = 35;
        grootX = 1198;

        try{
            background = ImageIO.read(new File("resources/Background/WaitScreen.png"));
        } catch(IOException ex){
            System.out.println("Waiting Screen Error");
        }

        try{
            message = ImageIO.read(new File("resources/Objects/PlayerWait.png"));
        } catch(IOException ex){
            System.out.println("Waiting Screen Error");
        }
        try{
            aang = ImageIO.read(new File("resources/Objects/AangPush.png"));
        } catch(IOException ex){
            System.out.println("Waiting Screen Error");
        }
        try{
            groot = ImageIO.read(new File("resources/Objects/GrootPull.png"));
        } catch(IOException ex){
            System.out.println("Waiting Screen Error");
        }
    }

    public void draw(Graphics g){
        //Graphics2D g2 = (Graphics2D) g;

        g.drawImage(background, 0, 0, (xBorder*2) + 1280*scale, (yBorder*2) + 720*scale, null);

        g.drawImage(aang, aangX, (yBorder + (720/2 + 38))*scale, 45*scale, 60*scale, null);
        g.drawImage(groot, grootX, (yBorder + (720/2 + 38))*scale, 45*scale, 60*scale, null);

        g.drawImage(message, messageX, (yBorder + (720/2 - 102))*scale, 1180*scale, 200*scale, null);

    }

    public void animate(){

        if(LaunchGame.clientRunning){
            if(LaunchGame.gamePlay.mapLoaded){
                LaunchGame.State = LaunchGame.STATE.GAME;
            }
        }
        else {
            if (start) {
                frames++;
            }

            //if (soundStart) {
                //soundClip();
            //}

            if (frames == 5) {
                soundStart = false;
            }

            if (frames == 100) {
                moving = true;
                start = false;
            }

            if (moving) {
                aangX += xVelocity;
                grootX += xVelocity;
                messageX += xVelocity;

                xVelocity += xSpeed;
                if (xVelocity >= maxXVelocity) {
                    xVelocity = maxXVelocity;
                }
            }

            if (aangX > xBorder * 2 + 1280) {
                aangX = 0 - 1180 * scale;
            }

            if (grootX > xBorder * 2 + 1280) {
                grootX = 0 - 1180 * scale;
            }

            if (messageX > xBorder * 2 + 1280) {
                messageX = 0 - 1180 * scale;
            }
        }
    }

    public void soundClip(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/Grunting.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e){
            System.out.println("Sound Error");
        }
    }
}
