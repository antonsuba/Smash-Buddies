import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Anton on 3/15/2015.
 *
 */
public class Neon extends Map{

    NeonBlock upperLeft;
    NeonBlock upperRight;
    NeonBlock lowerLeft;
    NeonBlock lowerRight;

    NeonBlock left;
    NeonBlock left2;
    NeonBlock right;
    NeonBlock right2;

    NeonBlock ceiling;
    NeonBlock ceiling2;
    NeonBlock floor;
    NeonBlock floor2;

    NeonBlock platform;
    NeonBlock platform2;
    NeonBlock platform3;
    NeonBlock platform4;
    NeonBlock platform5;
    NeonBlock platform6;
    NeonBlock platform7;

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
    Rectangle block18;
    Rectangle block19;
    Rectangle block20;
    Rectangle block21;
    Rectangle block22;
    Rectangle block23;

    ArrayList<NeonBlock> blocks = new ArrayList<NeonBlock>();

    int xBorder, yBorder, scale;

    public Neon(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        upperLeft = new NeonBlock((xBorder)*scale, (yBorder)*scale, 100*scale, 100*scale, "Orange", "Block2");
        upperRight = new NeonBlock((xBorder + 1180)*scale, (yBorder)*scale, 100*scale, 100*scale, "Orange", "Block3");
        lowerLeft = new NeonBlock((xBorder)*scale, (yBorder + 620)*scale, 100*scale, 100*scale, "Orange", "Block");
        lowerRight = new NeonBlock((xBorder + 1180)*scale, (yBorder + 620)*scale, 100*scale, 100*scale, "Orange", "Block4");

        left = new NeonBlock((xBorder)*scale, (yBorder + 720/7 * 2)*scale, 50*scale, 100*scale, "Cyan", "Side");
        left2 = new NeonBlock((xBorder)*scale, (yBorder + 720/7 * 4)*scale, 50*scale, 100*scale, "Cyan", "Side");
        right = new NeonBlock((xBorder + 1230)*scale, (yBorder + 720/7 * 2)*scale, 50*scale, 100*scale, "Cyan", "Side2");
        right2 = new NeonBlock((xBorder + 1230)*scale, (yBorder + 720/7 * 4)*scale, 50*scale, 100*scale, "Cyan", "Side2");

        ceiling = new NeonBlock((xBorder + 1280/5)*scale, (yBorder)*scale, 250*scale, 50*scale, "Yellow", "Ceiling");
        ceiling2 = new NeonBlock((xBorder + 1280/5 * 3)*scale, (yBorder)*scale, 250*scale, 50*scale, "Yellow", "Ceiling");
        floor = new NeonBlock((xBorder + 1280/5)*scale, (yBorder + 670)*scale, 250*scale, 50*scale, "Yellow", "Floor");
        floor2 = new NeonBlock((xBorder + 1280/5 * 3)*scale, (yBorder + 670)*scale, 250*scale, 50*scale, "Yellow", "Floor");

        platform = new NeonBlock((xBorder + (1280/8 - 50))*scale, (yBorder + (720/2 - 25))*scale,
                120*scale, 50*scale, "Green", "Platform");
        platform2 = new NeonBlock((xBorder + (1280/8 * 6 + 80))*scale, (yBorder + (720/2 - 25))*scale,
                120*scale, 50*scale, "Green", "Platform");
        platform3 = new NeonBlock((xBorder + (1280/8 * 2 - 60))*scale, (yBorder + 720/8 * 2)*scale,
                250*scale, 50*scale, "Green", "Platform");
        platform4 = new NeonBlock((xBorder + (1280/8 * 2 - 60))*scale, (yBorder + (720/8 * 5 + 50))*scale,
                250*scale, 50*scale, "Green", "Platform");
        platform5 = new NeonBlock((xBorder + (1280/8 * 5 - 30))*scale, (yBorder + 720/8 * 2)*scale,
                250*scale, 50*scale, "Green", "Platform");
        platform6 = new NeonBlock((xBorder + (1280/8 * 5 - 30))*scale, (yBorder + (720/8 * 5 + 50))*scale,
                250*scale, 50*scale, "Green", "Platform");
        platform7 = new NeonBlock((xBorder + (1280/2 - 50))*scale, (yBorder + (720/2 - 50))*scale,
                100*scale, 100*scale, "Magenta", "Square");

        block1 = new Rectangle(upperLeft.x, upperLeft.y - 100, upperLeft.w - 55, upperLeft.h + 90);
        block2 = new Rectangle(upperRight.x + 55, upperRight.y - 100, upperRight.w, upperRight.h + 90);
        block3 = new Rectangle(lowerLeft.x, lowerLeft.y + 15, lowerLeft.w - 55, lowerLeft.h + 40);
        block4 = new Rectangle(lowerRight.x + 55, lowerRight.y + 15, lowerRight.w, lowerRight.h + 40);
        block5 = left.getBounds();
        block6 = left2.getBounds();
        block7 = right.getBounds();
        block8 = right2.getBounds();
        block9 = ceiling.getBounds();
        block10 = ceiling2.getBounds();
        block11 = floor.getBounds();
        block12 = floor2.getBounds();
        block13 = platform.getBounds();
        block14 = platform2.getBounds();
        block15 = platform3.getBounds();
        block16 = platform4.getBounds();
        block17 = platform5.getBounds();
        block18 = platform6.getBounds();
        block19 = platform7.getBounds();
        block20 = new Rectangle(upperLeft.x, upperLeft.y - 50, upperLeft.w - 10, upperLeft.h - 5);
        block21 = new Rectangle(upperRight.x + 10, upperRight.y - 50, upperRight.w, upperRight.h - 5);
        block22 = new Rectangle(lowerLeft.x, lowerLeft.y + 50, lowerLeft.w - 10, lowerLeft.h);
        block23 = new Rectangle(lowerRight.x + 10, lowerRight.y + 50, lowerRight.w, lowerRight.h);

        blocks.add(upperLeft);
        blocks.add(upperRight);
        blocks.add(lowerLeft);
        blocks.add(lowerRight);
        blocks.add(left);
        blocks.add(left2);
        blocks.add(right);
        blocks.add(right2);
        blocks.add(ceiling);
        blocks.add(ceiling2);
        blocks.add(floor);
        blocks.add(floor2);
        blocks.add(platform);
        blocks.add(platform2);
        blocks.add(platform3);
        blocks.add(platform4);
        blocks.add(platform5);
        blocks.add(platform6);
        blocks.add(platform7);

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
        mapFloors.add(block23);


        Orb a = new Orb(platform3.x + (platform3.w/2) - 50, platform3.y - 80);
        Orb b = new Orb(platform4.x + (platform4.w/2) - 50, platform4.y - 80);
        Orb c = new Orb(platform5.x + (platform5.w/2) - 50, platform5.y - 80);
        Orb d = new Orb(platform6.x + (platform6.w/2) - 50, platform6.y - 80);
        Orb e = new Orb(platform7.x + (platform7.w/2) - 40, platform7.y - 80);

        orbSpawn.add(a);
        orbSpawn.add(b);
        orbSpawn.add(c);
        orbSpawn.add(d);
        orbSpawn.add(e);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect((xBorder)*scale, (yBorder)*scale, 1280*scale, 720*scale);

        upperLeft.draw(g);
        upperRight.draw(g);
        lowerRight.draw(g);
        lowerLeft.draw(g);

        left.draw(g);
        left2.draw(g);
        right.draw(g);
        right2.draw(g);

        ceiling.draw(g);
        ceiling2.draw(g);
        floor.draw(g);
        floor2.draw(g);

        platform.draw(g);
        platform2.draw(g);
        platform3.draw(g);
        platform4.draw(g);
        platform5.draw(g);
        platform6.draw(g);
        platform7.draw(g);

        /**g.setColor(Color.WHITE);
         for(int i = 0; i < mapFloors.size(); i++){
         g.drawRect(mapFloors.get(i).x, mapFloors.get(i).y, mapFloors.get(i).width, mapFloors.get(i).height);
         }*/
    }

    @Override
    public void animate(){
        for (NeonBlock a : blocks) {
            a.animate();
        }
    }
}
