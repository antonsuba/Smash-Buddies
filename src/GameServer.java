import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Anton on 3/7/2015.
 *
 */
public class GameServer extends Thread{

    ServerSocket serverSocket;
    Socket socket;
    final int port = 8000;

    Scanner sc;
    PrintStream output;

    boolean serverRunning = true;

    boolean rosterSent = false;
    boolean rosterReceived = false;
    ArrayList<String> roster;

    public void startServer() {
        System.out.println("Server started");

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("Client has connected");
            sc = new Scanner(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            output = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        startServer();

        while (serverRunning) {
            try {
                readData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void readData() throws IOException{
        String data;

        while(sc.hasNext()){
            data = sc.nextLine();

            if(data.equalsIgnoreCase("waiting")){
                //Wait Screen
            }
            else if(LaunchGame.State == LaunchGame.STATE.GAME && !data.equalsIgnoreCase("waiting")) {
                System.out.println(data);

                try {
                    if(data.substring(0,2).equalsIgnoreCase("00")){
                        System.out.println(data);

                        String[] dataParts = data.split("\\|");

                        String enemyName = dataParts[1];
                        String enemyChar1 = dataParts[2];
                        String enemyChar2 = dataParts[3];
                        String enemyChar3 = dataParts[4];

                        rosterReceived = true;

                        LaunchGame.gamePlay.enemyName = enemyName;
                        LaunchGame.gamePlay.addEnemyCharacters(enemyChar1,enemyChar2,enemyChar3);
                    }
                    else{
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
                        //int numLives = Integer.parseInt(dataParts[7]);

                        LaunchGame.gamePlay.currentEnemy.update(xPos, yPos);

                        if(enemyLoss){
                            LaunchGame.State = LaunchGame.STATE.WIN;
                        }

                        if (bulletFired) {
                            LaunchGame.gamePlay.currentEnemy.fireBullet(xTarget, yTarget);
                            System.out.println("enemy fired");
                            LaunchGame.gamePlay.currentEnemy.bulletFired = false;
                        }
                        else if(specialUsed){
                            LaunchGame.gamePlay.currentEnemy.specialSkill(xTarget,yTarget);
                            LaunchGame.gamePlay.currentEnemy.specialUsed = false;
                        }

                        if(!isAlive){
                            LaunchGame.gamePlay.currentEnemy.isAlive = false;
                        }
                    }
                }catch(Exception e){
                    //Do nothing
                }
            }

            sendData();
        }
    }

    public void sendData(){
        String dataToBeSent;

        if(LaunchGame.State == LaunchGame.STATE.GAME) {

            try {
                if(!rosterSent && rosterReceived){
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
                else {
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
                    //String numLives = String.valueOf(LaunchGame.gamePlay.currentChar.livesLeft);

                    dataToBeSent = xPos + "|" + yPos + "|" + bulletFired + "|" + xTarget + "|"
                            + yTarget + "|" + specialUsed + "|" + isAlive + "|" + playerLoss;

                    output.println(dataToBeSent);
                    output.flush();

                    if (LaunchGame.gamePlay.currentChar.bulletFired) {
                        LaunchGame.gamePlay.currentChar.bulletFired = false;
                    }
                }
            }catch (Exception e){
                //Do nothing
            }
        }
        else{
            try {
                output.println("waiting");
                output.flush();
            }catch (Exception e){
                //Do nothing
            }
        }
    }


}