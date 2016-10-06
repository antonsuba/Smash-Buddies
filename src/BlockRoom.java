import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anton on 3/15/2015.
 *
 */
public class BlockRoom extends Map{

    BufferedImage block;
    BufferedImage block2;
    BufferedImage block3;
    ArrayList<BufferedImage> blocksCeiling = new ArrayList<BufferedImage>();

    BufferedImage block4;
    BufferedImage block5;
    BufferedImage block6;
    ArrayList<BufferedImage> blocksLeft = new ArrayList<BufferedImage>();

    BufferedImage block10;
    BufferedImage block11;
    BufferedImage block12;
    ArrayList<BufferedImage> blocksRight = new ArrayList<BufferedImage>();

    BufferedImage block13;
    BufferedImage block14;
    BufferedImage block15;
    ArrayList<BufferedImage> blocks1 = new ArrayList<BufferedImage>();

    BufferedImage block16;
    BufferedImage block17;
    BufferedImage block18;
    ArrayList<BufferedImage> blocks2 = new ArrayList<BufferedImage>();

    BufferedImage block19;
    BufferedImage block20;
    BufferedImage block21;
    ArrayList<BufferedImage> blocks3 = new ArrayList<BufferedImage>();

    BufferedImage block22;
    BufferedImage block23;
    BufferedImage block24;
    ArrayList<BufferedImage> blocks4 = new ArrayList<BufferedImage>();

    BufferedImage block25;

    Rectangle box1;
    Rectangle box2;
    Rectangle box3;
    Rectangle box4;
    Rectangle box5;
    Rectangle box6;
    Rectangle box7;
    Rectangle box8;
    Rectangle box9;
    Rectangle box10;
    Rectangle box11;
    Rectangle box12;
    Rectangle box13;
    Rectangle box14;

    ArrayList<Color> set1 = new ArrayList<Color>();
    ArrayList<Color> set2 = new ArrayList<Color>();
    ArrayList<Color> set3 = new ArrayList<Color>();
    ArrayList<Color> set4 = new ArrayList<Color>();

    Color Red = new Color(255, 0, 0);
    Color Green = new Color(0, 128, 0);
    Color Blue = new Color(0, 0, 255);
    Color Purple = new Color(128, 0, 128);

    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    int i6 = 0;
    int i7 = 0;
    int i8 = 0;

    int animationCounter2 = 0;
    int colorFrame = 0;
    Random rand = new Random();
    int rn;

    int animationCounter = 0;
    int animationFrame = 0;

    int y = 720/2 + 300;
    int width = 160;

    int w = 40;
    int h = 40;
    int w2 = 60;
    int h2 = 60;

    Orb a;
    Orb b;
    Orb e;

    int xBorder, yBorder, scale;

