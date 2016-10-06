import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Anton on 3/2/2015.
 *
 */

public class MoueEventHandler implements MouseListener{

    int x;
    int y;

    int xBorder, yBorder, scale;

    public MoueEventHandler(int xB, int yB, int sc){
        xBorder = xB;
        yBorder = yB;
        scale = sc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        if(LaunchGame.State == LaunchGame.STATE.MENU){
            MenuMouseEvent();
        }
        else if(LaunchGame.State == LaunchGame.STATE.CHARSEL){
            CharSelectMouseEvent();
        }
        else if(LaunchGame.State == LaunchGame.STATE.MAPSEL){
            MapSelectMouseEvent();
        }
        else if(LaunchGame.State == LaunchGame.STATE.GAME){
            GameMouseEvent(e);
        }
    }

    public void MenuMouseEvent(){

        int createX = StartMenu.createX;
        int joinX = StartMenu.joinX;
        int creditsX = StartMenu.creditsX;
        int exitX = StartMenu.exitX;

        int createY = StartMenu.createY;
        int joinY = StartMenu.joinY;
        int creditsY = StartMenu.creditsY;
        int exitY = StartMenu.exitY;

        int createWidth = StartMenu.createWidth;
        int joinWidth = StartMenu.joinWidth;
        int creditsWidth = StartMenu.creditsWidth;
        int exitWidth = StartMenu.exitWidth;
        int buttonHeight = StartMenu.buttonHeight;

        if(x >= createX && x <= createX + createWidth && y >= createY && y <= createY + buttonHeight){
            LaunchGame.gamePlay.userName = JOptionPane.showInputDialog("Input user name: ");
            LaunchGame.startServer();
            LaunchGame.State = LaunchGame.STATE.CHARSEL;
        }
        else if(x >= joinX && x <= joinX + joinWidth && y >= joinY && y <= joinY + buttonHeight){
            LaunchGame.gamePlay.userName = JOptionPane.showInputDialog("Input user name: ");
            LaunchGame.startClient();
            LaunchGame.State = LaunchGame.STATE.CHARSEL;
        }
        else if(x >= creditsX && x <= creditsX + creditsWidth && y >= creditsY && y <= creditsY + buttonHeight){
            System.out.println("Credits");
        }
        else if(x >= exitX && x <= exitX + exitWidth && y >= exitY && y <= exitY + buttonHeight){
            LaunchGame.running = false;
            System.exit(1);
        }
    }

