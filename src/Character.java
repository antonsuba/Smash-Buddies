import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/3/2015.
 *
 */
abstract class Character {

    BufferedImage charImgLeft;
    BufferedImage charImgRight;
    BufferedImage specialSkillLeft;
    BufferedImage specialSkillRight;

    String charLocationLeft = "";
    String charLocationRight = "";
    String specialLocationLeft = "";
    String specialLocationRight = "";
    String id;
    String playerName = "";
    String enemyName = "";

    int xBorder, yBorder, scale;
    int xPos, yPos, height, width;
    int xVelocity = 0;
    int yVelocity = 0;
    final int xSpeed = 1;
    final int maxXVelocity = 15;
    final int maxYVelocity = 22;
    final int gravity = 1;

    boolean facingLeft = false;
    boolean facingRight = true;
    boolean movingLeft = false;
    boolean movingRight = false;
    boolean falling = true;
    boolean jumping = false;
    boolean canJump = false;

    int livesLeft = 3;
    boolean isAlive = true;
    boolean immunity = false;
    boolean emptyCharChecker = false;
    int immunityCounter;
    int hitAnimationCounter;

    boolean tempImmunity = false;
    int tempImmunityLimit = 20;
    int tempImmunityCounter;

    boolean canAttack = false;
    boolean canUseSpecial = true;
    boolean specialActive = false;
    boolean bulletFired = false;
    boolean specialUsed = false;
    int skillTimeLimit;
    int xTarget;
    int yTarget;

    int deathAnimationFrame;
    boolean deathAnimationFinish = false;
    BufferedImage blood1, blood2, blood3, blood4;

    boolean isAnEnemy = false;

    boolean hasMoved = false;
    boolean canMove = true;

