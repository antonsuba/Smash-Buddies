import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/15/2015.
 *
 */

public class MapSelect {
    BufferedImage backgroundPrev;

    int w;
    int h;

    int xBorder, yBorder, scale;

    BufferedImage dojoPrev;
    BufferedImage grayRoomPrev;
    BufferedImage blockRoomPrev;
    BufferedImage neonPrev;

    Rectangle border = new Rectangle(-10, -10, 0, 0);

    public boolean mapSelected = false;

    int backX;
    int backY;
    int startY;
    int startX;
    int startWidth, backWidth, buttonHeight;
    MenuButton backButton;
    MenuButton startButton;

    int dojoX;
    int grayRoomX;
    int blockRoomX;
    int neonX;
    int thumbY;

    public MapSelect(int xB, int yB, int sc){

        xBorder = xB;
        yBorder = yB;
        scale = sc;

        //Initialize Values
        w = 250*scale;
        h = 150*scale;

        border = new Rectangle(-10, -10, 0, 0);

        backX = (xBorder + (1280/4 - 50)) * scale;
        backY = (yBorder + 670) * scale;
        startY = backY;
        startX = (xBorder + (1250/4 * 3 - 60))*scale;
        startWidth = 125*scale;
        backWidth = 100*scale;
        buttonHeight = 30*scale;

        dojoX = (xBorder + 100)*scale;
        grayRoomX = (xBorder + 370)*scale;
        blockRoomX = (xBorder + 640)*scale;
        neonX = (xBorder + 910)*scale;
        thumbY = (yBorder + 360)*scale;
        //Initialize Values

        backButton = new MenuButton("Back", backX, backY, backWidth, buttonHeight);
        startButton = new MenuButton("Start", startX, startY, startWidth, buttonHeight);

        try {
            backgroundPrev = ImageIO.read(new File(""));

        } catch (IOException ex) {
            System.out.println("No");
        }

        try{
            dojoPrev = ImageIO.read(new File("resources/Maps/Dojo Preview.png"));
            grayRoomPrev = ImageIO.read(new File("resources/Maps/GrayRoom Preview.png"));
            blockRoomPrev = ImageIO.read(new File("resources/Maps/BlockRoom Preview.png"));
            neonPrev = ImageIO.read(new File("resources/Maps/Neon Preview.png"));
        } catch (IOException ex) {
            System.out.println("No");
        }

        startButton.width = 0;
        startButton.height = 0;
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(backgroundPrev, 0, 0, (1280 + (xBorder*2))*scale, (720 + (yBorder*2))*scale, null);

        g.drawImage(dojoPrev, dojoX, thumbY, w, h, null );
        g.drawRect(dojoX, thumbY, w, h);
        g.drawImage(grayRoomPrev, grayRoomX, thumbY, w, h, null);
        g.drawRect(grayRoomX, thumbY, w, h);
        g.drawImage(blockRoomPrev, blockRoomX, thumbY, w, h, null);
        g.drawRect(blockRoomX, thumbY, w, h);
        g.drawImage(neonPrev, neonX, thumbY, w, h, null);
        g.drawRect(neonX, thumbY, w, h);

        g2.setStroke(new BasicStroke(5));
        g.setColor(Color.red);
        g.drawRect(border.x, border.y, border.width, border.height);

        backButton.draw(g);
        startButton.draw(g);
    }

    public void drawBorder(int x, int y){
        if(border.x == x && border.y == y){
            removeBorder();
        }
        else if(mapSelected){
            System.out.println("Map already selected");
        }
        else{
            if(x == dojoX && y == thumbY){
                LaunchGame.gamePlay.mapList.add(new Dojo(xBorder,yBorder,scale));
            } else if(x == grayRoomX && y == thumbY){
                LaunchGame.gamePlay.mapList.add(new GrayRoom(xBorder,yBorder,scale));
            } else if(x == blockRoomX && y == thumbY){
                LaunchGame.gamePlay.mapList.add(new BlockRoom(xBorder,yBorder,scale));
            } else if(x == neonX && y == thumbY){
                LaunchGame.gamePlay.mapList.add(new Neon(xBorder,yBorder,scale));
            }
            mapSelected = true;
            border.setLocation(x, y);
            border.setSize(w, h);
            startVisibility("visible");
        }
    }

    public void removeBorder(){
        border.setLocation(0, 0);
        border.setSize(0, 0);

        if(mapSelected){
            LaunchGame.gamePlay.mapList.remove(0);
        }
        mapSelected = false;
        startVisibility("invisible");
    }

    public void startVisibility(String condition){
        if(condition.equals("visible")){
            startButton.width = 120*scale;
            startButton.height = 25*scale;
        } else{
            startButton.width = 0;
            startButton.height = 0;
        }
    }

    public void animate() {
        int x = MouseMotionHandler.MouseX;
        int y = MouseMotionHandler.MouseY;

        if(x >= dojoX && x <= dojoX + w && y >= thumbY && y <= thumbY + h){
            backgroundPrev = dojoPrev;
        } else if(x >= grayRoomX && x <= grayRoomX + w && y >= thumbY && y <= thumbY + h){
            backgroundPrev = grayRoomPrev;
        } else if(x >= blockRoomX && x <= blockRoomX + w && y >= thumbY && y <= thumbY + h){
            backgroundPrev = blockRoomPrev;
        } else if(x >= neonX && x <= neonX + w && y >= thumbY && y <= thumbY + h){
            backgroundPrev = neonPrev;
        } else if(x >= backX && x <= backX + backWidth && y >= backY && y <= backY + buttonHeight){
            backButton.start();
        } else if(x >= startX && x <= startX + startWidth && y >= startY && y <= startY + buttonHeight){
            startButton.start();
        } else{
            backButton.stop();
            startButton.stop();
        }

    }
}
