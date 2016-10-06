import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 3/18/2015.
 *
 */
public class ViewCharacter {
    BufferedImage charImg = null;
    String charName = "";
    String activeLabel = "";
    String passiveLabel = "";
    String activeSkill = "";
    String passiveSkill = "";

    int x;
    int y;

    int width;
    int height;

    int xBorder, yBorder, scale;

    public ViewCharacter(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;

        x = (xBorder + 500)*scale;
        y = (yBorder + 80)*scale;
        width = 100*scale;
        height = 200*scale;
    }

    public void draw(Graphics g){
        //Graphics2D g2 = (Graphics2D) g;

        if(charName.equals("Pokemon Trainer")){
            g.setFont(new Font("Synchro LET",Font.PLAIN, 20));
        } else{
            g.setFont(new Font("Synchro LET",Font.PLAIN, 30));
        }

        g.setColor(Color.BLUE);

        if(charName.equals("Pokemon Trainer")){
            g.drawString(charName, (xBorder + 495)*scale, (yBorder + 30)*scale);
        } else{
            g.drawString(charName, (xBorder + 500)*scale, (yBorder + 30)*scale);
        }

        if (charName.equals("Batman")) {
            g.drawImage(charImg, x - 20, y, width + 40, height, null);

        } else if (charName.equals("Ariel")) {
            g.drawImage(charImg, x - 20, y, width + 20, height, null);

        } else if (charName.equals("Groot")) {
            g.drawImage(charImg, x, y, width + 10, height, null);

        } else if (charName.equals("Superman")) {
            g.drawImage(charImg, x - 10, y, width + 10, height, null);

        } else if (charName.equals("Goku")) {
            g.drawImage(charImg, x - 20, y - 10, width + 20, height + 10, null);

        } else {
            g.drawImage(charImg, x, y, width, height, null);

        }

        g.setFont(new Font("Seravek",Font.PLAIN, 15));
        g.drawString(activeLabel, (xBorder + 480)*scale, (yBorder + 360)*scale);
        g.drawString(activeSkill, (xBorder + 560)*scale, (yBorder + 360)*scale);
    }

    public void changeChar(String charName, String activeSkill){
        this.charName = charName;
        this.activeSkill = activeSkill;
        this.passiveSkill = passiveSkill;

        String location = "resources/Sprites/" + charName + ".png";
        try {
            charImg = ImageIO.read(new File(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnLabelVisible(){
        activeLabel = "Active Skill:";
        passiveLabel = "Passive Skill:";
    }

    public void turnLabelInvisible(){
        activeLabel = "";
        passiveLabel = "";
    }
}