    public Character(int x, int y, int sc, int xP, int yP, int w, int h){
        xBorder = x;
        yBorder = y;
        scale = sc;

        xPos = xP;
        yPos = yP;
        width = w;
        height = h;

        playerName = LaunchGame.gamePlay.userName;
        enemyName = LaunchGame.gamePlay.enemyName;

        try {
            blood1 = ImageIO.read(new File("resources/Objects/Blood.png"));
            blood2 = ImageIO.read(new File("resources/Objects/Blood2.png"));
            blood3 = ImageIO.read(new File("resources/Objects/Blood3.png"));
            blood4 = ImageIO.read(new File("resources/Objects/Blood4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        try {
            charImgLeft = ImageIO.read(new File(charLocationLeft));
            charImgRight = ImageIO.read(new File(charLocationRight));
            specialSkillLeft = ImageIO.read(new File(specialLocationLeft));
            specialSkillRight = ImageIO.read(new File(specialLocationRight));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!isAlive){
            deathAnimationFrame++;
            if (deathAnimationFrame <= 3) {
                g.drawImage(blood1,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else if (deathAnimationFrame <= 6) {
                g.drawImage(blood2,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else if (deathAnimationFrame <= 9) {
                g.drawImage(blood3,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else if (deathAnimationFrame <= 20) {
                g.drawImage(blood4,xPos - 10*scale,yPos - 10*scale,width + 10*scale,height + scale*scale,null);
            }else{
                deathAnimationFinish = true;
            }
        }
        else if(specialActive && !emptyCharChecker){
            if(facingLeft){
                g.drawImage(specialSkillLeft, xPos, yPos, width, height, null);
            }
            else if(facingRight){
                g.drawImage(specialSkillRight, xPos, yPos, width, height, null);
            }
        }
        else if(!specialActive && !emptyCharChecker){
            if (facingLeft) {
                g.drawImage(charImgLeft, xPos, yPos, width, height, null);
            }
            else if (facingRight) {
                g.drawImage(charImgRight, xPos, yPos, width, height, null);
            }
        }
        g.setFont(new Font("Seravek",Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        if(isAnEnemy){
            int stringLen = (int) g2.getFontMetrics().getStringBounds(enemyName, g2).getWidth();
            int start = width / 2 - stringLen / 2;
            g.drawString(enemyName, start + xPos, yPos - 10);
        }
        else {
            int stringLen = (int) g2.getFontMetrics().getStringBounds(playerName, g2).getWidth();
            int start = width / 2 - stringLen / 2;
            g.drawString(playerName, start + xPos, yPos - 10);
        }
    }

    public void animate() {
        if (tempImmunity) {
            tempImmunityCounter++;
            if (tempImmunityCounter >= tempImmunityLimit) {
                tempImmunity = false;
                tempImmunityCounter = 0;
            }
        }

        //Immunity when hit
        if (immunity) {
            immunityCounter++;
            if (immunityCounter >= 180) {
                immunity = false;
                emptyCharChecker = false;
                immunityCounter = 0;
            }

            hitAnimationCounter++;
            if (hitAnimationCounter == 5) {
                emptyCharChecker = true;
            } else if (hitAnimationCounter == 10) {
                emptyCharChecker = false;
                hitAnimationCounter = 0;
            }

        }
        //Immunity when hit

        //Character Movement
        if (canMove) {
            if (movingLeft) {
                xPos -= xVelocity;

                xVelocity += xSpeed;
                if (xVelocity >= maxXVelocity) {
                    xVelocity = maxXVelocity;
                }
                if (xPos + width <= xBorder * scale) {
                    xPos = (xBorder + 1280) * scale;
                }
            } else if (movingRight) {
                xPos += xVelocity;

                xVelocity += xSpeed;
                if (xVelocity >= maxXVelocity) {
                    xVelocity = maxXVelocity;
                }
                if (xPos >= (xBorder + 1280) * scale) {
                    xPos = xBorder * scale - width;
                }
            }

            if (falling) {
                yPos += yVelocity;

                yVelocity += gravity;
                if (yVelocity >= maxYVelocity) {
                    yVelocity = maxYVelocity;
                }
                if (yPos >= (yBorder + 720) * scale) {
                    yPos = (yBorder) * scale - height;
                }
            } else if (jumping && canJump) {
                yPos -= yVelocity;

                yVelocity -= gravity;
                if (yVelocity == 0) {
                    falling = true;
                    jumping = false;
                }
                if (yPos + height <= (yBorder) * scale) {
                    yPos = (yBorder + 720) * scale;
                }
            }
            //Character Movement

            attackCollision();
            Collision();
        }
    }

    //Collision Bounds
    public Rectangle getBounds(){ return new Rectangle(xPos, yPos, width , height); }
    public Rectangle getTopBounds(){ return new Rectangle(xPos, yPos, width , height/4); }
    public Rectangle getBottomBounds(){ return  new Rectangle(xPos, yPos + ((height/4)*3), width, height/4); }
    public Rectangle getLeftBounds(){ return  new Rectangle(xPos, yPos, width/4, height); }
    public Rectangle getRightBounds(){ return  new Rectangle(xPos + ((width/4)*3), yPos, width/4, height); }
    //Collision Bounds

    //Collision for bullets and special attack
    public void attackCollision(){
        for(int i = 0; i < LaunchGame.gamePlay.bulletList.size(); i++){

            Rectangle bullet = LaunchGame.gamePlay.bulletList.get(i).getBounds();

            if(bullet.intersects(getBounds())){
                if(!tempImmunity){
                    if(!immunity) {
                        if(!isAnEnemy) {
                            livesLeft--;
                        }
                        LaunchGame.gamePlay.bulletList.remove(i);
                        soundClip("PlayerHit");
                    }
                    immunity = true;
                    if(livesLeft == 0){
                        isAlive = false;
                    }
                }
            }
        }
    }
    //Collision

    //Collision
    public void Collision(){
        for(int i = 0; i < LaunchGame.gamePlay.currentMap.mapFloors.size(); i++){

            Rectangle object = LaunchGame.gamePlay.currentMap.mapFloors.get(i);

            //Top Collision
            if(object.intersects(getTopBounds())) {

                if(object.intersects(getBottomBounds()) && object.intersects(getLeftBounds())){
                    xPos =  (int) object.getX() + (int) object.getWidth();
                }

                else if(object.intersects(getBottomBounds()) && object.intersects(getRightBounds())){
                    xPos = ((int) object.getX() - width);
                }

                else {
                    yPos = (int) object.getY() + (int) object.getHeight();
                    jumping = false;
                    canJump = false;
                    falling = true;
                }
            }

            //Bottom Collision
            else if(object.intersects(getBottomBounds())) {
                yPos = (int) object.getY() - height;
                falling = false;
                canJump = true;
            }

            else if(!jumping){
                falling = true;
            }
        }
    }
    //Collision

    //Attack
    public void fireBullet(int x, int y){
        if(canAttack && !specialActive) {
            xTarget = x;
            yTarget = y;
            bulletFired = true;

            Bullet bullet = new Bullet(xBorder, yBorder, scale, xPos, yPos, xTarget, yTarget);
            LaunchGame.gamePlay.bulletList.add(bullet);
            canAttack = false;
            tempImmunity = true;
        }
    }

    abstract public void specialSkill(int x, int y);
    //Attack

    //Used to update if this character is the enemy character
    public void update(int x, int y){
        xPos = x;
        yPos = y;

        //livesLeft = lives;
    }

    //Get name of character
    public String getID(){
        return id;
    }

    //Play sound clip
    public void soundClip(String name){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Sound Clips/" + name + ".wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();

        } catch (Exception e){
            System.out.println("Sound Error");
        }

    }

    abstract public void specialSoundClip();
}