    public BlockRoom(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        //bottom boxes
        box1 = new Rectangle((xBorder)*scale,(yBorder + y)*scale, width*scale, 400*scale);
        box2 = new Rectangle((xBorder + 1280/8)*scale, (yBorder + y)*scale, width*scale, 400*scale);
        box3 = new Rectangle((xBorder + 1280/8 * 2)*scale, (yBorder + y)*scale, width*scale, 400*scale);
        box4 = new Rectangle((xBorder + 1280/8 * 3)*scale, (yBorder + y)*scale, width*scale, 400*scale);
        box5 = new Rectangle((xBorder + 1280/8 * 4)*scale, (yBorder + y)*scale, width*scale, 400*scale);
        box6 = new Rectangle((xBorder + 1280/8 * 5)*scale, (yBorder + y)*scale, width*scale, 400*scale);
        box7 = new Rectangle((xBorder + 1280/8 * 6)*scale, (yBorder + y)*scale, width*scale, 400*scale);
        box8 = new Rectangle((xBorder + 1280/8 * 7)*scale, (yBorder + y)*scale, width*scale, 400*scale);

        //ceiling
        box9 = new Rectangle((xBorder)*scale, (yBorder)*scale, 1280*scale, 40*scale);

        //Left wall
        box10 = new Rectangle((xBorder)*scale, (yBorder)*scale, 40*scale, 720*scale);

        //Right wall
        box11 = new Rectangle((xBorder + 1240)*scale, (yBorder)*scale, 40*scale, 720*scale);

        //platforms
        box12 = new Rectangle((xBorder + (1280/3 - 100))*scale, (yBorder + 720/2)*scale,
                ((1280/3 * 2 + 100) - (1280/3 - 100))*scale, 50*scale);
        box13 = new Rectangle((xBorder + (1280/4 - 220))*scale, (yBorder + 720/4)*scale,
                300*scale, 50*scale);
        box14 = new Rectangle((xBorder + (1280/4 * 3 - 100))*scale, (yBorder + 720/4)*scale,
                300*scale, 50*scale);

        try {
            block = ImageIO.read(new File("resources/Objects/BlockRoom/BlockUp.png"));
            block2 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockUp2.png"));
            block3 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockUp3.png"));
            block4 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockLeft.png"));
            block5 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockLeft2.png"));
            block6 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockLeft3.png"));
            block10 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockRight.png"));
            block11 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockRight2.png"));
            block12 = ImageIO.read(new File("resources/Objects/BlockRoom/BlockRight3.png"));
            block13 = ImageIO.read(new File("resources/Objects/BlockRoom/Block4.png"));
            block14 = ImageIO.read(new File("resources/Objects/BlockRoom/Block42.png"));
            block15 = ImageIO.read(new File("resources/Objects/BlockRoom/Block43.png"));
            block16 = ImageIO.read(new File("resources/Objects/BlockRoom/Block5.png"));
            block17 = ImageIO.read(new File("resources/Objects/BlockRoom/Block52.png"));
            block18 = ImageIO.read(new File("resources/Objects/BlockRoom/Block53.png"));
            block19 = ImageIO.read(new File("resources/Objects/BlockRoom/Block6.png"));
            block20 = ImageIO.read(new File("resources/Objects/BlockRoom/Block62.png"));
            block21 = ImageIO.read(new File("resources/Objects/BlockRoom/Block63.png"));
            block22 = ImageIO.read(new File("resources/Objects/BlockRoom/Block7.png"));
            block23 = ImageIO.read(new File("resources/Objects/BlockRoom/Block72.png"));
            block24 = ImageIO.read(new File("resources/Objects/BlockRoom/Block73.png"));
            block25 = ImageIO.read(new File("resources/Objects/BlockRoom/Block8.png"));
        } catch (IOException ex) {
            System.out.println("No");
        }

        blocksCeiling.add(block);
        blocksCeiling.add(block2);
        blocksCeiling.add(block3);

        blocksLeft.add(block4);
        blocksLeft.add(block5);
        blocksLeft.add(block6);

        blocksRight.add(block10);
        blocksRight.add(block11);
        blocksRight.add(block12);

        blocks1.add(block13);
        blocks1.add(block14);
        blocks1.add(block15);

        blocks2.add(block16);
        blocks2.add(block17);
        blocks2.add(block18);

        blocks3.add(block19);
        blocks3.add(block20);
        blocks3.add(block21);

        blocks4.add(block22);
        blocks4.add(block23);
        blocks4.add(block24);

        set1.add(Green);
        set1.add(Red);
        set1.add(Purple);
        set1.add(Blue);


        set2.add(Blue);
        set2.add(Green);
        set2.add(Red);
        set2.add(Purple);

        set3.add(Purple);
        set3.add(Blue);
        set3.add(Green);
        set3.add(Red);

        set4.add(Red);
        set4.add(Purple);
        set4.add(Blue);
        set4.add(Green);

        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box1.y -= 1;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box2.y = rn;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box3.y = rn;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box4.y = rn;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box5.y = rn;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box6.y = rn;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box7.y = rn;
        rn = rand.nextInt((y) - (y - 100) + 1) + (y - 100);
        box8.y = rn;

        mapFloors.add(box1);
        mapFloors.add(box2);
        mapFloors.add(box3);
        mapFloors.add(box4);
        mapFloors.add(box5);
        mapFloors.add(box6);
        mapFloors.add(box7);
        mapFloors.add(box8);
        mapFloors.add(box9);
        mapFloors.add(box10);
        mapFloors.add(box11);
        mapFloors.add(box12);
        mapFloors.add(box13);
        mapFloors.add(box14);

        a = new Orb(box1.x + 50, box1.y - 80);
        b = new Orb(box8.x + 20, box8.y - 80);
        Orb c = new Orb(box12.x + 10, box12.y - 80);
        Orb d = new Orb(box12.x + box12.width - 80, box12.y - 80);
        e = new Orb(box4.x + (box4.width/2) - 50, box4.y - 80);

        orbSpawn.add(a);
        orbSpawn.add(b);
        orbSpawn.add(c);
        orbSpawn.add(d);
        orbSpawn.add(e);
    }

    @Override
    public void draw(Graphics g) {
        a.y = box1.y - 80;
        b.y = box8.y - 80;
        e.y = box4.y - 80;

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, xBorder*2 + 1280, yBorder*2 + 720);

        animationCounter++;
        animationCounter2++;

        if(i1 == 0){
            box1.y -= 5;

            if(box1.y < y - 150){
                i1 = 1;
            }
        } else{
            box1.y += 5;

            if(box1.y > y){
                i1 = 0;
            }
        }

        if(i2 == 0){
            box2.y -= 3;

            if(box2.y < y - 150){
                i2 = 1;
            }
        } else{
            box2.y += 3;

            if(box2.y > y){
                i2 = 0;
            }
        }

