import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Anton on 3/2/2015.
 *
 */

public class GameNetwork extends Thread{

    Socket clientSocket;
    Scanner sc;

    boolean serverRunning = true;

    public GameNetwork(Socket clientSocket){
        this.clientSocket = clientSocket;
        System.out.println("Client has connected");

        try {
            sc = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(serverRunning) {
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

            if(LaunchGame.State == LaunchGame.STATE.GAME && !data.equalsIgnoreCase("waiting")) {
                String[] dataParts = data.split("\\|");

                int xBorder = LaunchGame.game.xBorder;
                int yBorder = LaunchGame.game.yBorder;
                int scale = LaunchGame.game.scaling;

                int xPos = ((xBorder + (Integer.parseInt(dataParts[0])))*scale);
                int yPos = ((yBorder + (Integer.parseInt(dataParts[1])))*scale);

                //LaunchGame.gamePlay.currentEnemy.update(xPos,yPos);
            }

            sendData();
        }
    }

    public void sendData() throws IOException{
        PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());

        //if(LaunchGame.State == LaunchGame.STATE.GAME) {
            StringBuilder dataToBeSent;

            int xBorder = LaunchGame.game.xBorder;
            int yBorder = LaunchGame.game.yBorder;
            int scale = LaunchGame.game.scaling;

            String xPos = String.valueOf(((LaunchGame.gamePlay.currentChar.xPos) - xBorder) / scale);
            String yPos = String.valueOf(((LaunchGame.gamePlay.currentChar.yPos) - yBorder) / scale);

            dataToBeSent = new StringBuilder(xPos).append("|").append(yPos);

            outputStream.println(dataToBeSent);
            outputStream.flush();
        //}
    }
}