    public void CharSelectMouseEvent(){

        int thumbX = xBorder + 1280/10;
        int thumbXLimit = xBorder + 1280/10 + 60;

        int thumbY = (yBorder +(720/2 + 48))*scale;
        int thumbYLimit = (yBorder + (720/2 + 60 + 48))*scale;

        if(x >= LaunchGame.characterSelect.readyX && x <= LaunchGame.characterSelect.readyX + LaunchGame.characterSelect.readyWidth
                && y >= LaunchGame.characterSelect.readyY && y <= LaunchGame.characterSelect.readyY + LaunchGame.characterSelect.buttonHeight){
            if(LaunchGame.characterSelect.sel1Visible && LaunchGame.characterSelect.sel2Visible && LaunchGame.characterSelect.sel3Visible) {
                addNamesToList(LaunchGame.gamePlay.characterList);
                if(LaunchGame.serverRunning) {
                    LaunchGame.State = LaunchGame.STATE.MAPSEL;
                }
                else if(LaunchGame.clientRunning){
                    if(LaunchGame.gameClient.mapLoaded){
                        LaunchGame.State = LaunchGame.STATE.GAME;
                    }
                    else{
                        LaunchGame.State = LaunchGame.STATE.WAIT;
                    }
                }
            }
        }
        else if(x >= LaunchGame.characterSelect.backX && x <= LaunchGame.characterSelect.backX + LaunchGame.characterSelect.backWidth &&
                y >= LaunchGame.characterSelect.backY && y <= LaunchGame.characterSelect.backY + LaunchGame.characterSelect.buttonHeight){
            LaunchGame.State = LaunchGame.STATE.MENU;
            LaunchGame.characterSelect.removeCharBorders();
        }
        else if(x >= thumbX*scale && x <= thumbXLimit*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar(thumbX*scale, thumbY);
        }
        else if(x >= (thumbX + 90)*scale && x <= (thumbXLimit + 90)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*2)*scale && x <= (thumbXLimit + 90*2)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*2)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*3)*scale && x <= (thumbXLimit + 90*3)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*3)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*4)*scale && x <= (thumbXLimit + 90*4)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*4)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*5)*scale && x <= (thumbXLimit + 90*5)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*5)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*6)*scale && x <= (thumbXLimit + 90*6)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*6)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*7)*scale && x <= (thumbXLimit + 90*7)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*7)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*8)*scale && x <= (thumbXLimit + 90*8)*scale && y >= thumbY && y <= thumbYLimit){
            LaunchGame.characterSelect.selectChar((thumbX + 90*8)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*9)*scale && x <= (thumbXLimit + 90*9)*scale && y >= thumbY && y <= thumbYLimit) {
            LaunchGame.characterSelect.selectChar((thumbX + 90*9)*scale, thumbY);
        }
        else if(x >= (thumbX + 90*10)*scale && x <= (thumbXLimit + 90*10)*scale && y >= thumbY && y <= thumbYLimit) {
            LaunchGame.characterSelect.selectChar((thumbX + 90*10)*scale, thumbY);
        }
    }

    public void addNamesToList(ArrayList<Character> list){
        for (Character aList : list) {
            LaunchGame.characterSelect.characterSelected.add(aList.getID());
        }
    }

    public void MapSelectMouseEvent(){

        int dojoX = LaunchGame.mapSelect.dojoX;
        int grayRoomX = LaunchGame.mapSelect.grayRoomX;
        int blockRoomX = LaunchGame.mapSelect.blockRoomX;
        int neonX = LaunchGame.mapSelect.neonX;
        int thumbY = LaunchGame.mapSelect.thumbY;
        int width = LaunchGame.mapSelect.w;
        int height = LaunchGame.mapSelect.h;

        if(x >= LaunchGame.mapSelect.backX && x <= LaunchGame.mapSelect.backX + 120
                && y >= LaunchGame.mapSelect.backY && y <= LaunchGame.mapSelect.backY + 25){
            LaunchGame.State = LaunchGame.STATE.CHARSEL;
            LaunchGame.mapSelect.removeBorder();
        }
        else if(x >= LaunchGame.mapSelect.startX && x <= LaunchGame.mapSelect.startX + 120
                && y >= LaunchGame.mapSelect.startY && y <= LaunchGame.mapSelect.startY + 25){
            LaunchGame.gamePlay.currentMap = LaunchGame.gamePlay.mapList.get(0);
            LaunchGame.gamePlay.orbList = LaunchGame.gamePlay.currentMap.orbSpawn;
            LaunchGame.State = LaunchGame.STATE.GAME;
        }
        else if(x >= dojoX && x <= dojoX + width && y >= thumbY && y <= thumbY + height){
            LaunchGame.mapSelect.drawBorder(dojoX, thumbY);
        }
        else if(x >= grayRoomX && x <= grayRoomX + width && y >= thumbY && y <= thumbY + height){
            LaunchGame.mapSelect.drawBorder(grayRoomX, thumbY);
        }
        else if(x >= blockRoomX && x <= blockRoomX + width && y >= thumbY && y <= thumbY + height){
            LaunchGame.mapSelect.drawBorder(blockRoomX, thumbY);
        }
        else if(x >= neonX && x <= neonX + width && y >= thumbY && y <= thumbY + height){
            LaunchGame.mapSelect.drawBorder(neonX, thumbY);
        }
    }

    public void GameMouseEvent(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            LaunchGame.gamePlay.currentChar.fireBullet(x, y);
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            LaunchGame.gamePlay.currentChar.specialSkill(x,y);
        }
    }

    //Unneeded Methods
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    //Unneeded Methods
}
