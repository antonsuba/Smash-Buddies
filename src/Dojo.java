
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dojo extends Map{

    BufferedImage background;
    BufferedImage wood;
    BufferedImage wood2;
    BufferedImage wood5;
    BufferedImage wood6;
    BufferedImage wood7;
    BufferedImage wood8;
    BufferedImage wood9;

    int xBorder, yBorder, scale;

    Rectangle block1;
    Rectangle block2;
    Rectangle block3;
    Rectangle block4;
    Rectangle block5;
    Rectangle block6;
    Rectangle block7;
    Rectangle block8;
    Rectangle block9;
    Rectangle block10;
    Rectangle block11;
    Rectangle block12;
    Rectangle block13;
    Rectangle block14;
    Rectangle block15;
    Rectangle block16;
    Rectangle block17;

    public Dojo(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        //left walls
        block1 = new Rectangle((xBorder)*scale, (yBorder)*scale, 60*scale, (720/5 - 40)*scale);
        block2 = new Rectangle((xBorder)*scale, (yBorder + (720/5 * 2 - 20))*scale, 60*scale, ((720/5 * 3 + 20) - (720/5 * 2) - 30)*scale);
        block3 = new Rectangle((xBorder)*scale, (yBorder + (720/5 * 4 - 20))*scale, 60*scale, (720 - (720/5 * 4) + 30)*scale);

        //right walls
        block4 = new Rectangle((xBorder + 1220)*scale, (yBorder)*scale, 60*scale, (720/5 - 40)*scale);
        block5 = new Rectangle((xBorder + 1220)*scale, (yBorder + (720/5 * 2 - 20))*scale, 60*scale, ((720/5 * 3 + 20) - (720/5 * 2) - 30)*scale);
        block6 = new Rectangle((xBorder + 1220)*scale, (yBorder + (720/5 * 4 - 20))*scale, 60*scale, (720 - (720/5 * 4) + 30)*scale);

        //ceiling
        block7 = new Rectangle((xBorder)*scale, (yBorder + -100)*scale, (1280/5 + 50 - 6)*scale, (40 + 100)*scale);
        block8 = new Rectangle((xBorder + (1280/5 * 2 - 40 + 8))*scale, (yBorder + -100)*scale, ((1280/5 * 3 + 40) - (1280/5 * 2 - 40) - 8)*scale, (40 + 100)*scale);
        block9 = new Rectangle((xBorder + (1280/5 * 4 - 40 + 16))*scale, (yBorder + -100)*scale, (1280 - (1280/5 * 4 - 40))*scale, (40 + 100)*scale);

        //floor
        block10 = new Rectangle((xBorder)*scale, (yBorder + 680)*scale, (1280/5 + 50 - 6)*scale, (40 + 100)*scale);
        block11 = new Rectangle((xBorder + (1280/5 * 2 - 40 + 8))*scale, (yBorder + 680)*scale, ((1280/5 * 3 + 40) - (1280/5 * 2 - 40))*scale, (40 + 100)*scale);
        block12 = new Rectangle((xBorder + (1280/5 * 4 - 40 + 16))*scale, (yBorder + 680)*scale, (1280 - (1280/5 * 4 - 40))*scale, (40 + 100)*scale);

        //platforms:

        //middle
        block13 = new Rectangle((xBorder + (1280/5 * 2))*scale, (yBorder + (720/5 * 3 - ((720/5 * 3) - (720/5 * 2))/2))*scale,
                ((1280/5 * 3) - (1280/5 * 2))*scale, 20*scale);

        //top-left
        block14 = new Rectangle((xBorder + (1280/5  - ((1280/5 * 2) - (1280/5))/2))*scale, (yBorder + ((720/5 * 2) - ((720/5 * 2) - (720/5))/2))*scale,
                ((1280/5 * 2) - (1280/5))*scale, 20*scale);

        //bottom-left
        block15 = new Rectangle((xBorder + (1280/5  - ((1280/5 * 2) - (1280/5))/2))*scale, (yBorder + ((720/5 * 4) - ((720/5 * 3) - (720/5 * 2))/2))*scale,
                ((1280/5 * 2) - (1280/5))*scale, 20*scale);

        //top-right
        block16 = new Rectangle((xBorder + (1280/5 * 4  - ((1280/5 * 4) - (1280/5 * 3))/2))*scale, (yBorder + ((720/5 * 2) - ((720/5 * 2) - (720/5))/2))*scale,
                ((1280/5 * 2) - (1280/5))*scale, 20*scale);

        //bottom-right
        block17 = new Rectangle((xBorder + (1280/5 * 4  - ((1280/5 * 4) - (1280/5 * 3))/2))*scale, (yBorder + ((720/5 * 4) - ((720/5 * 3) - (720/5 * 2))/2))*scale,
                ((1280/5 * 2) - (1280/5))*scale, 20*scale);

        //Point a;

        mapFloors.add(block1);
        mapFloors.add(block2);
        mapFloors.add(block3);
        mapFloors.add(block4);
        mapFloors.add(block5);
        mapFloors.add(block6);
        mapFloors.add(block7);
        mapFloors.add(block8);
        mapFloors.add(block9);
        mapFloors.add(block10);
        mapFloors.add(block11);
        mapFloors.add(block12);
        mapFloors.add(block13);
        mapFloors.add(block14);
        mapFloors.add(block15);
        mapFloors.add(block16);
        mapFloors.add(block17);

        try {
            background = ImageIO.read(new File("resources/Maps/Dojo background.png"));
            wood = ImageIO.read(new File("resources/Objects/WoodBlock.png"));
            wood2 = ImageIO.read(new File("resources/Objects/WoodBlock2.png"));
            wood5  = ImageIO.read(new File("resources/Objects/WoodBlock5.png"));
            wood6  = ImageIO.read(new File("resources/Objects/WoodBlock6.png"));
            wood7  = ImageIO.read(new File("resources/Objects/WoodBlock7.png"));
            wood8  = ImageIO.read(new File("resources/Objects/WoodBlock8.png"));
            wood9  = ImageIO.read(new File("resources/Objects/WoodBlock9.png"));
        } catch (IOException ex) {
            System.out.println("No");
        }

        Orb a = new Orb(block13.x + (block13.width/2) - 50, block13.y - 80);
        Orb b = new Orb(block14.x + (block14.width/2) - 50, block14.y - 80);
        Orb c = new Orb(block15.x + (block15.width/2) - 50, block15.y - 80);
        Orb d = new Orb(block16.x + (block16.width/2) - 50, block16.y - 80);
        Orb e = new Orb(block17.x + (block17.width/2) - 50, block17.y - 80);

        orbSpawn.add(a);
        orbSpawn.add(b);
        orbSpawn.add(c);
        orbSpawn.add(d);
        orbSpawn.add(e);

    }

    @Override
    public void draw(Graphics g){

        g.drawImage(background,xBorder,yBorder,1280*scale,720*scale,null);

        for(int i = 0; i <= 1280; i += 20){
            for(int j = 0; j <= 720; j += 20){
                if(i >= 20 && i < 1260){
                    if(i  <= 1280/5 + 30 || i >= 1280/5 * 2 - 40 && i <= 1280/5 * 3 + 20 || i >= 1280/5 * 4 - 40){
                        if(j <= 20){
                            //ceiling
                            g.drawImage(wood, (xBorder + i)*scale, (yBorder + j)*scale, 20*scale, 20*scale, null);
                        } else if(j >= 680){
                            //floor
                            g.drawImage(wood2, (xBorder + i)*scale, (yBorder + j)*scale, 20*scale, 20*scale, null);
                        }
                    }
                }
            }
        }

        //left walls
        g.drawImage(wood7, block1.x, block1.y, block1.width, block1.height, null);
        g.drawImage(wood7, block2.x, block2.y, block2.width, block2.height, null);
        g.drawImage(wood7, block3.x, block3.y, block3.width, block3.height, null);

        //right walls
        g.drawImage(wood6, block4.x, block4.y, block4.width, block4.height, null);
        g.drawImage(wood6, block5.x, block5.y, block5.width, block5.height, null);
        g.drawImage(wood6, block6.x, block6.y, block6.width, block6.height, null);

        //middle platform
        g.drawImage(wood5, block13.x, block13.y, block13.width, block13.height, null);

        //top-left platform
        g.drawImage(wood5, block14.x, block14.y, block14.width, block14.height, null);

        //bottom-left platform
        g.drawImage(wood5, block15.x, block15.y, block15.width, block15.height, null);

        //top-right platform
        g.drawImage(wood5, block16.x, block16.y, block16.width, block16.height, null);

        //bottom-left platform
        g.drawImage(wood5, block17.x, block17.y, block17.width, block17.height, null);
    }

    public void animate(){
        //Do nothing
    }

}