        if(i3 == 0){
            box3.y -= 2;

            if(box3.y < y - 150){
                i3 = 1;
            }
        } else{
            box3.y += 2;

            if(box3.y > y){
                i3 = 0;
            }
        }

        if(i4 == 0){
            box4.y -= 4;

            if(box4.y < y - 150){
                i4 = 1;
            }
        } else{
            box4.y += 4;

            if(box4.y > y){
                i4 = 0;
            }
        }

        if(i5 == 0){
            box5.y -= 6;

            if(box5.y < y - 150){
                i5 = 1;
            }
        } else{
            box5.y += 6;

            if(box5.y > y){
                i5 = 0;
            }
        }

        if(i6 == 0){
            box6.y -= 3;

            if(box6.y < y - 150){
                i6 = 1;
            }
        } else{
            box6.y += 3;

            if(box6.y > y){
                i6 = 0;
            }
        }

        if(i7 == 0){
            box7.y -= 5;

            if(box7.y < y - 150){
                i7 = 1;
            }
        } else{
            box7.y += 5;

            if(box7.y > y){
                i7 = 0;
            }
        }

        if(i8 == 0){
            box8.y -= 4;

            if(box8.y < y - 150){
                i8 = 1;
            }
        } else{
            box8.y += 4;

            if(box8.y > y){
                i8 = 0;

            }
        }

        if(animationCounter == 5){
            animationFrame++;



            if(animationFrame == blocksCeiling.size()){
                animationFrame = 0;
            }

            animationCounter = 0;
        }

        if(animationCounter2 == 20){
            colorFrame++;



            if(colorFrame == set1.size()){
                colorFrame = 0;
            }

            animationCounter2 = 0;
        }

        g.setColor(set1.get(colorFrame));
        g.fillRect(box1.x, box1.y, box1.width, box1.height);
        g.fillRect(box5.x, box5.y, box5.width, box5.height);

        g.setColor(set2.get(colorFrame));
        g.fillRect(box2.x, box2.y, box2.width, box2.height);
        g.fillRect(box6.x, box6.y, box6.width, box6.height);

        g.setColor(set3.get(colorFrame));
        g.fillRect(box3.x, box3.y, box3.width, box7.height);
        g.fillRect(box7.x, box7.y, box7.width, box7.height);

        g.setColor(set4.get(colorFrame));
        g.fillRect(box4.x, box4.y, box4.width, box4.height);
        g.fillRect(box8.x, box8.y, box8.width, box8.height);

        g2.setStroke(new BasicStroke(20));

        g.setColor(Color.BLACK);
        g.drawRect(box1.x, box1.y, box1.width, box1.height);
        g.drawRect(box5.x, box5.y, box5.width, box5.height);

        g.drawRect(box2.x, box2.y, box2.width, box2.height);
        g.drawRect(box6.x, box6.y, box6.width, box6.height);

        g.drawRect(box3.x, box3.y, box3.width, box7.height);
        g.drawRect(box7.x, box7.y, box7.width, box7.height);

        g.drawRect(box4.x, box4.y, box4.width, box4.height);
        g.drawRect(box8.x, box8.y, box8.width, box8.height);


        for(int i = 0; i <= 1280; i += 40){
            for(int j = 0; j <= 720; j += 40){
                if(i == 0){
                    if(j >= 40 && j < 680){
                        g2.drawImage(blocksLeft.get(animationFrame), (xBorder + i - 20)*scale, (yBorder + j)*scale, w2*scale, h*scale, null);
                    } else if(j == 0){
                        g2.drawImage(blocks1.get(animationFrame), (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                    } else{
                        g2.drawImage(blocks2.get(animationFrame), (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                    }
                } else if(i == 1240){
                    if(j >= 40 && j < 680){
                        g2.drawImage(blocksRight.get(animationFrame), (xBorder + i)*scale, (yBorder + j)*scale, w2*scale, h*scale, null);
                    } else if(j == 0){
                        g2.drawImage(blocks4.get(animationFrame), (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                    } else{
                        g2.drawImage(blocks3.get(animationFrame), (xBorder + i)*scale, (yBorder + j)*scale, w*scale, h*scale, null);
                    }
                } else if(i >= 40 && i < 1240){
                    if(j == 0){
                        g2.drawImage(blocksCeiling.get(animationFrame), (xBorder + i)*scale, (yBorder + j - 20)*scale, w*scale, h2*scale, null);
                    } else if(j == 680){
                        //g2.drawImage(blocksFloor.get(animationFrame), i, j, w, h2, null);
                    }
                }
            }
        }

        g2.drawImage(block25, box12.x, box12.y, box12.width, box12.height, null);
        g2.drawImage(block25, box13.x, box13.y, box13.width, box13.height, null);
        g2.drawImage(block25, box14.x, box14.y, box14.width, box14.height, null);
    }

    @Override
    public void animate() {
        //do nothing
    }
}
