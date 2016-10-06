import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 2/26/2015.
 *
 */

public class StartMenu{

    int xBorder;
    int yBorder;
    int scale;

    BufferedImage batmanImg;
    BufferedImage grootImg;

    MenuButton createGame;
    MenuButton joinGame;
    MenuButton Credits;
    MenuButton Exit;

    static int createX, joinX, creditsX, exitX;
    static int createY, joinY, creditsY, exitY;
    static int createWidth, joinWidth, creditsWidth, exitWidth, buttonHeight;

    MenuSprite Aang;
    MenuSprite Goku;
    MenuSprite Batman;
    MenuSprite Groot;
    MenuSprite Rambo;
    MenuSprite Ariel;
    MenuSprite BruceLee;
    MenuSprite Superman;
    MenuSprite MaceWindu;

    Background menu;

    public StartMenu(int x, int y, int a){
        xBorder = x;
        yBorder = y;
        scale = a;

        String batmanLocation = "resources/Sprites/Batman.png";
        String grootLocation = "resources/Sprites/Superman(FaceLeft).png";
        try {
            batmanImg = ImageIO.read(new File(batmanLocation));
            grootImg = ImageIO.read(new File(grootLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createX = xBorder+((1280/2 - 115)*scale);
        joinX = xBorder+((1280/2 - 95)*scale);
        creditsX = xBorder+((1280/2 - 75)*scale);
        exitX = xBorder+((1280/2 - 50)*scale);

        createY = yBorder+((720/2 + 60)*scale);
        joinY = yBorder+((720/2 + 35 + 60)*scale);
        creditsY = yBorder+((720/2 + 35*2 + 60)*scale);
        exitY = yBorder+((720/2 + 35*3 + 60)*scale);

        createWidth = 230*scale;
        joinWidth = 190*scale;
        creditsWidth = 150*scale;
        exitWidth = 100*scale;
        buttonHeight = 25*scale;

        createGame = new MenuButton("Create", createX, createY, createWidth, buttonHeight);
        joinGame = new MenuButton("Join", joinX, joinY, joinWidth, buttonHeight);
        Credits = new MenuButton("Credits", creditsX, creditsY, creditsWidth, buttonHeight);
        Exit = new MenuButton("Exit", exitX, exitY, exitWidth, buttonHeight);

        Aang = new MenuSprite(100, "Aang", xBorder, yBorder, scale);
        Goku = new MenuSprite(190, "Goku", xBorder, yBorder, scale);
        Batman = new MenuSprite(270, "Batman", xBorder, yBorder, scale);
        Groot = new MenuSprite(330, "Groot", xBorder, yBorder, scale);
        Rambo = new MenuSprite(740, "Rambo", xBorder, yBorder, scale);
        Ariel = new MenuSprite(820, "Ariel", xBorder, yBorder, scale);
        BruceLee = new MenuSprite(890, "Bruce Lee", xBorder, yBorder, scale);
        Superman = new MenuSprite(970, "Superman", xBorder, yBorder, scale);
        MaceWindu = new MenuSprite(410, "Mace Windu", xBorder, yBorder, scale);

        menu = new Background(xBorder, yBorder, scale);
    }

    public void draw(Graphics g){
        menu.draw(g);
        g.drawImage(batmanImg, xBorder+(100*scale), yBorder+((720/2 + 40)*scale), 140*scale, 200*scale, null);
        g.drawImage(grootImg, xBorder+(1070*scale), yBorder+((720/2 + 40)*scale), 110*scale, 200*scale, null);

        createGame.draw(g);
        joinGame.draw(g);
        Credits.draw(g);
        Exit.draw(g);

        Aang.draw(g);
        Goku.draw(g);
        Batman.draw(g);
        Groot.draw(g);
        Rambo.draw(g);
        Ariel.draw(g);
        BruceLee.draw(g);
        Superman.draw(g);
        MaceWindu.draw(g);
    }


    public void animate() {
        int x = MouseMotionHandler.MouseX;
        int y = MouseMotionHandler.MouseY;

        if(x >= createX && x <= createX + createWidth && y >= createY && y <= createY + buttonHeight){
            createGame.start();
        } else if(x >= joinX && x <= joinX + joinWidth && y >= joinY && y <= joinY + buttonHeight){
            joinGame.start();
        } else if(x >= creditsX && x <= creditsX + creditsWidth && y >= creditsY && y <= creditsY + buttonHeight){
            Credits.start();
        } else if(x >= exitX && x <= exitX + exitWidth && y >= exitY && y <= exitY + buttonHeight){
            Exit.start();
        } else {
            createGame.stop();
            joinGame.stop();
            Credits.stop();
            Exit.stop();
        }

        Aang.animate();
        Goku.animate();
        Batman.animate();
        Groot.animate();
        Rambo.animate();
        Ariel.animate();
        BruceLee.animate();
        Superman.animate();
        MaceWindu.animate();

        menu.animate();
    }
}