import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anton on 3/2/2015.
 *
 */
public class CharacterSelect{

    int xBorder, yBorder, scale;

    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
    ArrayList<String> characterSelected = new ArrayList<String>();

    ViewCharacter viewPanel;
    BufferedImage ready;

    Rectangle border = new Rectangle(0, 0, 0, 0);

    BufferedImage backgroundImg;
    BufferedImage charSelect1;
    BufferedImage charSelect2;
    BufferedImage charSelect3;

    public boolean sel1Visible = false;
    int sel1X, sel1Y;
    Rectangle selected1 = new Rectangle(10000, 10000, 0, 0);
    public boolean sel2Visible = false;
    int sel2X, sel2Y;
    Rectangle selected2 = new Rectangle(10000, 10000, 0, 0);
    public boolean sel3Visible = false;
    int sel3X, sel3Y;
    Rectangle selected3 = new Rectangle(10000, 10000, 0, 0);

    MenuButton backButton;
    MenuButton readyButton;

    //Values
    int x, y;

    int backX, backY, backWidth, buttonHeight;
    int readyX, readyY, readyWidth;

    int thumbX;

    public CharacterSelect(int xB, int yP, int sc){

        xBorder = xB;
        yBorder = yP;
        scale = sc;

        viewPanel = new ViewCharacter(xBorder,yBorder,scale);

        //Initialize Values
        x = 10;
        y = 720/3;

        backX = (xBorder + (1280/4 - 50))*scale;
        backY = (yBorder + 670)*scale;
        backWidth = 100*scale;
        readyX = (xBorder + ((1280/4) * 3 - 60))*scale;
        readyY = (yBorder + 670)*scale;
        readyWidth = 120*scale;
        buttonHeight = 30*scale;

        thumbX = xBorder + 1280/10;
        //Initialize Values

        try {
            backgroundImg = ImageIO.read(new File("resources/Background/CharacterSelect.png"));
            charSelect1 = ImageIO.read(new File("resources/Icons/1stSelected.png"));
            charSelect2 = ImageIO.read(new File("resources/Icons/2ndSelected.png"));
            charSelect3 = ImageIO.read(new File("resources/Icons/3rdSelected.png"));
            ready = ImageIO.read(new File("resources/MenuItems/Ready.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 1; i <= 11; i++){
            locations.add("resources/Icons/Character" + i + ".png");
        }

        for(int i = 0; i <= 10; i++){
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(locations.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sprites.add(img);
        }

        backButton = new MenuButton("Back", backX, backY, backWidth, buttonHeight);
        readyButton = new MenuButton("Ready", readyX, readyY, readyWidth, buttonHeight);

        readyButton.width = 0;
        readyButton.height = 0;
    }

    public void draw(Graphics g){
        x = (xBorder + 1280/10)*scale;
        y = (yBorder + 720/2 + 48)*scale;

        g.drawImage(backgroundImg, 0, 0, (xBorder*2 + 1280)*scale, (yBorder*2 + 720)*scale, null);

        Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i <= sprites.size() -1; i++){
            g2.drawImage(sprites.get(i), x, y, 60, 60, null);

            x += 90;
        }

        readyButton.draw(g);
        backButton.draw(g);

        g.drawRect(border.x, border.y, border.width, border.height);

        g2.setStroke(new BasicStroke(5));
        g.setColor(Color.red);
        g.drawImage(charSelect1, selected1.x, selected1.y, selected1.width, selected1.height, null);
        g.setColor(Color.GREEN);
        g.drawImage(charSelect2, selected2.x, selected2.y, selected2.width, selected2.height, null);
        g.setColor(Color.yellow);
        g.drawImage(charSelect3, selected3.x, selected3.y, selected3.width, selected3.height, null);

        viewPanel.draw(g);


    }

    public void drawBorder(int x, int y){
        border.setLocation(x, y);
        border.setSize(60*scale, 60*scale);
    }

    public void removeBorder(){
        border.setSize(0, 0);
    }

    public void removeCharBorders(){
        if(sel1Visible){
            sel1X = 0;
            sel1Y = 0;
            selected1.setLocation(10000, 10000);
            selected1.setSize(0, 0);
            sel1Visible = false;
            LaunchGame.gamePlay.characterList.remove(0);

            if(sel2Visible){
                sel2X = 0;
                sel2Y = 0;
                selected2.setLocation(10000, 10000);
                selected2.setSize(0, 0);
                sel2Visible = false;
                LaunchGame.gamePlay.characterList.remove(0);

                if(sel3Visible){
                    sel3X = 0;
                    sel3Y = 0;
                    selected3.setLocation(10000, 10000);
                    selected3.setSize(0, 0);
                    sel3Visible = false;
                    LaunchGame.gamePlay.characterList.remove(0);
                }
            } else if(sel3Visible){
                sel3X = 0;
                sel3Y = 0;
                selected3.setLocation(10000, 10000);
                selected3.setSize(0, 0);
                sel3Visible = false;
                LaunchGame.gamePlay.characterList.remove(1);
            }
        } else if(sel2Visible){
            sel2X = 0;
            sel2Y = 0;
            selected2.setLocation(10000, 10000);
            selected2.setSize(0, 0);
            sel2Visible = false;
            LaunchGame.gamePlay.characterList.remove(1);

            if(sel3Visible){
                sel3X = 0;
                sel3Y = 0;
                selected3.setLocation(10000, 10000);
                selected3.setSize(0, 0);
                sel3Visible = false;
                LaunchGame.gamePlay.characterList.remove(1);
            }
        } else if(sel3Visible){
            sel3X = 0;
            sel3Y = 0;
            selected3.setLocation(10000, 10000);
            selected3.setSize(0, 0);
            sel3Visible = false;
            LaunchGame.gamePlay.characterList.remove(0);
        }
    }

    public void selectChar(int x, int y){
        if(sel1X == x && sel1Y == y){
            sel1X = 0;
            sel1Y = 0;
            selected1.setLocation(10000, 10000);
            selected1.setSize(0, 0);
            sel1Visible = false;
            LaunchGame.gamePlay.characterList.remove(0);
        } else if(sel2X == x && sel2Y == y){
            selectChar2(x, y);
        } else if(sel3X == x && sel3Y == y){
            selectChar3(x, y);
        } else if(sel1Visible){
            if(sel2Visible){
                if(sel3Visible){
                    System.out.println("Roster is full");

                } else{
                    selectChar3(x, y);
                }
            } else if(sel3Visible){
                if(sel2Visible){
                    System.out.println("Roster is full");
                } else{
                    selectChar2(x, y);

                }
            } else{
                selectChar2(x, y);
            }
        } else if(sel2Visible){
            if(sel3Visible){
                if(sel1Visible){
                    System.out.println("Roster is full");
                } else{
                    sel1X = x;
                    sel1Y = y;
                    selected1.setLocation(x, y);
                    selected1.setSize(61, 61);
                    sel1Visible = true;
                    if(x == thumbX*scale){
                        //characterSelected.add("Aang");
                        LaunchGame.gamePlay.characterList.add(new Aang(xBorder,yBorder,scale,100, 100, 45, 80));
                    } else if(x == (thumbX + 90)*scale){
                        //characterSelected.add("Ariel");
                        LaunchGame.gamePlay.characterList.add(new Ariel(xBorder,yBorder,scale,100, 100, 50, 80));
                    } else if(x == (thumbX + 90*2)*scale){
                        //characterSelected.add("Batman");
                        LaunchGame.gamePlay.characterList.add(new Batman(xBorder,yBorder,scale,100, 100, 60, 80));
                    } else if(x == (thumbX + 90*3)*scale){
                        //characterSelected.add("BruceLee");
                        LaunchGame.gamePlay.characterList.add(new BruceLee(xBorder,yBorder,scale,100, 100, 45, 80));
                    } else if(x == (thumbX + 90*4)*scale){
                        //characterSelected.add("Groot");
                        LaunchGame.gamePlay.characterList.add(new Groot(xBorder,yBorder,scale,100, 100, 50, 80));
                    } else if(x == (thumbX + 90*5)*scale){
                        //characterSelected.add("Jake");
                        LaunchGame.gamePlay.characterList.add(new Jake(xBorder,yBorder,scale,100, 100, 50, 80));
                    } else if(x == (thumbX + 90*6)*scale){
                        //characterSelected.add("PokemonTrainer");
                        LaunchGame.gamePlay.characterList.add(new PokemonTrainer(xBorder,yBorder,scale, 100, 100, 45, 80));
                    } else if(x == (thumbX + 90*7)*scale){
                        //characterSelected.add("SuperMan");
                        LaunchGame.gamePlay.characterList.add(new SuperMan(xBorder,yBorder,scale, 100, 100, 50, 80));
                    } else if(x == (thumbX + 90*8)*scale){
                        //characterSelected.add("Goku");
                        LaunchGame.gamePlay.characterList.add(new Goku(xBorder,yBorder,scale, 100, 100, 60, 100));
                    } else if(x == (thumbX + 90*9)*scale){
                        //characterSelected.add("Rambo");
                        LaunchGame.gamePlay.characterList.add(new Rambo(xBorder,yBorder,scale, 100, 100, 45, 80));
                    } else if(x == (thumbX + 90*10)*scale) {
                        //characterSelected.add("Mace Windu");
                        LaunchGame.gamePlay.characterList.add(new MaceWindu(xBorder, yBorder, scale, 100, 100, 50, 80));
                    }
                }
            } else{
                sel1X = x;
                sel1Y = y;
                selected1.setLocation(x, y);
                selected1.setSize(61, 61);
                sel1Visible = true;
                if(x == thumbX*scale){
                    //characterSelected.add("Aang");
                    LaunchGame.gamePlay.characterList.add(new Aang(xBorder,yBorder,scale,100, 100, 45, 80));
                } else if(x == (thumbX + 90)*scale){
                    //characterSelected.add("Ariel");
                    LaunchGame.gamePlay.characterList.add(new Ariel(xBorder,yBorder,scale,100, 100, 50, 80));
                } else if(x == (thumbX + 90*2)*scale){
                    //characterSelected.add("Batman");
                    LaunchGame.gamePlay.characterList.add(new Batman(xBorder,yBorder,scale,100, 100, 60, 80));
                } else if(x == (thumbX + 90*3)*scale){
                    //characterSelected.add("BruceLee");
                    LaunchGame.gamePlay.characterList.add(new BruceLee(xBorder,yBorder,scale,100, 100, 45, 80));
                } else if(x == (thumbX + 90*4)*scale){
                    //characterSelected.add("Groot");
                    LaunchGame.gamePlay.characterList.add(new Groot(xBorder,yBorder,scale,100, 100, 50, 80));
                } else if(x == (thumbX + 90*5)*scale){
                    //characterSelected.add("Jake");
                    LaunchGame.gamePlay.characterList.add(new Jake(xBorder,yBorder,scale,100, 100, 50, 80));
                } else if(x == (thumbX + 90*6)*scale){
                    //characterSelected.add("PokemonTrainer");
                    LaunchGame.gamePlay.characterList.add(new PokemonTrainer(xBorder,yBorder,scale, 100, 100, 45, 80));
                } else if(x == (thumbX + 90*7)*scale){
                    //characterSelected.add("SuperMan");
                    LaunchGame.gamePlay.characterList.add(new SuperMan(xBorder,yBorder,scale, 100, 100, 50, 80));
                } else if(x == (thumbX + 90*8)*scale){
                    //characterSelected.add("Goku");
                    LaunchGame.gamePlay.characterList.add(new Goku(xBorder,yBorder,scale, 100, 100, 60, 100));
                } else if(x == (thumbX + 90*10)*scale){
                    //characterSelected.add("Mace Windu");
                    LaunchGame.gamePlay.characterList.add(new MaceWindu(xBorder,yBorder,scale, 100, 100, 50, 80));
                }
            }
        } else{
            sel1X = x;
            sel1Y = y;
            selected1.setLocation(x, y);
            selected1.setSize(61, 61);
            sel1Visible = true;
            if(x == thumbX*scale){
                //characterSelected.add("Aang");
                LaunchGame.gamePlay.characterList.add(new Aang(xBorder,yBorder,scale,100, 100, 45, 80));
            } else if(x == (thumbX + 90)*scale){
                //characterSelected.add("Ariel");
                LaunchGame.gamePlay.characterList.add(new Ariel(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*2)*scale){
                //characterSelected.add("Batman");
                LaunchGame.gamePlay.characterList.add(new Batman(xBorder,yBorder,scale,100, 100, 60, 80));
            } else if(x == (thumbX + 90*3)*scale){
                //characterSelected.add("BruceLee");
                LaunchGame.gamePlay.characterList.add(new BruceLee(xBorder,yBorder,scale,100, 100, 45, 80));
            } else if(x == (thumbX + 90*4)*scale){
                //characterSelected.add("Groot");
                LaunchGame.gamePlay.characterList.add(new Groot(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*5)*scale){
                //characterSelected.add("Jake");
                LaunchGame.gamePlay.characterList.add(new Jake(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*6)*scale){
                //characterSelected.add("PokemonTrainer");
                LaunchGame.gamePlay.characterList.add(new PokemonTrainer(xBorder,yBorder,scale, 100, 100, 45, 80));
            } else if(x == (thumbX + 90*7)*scale){
                //characterSelected.add("SuperMan");
                LaunchGame.gamePlay.characterList.add(new SuperMan(xBorder,yBorder,scale, 100, 100, 50, 80));
            } else if(x == (thumbX + 90*8)*scale){
                //characterSelected.add("Goku");
                LaunchGame.gamePlay.characterList.add(new Goku(xBorder,yBorder,scale, 100, 100, 60, 100));
            } else if(x == (thumbX + 90*9)*scale){
                //characterSelected.add("Rambo");
                LaunchGame.gamePlay.characterList.add(new Rambo(xBorder,yBorder,scale, 100, 100, 45, 80));
            } else if(x == (thumbX + 90*10)*scale){
                //characterSelected.add("Mace Windu");
                LaunchGame.gamePlay.characterList.add(new MaceWindu(xBorder,yBorder,scale, 100, 100, 50, 80));
            }
        }

        if(sel1Visible && sel2Visible && sel3Visible){
            readyVisibility("visible");
        } else{
            readyVisibility("invisible");
        }
    }

    public void selectChar2(int x, int y){
        if(sel2X == x && sel2Y == y){
            sel2X = 0;
            sel2Y = 0;
            selected2.setLocation(10000, 10000);
            selected2.setSize(0, 0);
            sel2Visible = false;
            if(sel1Visible){
                LaunchGame.gamePlay.characterList.remove(1);
            } else{
                LaunchGame.gamePlay.characterList.remove(0);
            }
            System.out.println(LaunchGame.gamePlay.characterList.size());
        } else{
            sel2X = x;
            sel2Y = y;
            selected2.setLocation(x, y);
            selected2.setSize(61, 61);
            sel2Visible = true;
            if(x == thumbX*scale){
                //characterSelected.add("Aang");
                LaunchGame.gamePlay.characterList.add(new Aang(xBorder,yBorder,scale,100, 100, 45, 80));
            } else if(x == (thumbX + 90)*scale){
                //characterSelected.add("Ariel");
                LaunchGame.gamePlay.characterList.add(new Ariel(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*2)*scale){
                //characterSelected.add("Batman");
                LaunchGame.gamePlay.characterList.add(new Batman(xBorder,yBorder,scale,100, 100, 60, 80));
            } else if(x == (thumbX + 90*3)*scale){
                //characterSelected.add("BruceLee");
                LaunchGame.gamePlay.characterList.add(new BruceLee(xBorder,yBorder,scale,100, 100, 45, 80));
            } else if(x == (thumbX + 90*4)*scale){
                //characterSelected.add("Groot");
                LaunchGame.gamePlay.characterList.add(new Groot(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*5)*scale){
                //characterSelected.add("Jake");
                LaunchGame.gamePlay.characterList.add(new Jake(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*6)*scale){
                //characterSelected.add("PokemonTrainer");
                LaunchGame.gamePlay.characterList.add(new PokemonTrainer(xBorder,yBorder,scale, 100, 100, 45, 80));
            } else if(x == (thumbX + 90*7)*scale){
                //characterSelected.add("SuperMan");
                LaunchGame.gamePlay.characterList.add(new SuperMan(xBorder,yBorder,scale, 100, 100, 50, 80));
            } else if(x == (thumbX + 90*8)*scale){
                //characterSelected.add("Goku");
                LaunchGame.gamePlay.characterList.add(new Goku(xBorder,yBorder,scale, 100, 100, 60, 100));
            } else if(x == (thumbX + 90*9)*scale){
                //characterSelected.add("Rambo");
                LaunchGame.gamePlay.characterList.add(new Rambo(xBorder,yBorder,scale, 100, 100, 45, 80));
            } else if(x == (thumbX + 90*10)*scale){
                //characterSelected.add("Mace Windu");
                LaunchGame.gamePlay.characterList.add(new MaceWindu(xBorder,yBorder,scale, 100, 100, 50, 80));
            }
        }

        if(sel1Visible && sel2Visible && sel3Visible){
            readyVisibility("visible");
        } else{
            readyVisibility("invisible");
        }
    }

    public void selectChar3(int x, int y){
        if(sel3X == x && sel3Y == y){
            sel3X = 0;
            sel3Y = 0;
            selected3.setLocation(10000, 10000);
            selected3.setSize(0, 0);
            sel3Visible = false;
            if(sel1Visible){
                if(sel2Visible){
                    LaunchGame.gamePlay.characterList.remove(2);
                } else{
                    LaunchGame.gamePlay.characterList.remove(1);
                }
            } else if(sel2Visible){
                LaunchGame.gamePlay.characterList.remove(1);
            } else{
                LaunchGame.gamePlay.characterList.remove(0);
            }
        } else{
            sel3X = x;
            sel3Y = y;
            selected3.setLocation(x, y);
            selected3.setSize(61, 61);
            sel3Visible = true;
            if(x == thumbX*scale){
                //characterSelected.add("Aang");
                LaunchGame.gamePlay.characterList.add(new Aang(xBorder,yBorder,scale,100, 100, 45, 80));
            } else if(x == (thumbX + 90)*scale){
                //characterSelected.add("Ariel");
                LaunchGame.gamePlay.characterList.add(new Ariel(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*2)*scale){
                //characterSelected.add("Batman");
                LaunchGame.gamePlay.characterList.add(new Batman(xBorder,yBorder,scale,100, 100, 60, 80));
            } else if(x == (thumbX + 90*3)*scale){
                //characterSelected.add("BruceLee");
                LaunchGame.gamePlay.characterList.add(new BruceLee(xBorder,yBorder,scale,100, 100, 45, 80));
            } else if(x == (thumbX + 90*4)*scale){
                //characterSelected.add("Groot");
                LaunchGame.gamePlay.characterList.add(new Groot(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*5)*scale){
                //characterSelected.add("Jake");
                LaunchGame.gamePlay.characterList.add(new Jake(xBorder,yBorder,scale,100, 100, 50, 80));
            } else if(x == (thumbX + 90*6)*scale){
                //characterSelected.add("PokemonTrainer");
                LaunchGame.gamePlay.characterList.add(new PokemonTrainer(xBorder,yBorder,scale, 100, 100, 45, 80));
            } else if(x == (thumbX + 90*7)*scale){
                //characterSelected.add("SuperMan");
                LaunchGame.gamePlay.characterList.add(new SuperMan(xBorder,yBorder,scale, 100, 100, 50, 80));
            } else if(x == (thumbX + 90*8)*scale){
                //characterSelected.add("Goku");
                LaunchGame.gamePlay.characterList.add(new Goku(xBorder,yBorder,scale, 100, 100, 60, 100));
            } else if(x == (thumbX + 90*9)*scale){
                //characterSelected.add("Rambo");
                LaunchGame.gamePlay.characterList.add(new Rambo(xBorder,yBorder,scale, 100, 100, 45, 80));
            } else if(x == (thumbX + 90*10)*scale) {
                //characterSelected.add("Mace Windu");
                LaunchGame.gamePlay.characterList.add(new MaceWindu(xBorder, yBorder, scale, 100, 100, 50, 80));
            }
        }

        if(sel1Visible && sel2Visible && sel3Visible){
            readyVisibility("visible");
        } else{
            readyVisibility("invisible");
        }
    }

    public void readyVisibility(String condition){
        if(condition.equals("visible")){
            readyButton.width = 120;
            readyButton.height = 25;
        } else{
            readyButton.width = 0;
            readyButton.height = 0;
        }
    }

    public void animate() {

        int x = MouseMotionHandler.MouseX;
        int y = MouseMotionHandler.MouseY;

        int thumbXLimit = xBorder + 1280/10 + 60;

        int thumbY = (yBorder +(720/2 + 48))*scale;
        int thumbYLimit = (yBorder + (720/2 + 60 + 48))*scale;

        int drawBorderY = (yBorder + (720/2 + 48))*scale;


        if(x >= thumbX*scale && x <= thumbXLimit*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Aang", "Avatar State");
            drawBorder(thumbX*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90)*scale && x <= (thumbXLimit + 90)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Ariel", "Serenade");
            drawBorder((thumbX + 90)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*2)*scale && x <= (thumbXLimit + 90*2)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Batman", "Batarangs");
            drawBorder((thumbX + 90*2)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*3)*scale && x <= (thumbXLimit + 90*3)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Bruce Lee", "Flaming Kick");
            drawBorder((thumbX + 90*3)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*4)*scale && x <= (thumbXLimit + 90*4)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Groot", "Regeneration");
            drawBorder((thumbX + 90*4)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*5)*scale && x <= (thumbXLimit + 90*5)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Jake", "Shape Shift");
            drawBorder((thumbX + 90*5)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*6)*scale && x <= (thumbXLimit + 90*6)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Pokemon Trainer", "Summon Charizard");
            drawBorder((thumbX + 90*6)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*7)*scale && x <= (thumbXLimit + 90*7)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Superman", "Laser Eyes");
            drawBorder((thumbX + 90*7)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*8)*scale && x <= (thumbXLimit + 90*8)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Goku", "Kamehameha");
            drawBorder((thumbX + 90*8)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*9)*scale && x <= (thumbXLimit + 90*9)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Rambo", "Machine Gun");
            drawBorder((thumbX + 90*9)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= (thumbX + 90*10)*scale && x <= (thumbXLimit + 90*10)*scale && y >= thumbY && y <= thumbYLimit){
            viewPanel.changeChar("Mace Windu", "Vaapad");
            drawBorder((thumbX + 90*10)*scale, drawBorderY);
            viewPanel.turnLabelVisible();
        } else if(x >= readyX && x <= readyX + readyWidth && y >= readyY && y <= readyY + buttonHeight){
            if(sel1Visible && sel2Visible && sel3Visible){
                readyButton.start();
            }
        } else if(x >= backX && x <= backX + backWidth && y >= backY && y <= backY + buttonHeight){
            backButton.start();
        } else{
            removeBorder();
            backButton.stop();
            readyButton.stop();
        }
    }
}
