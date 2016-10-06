
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GrayRoom extends Map{

    BufferedImage block;
    BufferedImage background;

    int w = 10;
    int h = 10;

    int xBorder, yBorder, scale;

    String mapID = "GrayRoom";

    public GrayRoom(int xB, int yB, int sc){

        xBorder = xB;
        yBorder = yB;
        scale = sc;

        xBorder = xB;
        yBorder = yB;
        scale = sc;

        //Spawn Points
        serverSpawnX = (xBorder + 50)*scale;
        serverSpawnY = (yBorder + 50)*scale;
        clientSpawnX = (xBorder + 1230)*scale;
        clientSpawnY = (yBorder + 50)*scale;
        //Spawn Points

        //side walls
        Rectangle block1 = new Rectangle((xBorder)*scale, (yBorder)*scale, 20*scale, 320*scale);
        Rectangle block2 = new Rectangle((xBorder)*scale, (yBorder + 450)*scale, 20*scale, 250*scale);
        Rectangle block3 = new Rectangle((xBorder + 1260)*scale, (yBorder)*scale, 20*scale, 320*scale);
        Rectangle block4 = new Rectangle((xBorder + 1260)*scale, (yBorder + 450)*scale, 20*scale, 250*scale);

        //ceiling and floor
        Rectangle block5 = new Rectangle((xBorder)*scale, (yBorder)*scale, 540*scale, 20*scale);
        Rectangle block6 = new Rectangle((xBorder + 730)*scale, (yBorder)*scale, 550*scale, 20*scale);
        Rectangle block7 = new Rectangle((xBorder)*scale, (yBorder + 690)*scale, 540*scale, 20*scale);
        Rectangle block8 = new Rectangle((xBorder + 730)*scale, (yBorder + 690)*scale, 550*scale, 20*scale);

        //side platforms
        Rectangle block9 = new Rectangle((xBorder)*scale, (yBorder + 250)*scale, 150*scale, 20*scale);
        Rectangle block10 = new Rectangle((xBorder)*scale, (yBorder + 450)*scale, 150*scale, 20*scale);
        Rectangle block11 = new Rectangle((xBorder + 1130)*scale, (yBorder + 250)*scale, 150*scale, 20*scale);
        Rectangle block12 = new Rectangle((xBorder + 1130)*scale, (yBorder + 450)*scale, 150*scale, 20*scale);

        //middle structure:

        //walls
        Rectangle block13 = new Rectangle((xBorder + (1280/4 + 80))*scale, (yBorder + 700/2 - 220)*scale, 20*scale, ((700/2 - 100) - (700/2 - 220) + 10)*scale);
        Rectangle block14 = new Rectangle((xBorder + (1280/4 * 3 - 100))*scale, (yBorder + (700/2 - 220))*scale, 20*scale, ((700/2 - 100) - (700/2 - 220) + 10)*scale);
        Rectangle block15 = new Rectangle((xBorder + (1280/4 + 80))*scale, (yBorder + (700/2 + 50))*scale, 20*scale, ((700/2 + 160) - (700/2 + 50) + 10)*scale);
        Rectangle block16 = new Rectangle((xBorder + (1280/4 * 3 - 100))*scale, (yBorder + (700/2 + 50))*scale, 20*scale, ((700/2 + 160) - (700/2 + 50) + 10)*scale);

        //middle platforms
        Rectangle block17 = new Rectangle((xBorder + (1280/4))*scale, (yBorder + (700/2 - 110))*scale, 100*scale, 20*scale);
        Rectangle block18 = new Rectangle((xBorder + (1280/4 * 3 - 100))*scale, (yBorder + (700/2 - 110))*scale, 110*scale, 20*scale);
        Rectangle block19 = new Rectangle((xBorder + (1280/4))*scale, (yBorder + (700/2 + 50))*scale, 100*scale, 20*scale);
        Rectangle block20 = new Rectangle((xBorder + (1280/4 * 3 - 100))*scale, (yBorder + (700/2 + 50))*scale, 110*scale, 20*scale);

        //ceiling and floor
        Rectangle block21 = new Rectangle((xBorder + (1280/4 + 80))*scale, (yBorder + (700/2 - 220))*scale, ((1280/4 * 3 - 90) - (1280/4 + 90))*scale, 20*scale);
        Rectangle block22 = new Rectangle((xBorder + (1280/4 + 80))*scale, (yBorder + (700/2 + 150))*scale, ((1280/4 * 3 - 70) - (1280/4 + 90))*scale, 20*scale);

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
        mapFloors.add(block18);
        mapFloors.add(block19);
        mapFloors.add(block20);
        mapFloors.add(block21);
        mapFloors.add(block22);

        try {
            block = ImageIO.read(new File("resources/Objects/Block1.png"));
            background = ImageIO.read(new File("resources/Maps/GrayRoom background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Orb a = new Orb(block22.x + (block22.width/2) - 50, block22.y - 80);
        Orb b = new Orb(block9.x + (block9.width/2) - 50, block9.y - 80);
        Orb c = new Orb(block10.x + (block10.width/2) - 50, block10.y - 80);
        Orb d = new Orb(block11.x + (block11.width/2) - 50, block11.y - 80);
        Orb e = new Orb(block12.x + (block12.width/2) - 50, block12.y - 80);

        orbSpawn.add(a);
        orbSpawn.add(b);
        orbSpawn.add(c);
        orbSpawn.add(d);
        orbSpawn.add(e);

    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(background, xBorder*scale, yBorder*scale, 1280*scale,720*scale,null);

        for(int i = 0; i <= 1270; i += 10){
            for(int j = 0; j <= 720; j += 10){
                if(i == 0 || i == 10 || i == 1260 || i == 1270){
                    if(j <= 310 || j >= 450){
                        //side walls
                        g.drawImage(block, (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                    }
                } else if(i <= 530 || i >= 730){
                    if(i <= 140 || i >= 1130){
                        if(j == 250 || j == 260 || j == 450 || j == 460
                                || j == 0 || j == 10 || j == 690 || j == 700){
                            //side platforms
                            g.drawImage(block, (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                        }
                    }  else if(j == 0 || j == 10 || j == 690 || j == 700){
                        //top and bottom walls
                        g.drawImage(block, (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                    }
                }
            }
        }

        //middle structure
        for(int i = 0; i <= 1280/4 * 3; i += 10){
            for(int j = 0; j <= 700; j += 10){
                if(i >= 1280/4){
                    if(i <= 1280/4 + 90 || i >= 1280/4 * 3 - 100){
                        if(i == 1280/4 + 90 || i == 1280/4 + 80 ||
                                i == 1280/4 * 3 - 90 || i == 1280/4 * 3 - 100){
                            if(j >= 700/2 - 220 && j <= 700/2 - 100 ||
                                    j >= 700/2 + 50 && j <= 700/2 + 160){
                                //walls
                                g.drawImage(block, (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                            }
                        } else {
                            if(j == 700/2 + 50 || j == 700/2 + 60 ||
                                    j == 700/2 - 100 || j == 700/2 - 110){
                                //middle platforms
                                g.drawImage(block, (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                            }
                        }
                    } else if(i > 1280/4 + 90 && i < 1280/4 * 3 - 100){
                        if(j == 700/2 - 220 || j == 700/2 - 210 ||
                                j == 700/2 + 150 || j == 700/2 + 160){
                            //ceiling and floor
                            g.drawImage(block, (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                        }
                    }
                }
            }
        }
    }

    public void animate(){
        //Do nothing
    }

    @Override
    public String getMapID() {
        return mapID;
    }


}
