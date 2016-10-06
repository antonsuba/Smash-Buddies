import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Anton on 3/7/2015.
 *
 */
public class GameClient extends Thread{

    Socket socket;
    PrintStream output;
    Scanner sc;

    String ipAddress;
    String data;

    final int port = 8000;

    boolean clientRunning = true;

    boolean rosterSent = false;
    boolean rosterReceived = false;
    boolean mapLoaded = false;
    ArrayList<String> roster;

    int deathAnimationCounter = 0;

    public void startClient(){
        ipAddress = JOptionPane.showInputDialog("Input server IP Address: ");

        try {
            socket = new Socket(ipAddress,port);
            output = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            sc = new Scanner(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        startClient();

        while (clientRunning){

            sendData();

            try{
                data = sc.nextLine();
            }catch (Exception e){
                System.out.println("Connection lost");
                clientRunning = false;
                break;
            }

            readData();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void readData(){

        if(data.equalsIgnoreCase("waiting")){
            //Wait Screen
        }
        else if(LaunchGame.State == LaunchGame.STATE.WAIT && !data.equalsIgnoreCase("waiting")){
            System.out.println(data);

            if(data.substring(0,2).equalsIgnoreCase("00")){
                System.out.println(data);

                String[] dataParts = data.split("\\|");

                String enemyName = dataParts[1];
                String enemyChar1 = dataParts[2];
                String enemyChar2 = dataParts[3];
                String enemyChar3 = dataParts[4];
                String mapReceived = dataParts[5];

                rosterReceived = true;

                LaunchGame.gamePlay.addMap(mapReceived);

                LaunchGame.gamePlay.enemyName = enemyName;
                LaunchGame.gamePlay.addEnemyCharacters(enemyChar1,enemyChar2,enemyChar3);

                //mapLoaded = true;
            }
        }

        else if(LaunchGame.State == LaunchGame.STATE.GAME && !data.equalsIgnoreCase("waiting")) {
            System.out.println(data);

            try {
                String[] dataParts = data.split("\\|");

                int xBorder = LaunchGame.game.xBorder;
                int yBorder = LaunchGame.game.yBorder;
                int scale = LaunchGame.game.scaling;

                int xPos = ((xBorder + (Integer.parseInt(dataParts[0]))) * scale);
                int yPos = ((yBorder + (Integer.parseInt(dataParts[1]))) * scale);
                boolean bulletFired = Boolean.valueOf(dataParts[2]);
                int xTarget = ((xBorder + (Integer.parseInt(dataParts[3]))) * scale);
                int yTarget = ((yBorder + (Integer.parseInt(dataParts[4]))) * scale);
                boolean specialUsed = Boolean.valueOf(dataParts[5]);
                boolean isAlive = Boolean.valueOf(dataParts[6]);
                boolean enemyLoss = Boolean.valueOf(dataParts[7]);
                boolean facingLeft = Boolean.valueOf(dataParts[8]);
                boolean facingRight = Boolean.valueOf(dataParts[9]);
                int enemyLives = Integer.parseInt(dataParts[10]);
                boolean paralyze = Boolean.valueOf(dataParts[11]);

                LaunchGame.gamePlay.currentEnemy.update(xPos, yPos, facingLeft, facingRight);

                LaunchGame.gamePlay.enemyLives = enemyLives;

                if(enemyLoss){
                    LaunchGame.gamePlay.enemyLoss = true;
                }

                if(!isAlive){
                    LaunchGame.gamePlay.currentEnemy.isAlive = false;
                }

                LaunchGame.gamePlay.currentChar.paralysis = paralyze;

                if (bulletFired) {
                    LaunchGame.gamePlay.currentEnemy.fireBullet(xTarget, yTarget);
                    System.out.println("enemy fired");
                    LaunchGame.gamePlay.currentEnemy.bulletFired = false;
                }
                else if(specialUsed){
                    LaunchGame.gamePlay.currentEnemy.specialSkill(xTarget,yTarget);
                    LaunchGame.gamePlay.currentEnemy.specialUsed = false;
                }
            }catch (Exception e){
                //Do nothing
            }
        }
    }

    public void sendData() {
        String dataToBeSent;

        if(LaunchGame.State == LaunchGame.STATE.WAIT){
            if(!rosterSent){
                roster = LaunchGame.characterSelect.characterSelected;
                String name = LaunchGame.gamePlay.userName;
                String char1 = roster.get(0);
                String char2 = roster.get(1);
                String char3 = roster.get(2);

                dataToBeSent = "00" + "|" + name + "|" + char1 + "|" + char2 + "|" + char3;

                output.println(dataToBeSent);
                output.flush();

                rosterSent = true;
            }
        }

        else if (LaunchGame.State == LaunchGame.STATE.GAME) {

            try {
                //else{
                    int xBorder = LaunchGame.game.xBorder;
                    int yBorder = LaunchGame.game.yBorder;
                    int scale = LaunchGame.game.scaling;

                    String xPos = String.valueOf(((LaunchGame.gamePlay.currentChar.xPos) - xBorder) / scale);
                    String yPos = String.valueOf(((LaunchGame.gamePlay.currentChar.yPos) - yBorder) / scale);
                    String bulletFired = Boolean.toString(LaunchGame.gamePlay.currentChar.bulletFired);
                    String xTarget = String.valueOf(((LaunchGame.gamePlay.currentChar.xTarget) - yBorder) / scale);
                    String yTarget = String.valueOf(((LaunchGame.gamePlay.currentChar.yTarget) - yBorder) / scale);
                    String specialUsed = Boolean.toString(LaunchGame.gamePlay.currentChar.specialUsed);
                    String isAlive = Boolean.toString(LaunchGame.gamePlay.currentChar.isAlive);
                    String playerLoss = Boolean.toString(LaunchGame.gamePlay.playerLoss);
                    String facingLeft = Boolean.toString(LaunchGame.gamePlay.currentChar.facingLeft);
                    String facingRight = Boolean.toString(LaunchGame.gamePlay.currentChar.facingRight);
                    String numLives = String.valueOf(LaunchGame.gamePlay.currentChar.livesLeft);
                    String paralyze = String.valueOf(LaunchGame.gamePlay.currentChar.enemyParalyze);

                    if(isAlive.equalsIgnoreCase("false")){
                        deathAnimationCounter++;
                        if(deathAnimationCounter > 20){
                            isAlive = "true";
                        }
                    }
                    else if(isAlive.equalsIgnoreCase("true")){
                        deathAnimationCounter = 0;
                    }

                    dataToBeSent = xPos + "|" + yPos + "|" + bulletFired + "|" + xTarget + "|" + yTarget + "|" + specialUsed
                            + "|" + isAlive + "|" + playerLoss + "|" + facingLeft + "|" + facingRight + "|" + numLives
                            + "|" + paralyze;

                    output.println(dataToBeSent);
                    output.flush();

                    if (LaunchGame.gamePlay.currentChar.bulletFired) {
                        LaunchGame.gamePlay.currentChar.bulletFired = false;
                    }

                    if (LaunchGame.gamePlay.currentChar.specialUsed) {
                        LaunchGame.gamePlay.currentChar.specialUsed = false;
                    }
                //}
            }catch(Exception e){
                //Do nothing
            }
        }
        else {
            try {
                output.println("waiting");
                output.flush();
            }catch (Exception e){
                //Do nothing
            }
        }
    }

}
