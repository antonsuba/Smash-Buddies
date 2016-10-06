
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class MenuSprite {
    int x;
    int y;
    String character;

    BufferedImage placeholder;
    BufferedImage sprite;
    BufferedImage sprite2;

    ArrayList<BufferedImage> spriteDirection = new ArrayList<BufferedImage>();
    int spriteFrame = 0;

    int Frames = 0;
    int randomFrame;
    Random rn = new Random();

    boolean isOnLeftSide = false;
    boolean isOnRightSide = false;

    int xMax;
    int xMin;

    boolean moving = false;
    boolean movingRight = false;
    boolean movingLeft = false;

    int xVelocity = 0;
    final int xSpeed = 1;
    final int maxXVelocity = 5;

    int xBorder, yBorder, scale;

    public MenuSprite(int x, String character, int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        this.x = (xBorder + x)*scale;
        this.character = character;
        y = (yBorder + 290)*scale;

        try {
            sprite = ImageIO.read(new File("resources/Sprites/" + character + ".png"));
            sprite2 = ImageIO.read(new File("resources/Sprites/" + character + "(FaceLeft).png"));
        } catch (IOException ex) {
            System.out.println("No");
        }

        spriteDirection.add(sprite);
        spriteDirection.add(sprite2);

        randomFrame = rn.nextInt(800 - 200 + 1) + 50;

        if(x < 1280/2){
            placeholder = sprite;
            isOnLeftSide = true;
            isOnRightSide = false;

        } else{
            placeholder = sprite2;
            isOnRightSide = true;
            isOnLeftSide = false;
        }
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if (character.equals("Ariel") || character.equals("Groot") || character.equals("Jake") || character.equals("Superman") || character.equals("Mace Windu")) {
            g2.drawImage(placeholder, x, y, 30, 50, null);

        } else if (character.equals("Batman")) {
            g2.drawImage(placeholder, x, y, 40, 50, null);

        } else if (character.equals("Goku")) {
            g2.drawImage(placeholder, x, y - 10, 35, 60, null);

        } else if (character.equals("Rambo")) {
            g2.drawImage(placeholder, x, y, 27, 50, null);

        } else {
            g2.drawImage(placeholder, x, y, 25, 50, null);

        }
    }

    public void animate(){
        Frames++;

        if(Frames == randomFrame){
            if(!moving){
                moving = true;

                if(isOnLeftSide){
                    xMax = rn.nextInt((1280/8 * 7) - (1280/2 + 200) + 1) + (1280/2 + 200);
                    xMin = rn.nextInt((1280/2 - 200) - (1280/8) + 1) + (1280/8);
                    movingRight = true;
                    movingLeft = false;
                } else if(isOnRightSide){
                    xMin = rn.nextInt((1280/2 - 200) - (1280/8) + 1) + (1280/8);
                    xMax = rn.nextInt((1280/8 * 7) - (1280/2 + 200) + 1) + (1280/2 + 200);
                    movingLeft = true;
                    movingRight = false;

                }

            }

            Frames = 0;
        }

        if(movingRight){
            if(x > xMax){
                moving = false;
                if(!moving){
                    isOnRightSide = true;
                    isOnLeftSide = false;
                    movingRight = false;
                    movingLeft = true;

                    placeholder = sprite2;

                    spriteFrame++;

                    if(spriteFrame == spriteDirection.size()){
                        spriteFrame = 0;
                    }
                }
            }
        } else if(movingLeft){
            if(x < xMin){
                moving = false;
                if(!moving){
                    isOnRightSide = false;
                    isOnLeftSide = true;
                    movingRight = true;
                    movingLeft = false;

                    placeholder = sprite;

                    spriteFrame++;

                    if(spriteFrame == spriteDirection.size()){
                        spriteFrame = 0;
                    }
                }
            }
        }

        if(moving){
            if(isOnLeftSide){
                x += xVelocity;

                xVelocity += xSpeed;
                if (xVelocity >= maxXVelocity){
                    xVelocity = maxXVelocity;
                }
            } else if(isOnRightSide){
                x -= xVelocity;

                xVelocity += xSpeed;
                if (xVelocity >= maxXVelocity){
                    xVelocity = maxXVelocity;
                }
            }
        }


    }
}
