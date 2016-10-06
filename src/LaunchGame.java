import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import net.java.games.input.Controller;

/**
 * Created by Anton on 2/24/2015.
 *
 */

public class LaunchGame extends Canvas implements Runnable{

    static LaunchGame game;
    static StartMenu startMenu;
    static CharacterSelect characterSelect;
    static MapSelect mapSelect;
    static GamePlay gamePlay;

    static GameClient gameClient;
    static GameServer gameServer;

    private Thread gameThread;

    static enum STATE{MENU,CHARSEL, MAPSEL, GAME, WIN, CREDITS};
    static STATE State = STATE.MENU;

    int xResolution;
    int yResolution;
    int scaling;
    int xBorder;
    int yBorder;

    static boolean running = false;
    static boolean serverRunning = false;
    static boolean clientRunning = false;

    JInputJoystick controller;

    public LaunchGame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        xResolution = (int) screenWidth;
        yResolution = (int) screenHeight;
        setScaling();
        setBorders();

        controller = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);
        if(!controller.isControllerConnected()){
            System.out.println("Controller is not connected");
        }

        startMenu = new StartMenu(xBorder,yBorder,scaling);
        characterSelect = new CharacterSelect(xBorder,yBorder,scaling);
        mapSelect = new MapSelect(xBorder,yBorder,scaling);
        gamePlay = new GamePlay(xBorder,yBorder,scaling);

        addMouseMotionListener(new MouseMotionHandler());
        addMouseListener(new MoueEventHandler(xBorder,yBorder,scaling));
        addKeyListener(new KeyEventHandler());
    }

    //Set Scaling and Border
    private void setScaling(){
        if(xResolution >= 3840 && yResolution >= 2160){
            scaling = 3;
        }
        else if(xResolution >= 2560 && yResolution >= 1600){
            scaling = 2;
        }
        else{
            scaling = 1;
        }
    }

    private void setBorders(){
        xBorder = (xResolution - (1280*scaling)) / 2;
        yBorder = (yResolution - (720*scaling)) / 2;
    }
    //Set Scaling and Border

    //Game Loop
    private synchronized void startGame(){
        if(running){
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private synchronized void stopGame(){
        if(!running){
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long previousTime = System.nanoTime();
        final double limitFPS = 60.0;
        double divider = 1000000000 / limitFPS;
        double timePassed = 0;
        int frames = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long currentTime = System.nanoTime();
            timePassed += (currentTime - previousTime) / divider;
            previousTime = currentTime;

            if(timePassed >= 1){
                animate();
                timePassed--;
                updates++;
            }
            frames++;
            repaint();

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println(updates + "FPS, " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stopGame();
    }
    //Game Loop

    //Animating Graphics
    public void animate(){
        if(State == STATE.MENU){
            startMenu.animate();
        }
        else if(State == STATE.CHARSEL){
            characterSelect.animate();
        }
        else if(State == STATE.MAPSEL){
            mapSelect.animate();
        }
        else if(State == STATE.GAME){
            gamePlay.animate();
            if(controller.isControllerConnected()){
                ControllerEvent();
            }
        }
    }

    //Rendering Graphics
    public void paint(Graphics g){
        if(State == STATE.MENU) {
            startMenu.draw(g);
        }
        else if(State == STATE.CHARSEL){
            characterSelect.draw(g);
        }
        else if(State == STATE.MAPSEL){
            mapSelect.draw(g);
        }
        else if(State == STATE.GAME){
            gamePlay.draw(g);
        }
        else if(State == STATE.WIN){
            g.setColor(Color.BLACK);
            g.drawRect(0,0,xResolution,yResolution);
            g.setFont(new Font("Seravek",Font.PLAIN, 80));

            if(LaunchGame.gamePlay.playerLoss){
                g.drawString("You Lose", xResolution/2 - 10, yResolution/2 - 10);
            }
            else{
                g.drawString("You Win",xResolution/2 - 10, yResolution/2 - 10);
            }
        }
    }

    //Buffer Strategy
    public void update(Graphics g) {
        Graphics offgc;
        Image offscreen;
        Dimension d = size();


        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();

        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());

        paint(offgc);

        g.drawImage(offscreen, 0, 0, this);
    }

    //Networking
    public static void startServer(){
        gameServer = new GameServer();
        gameServer.start();
    }

    public static void startClient(){
        gameClient = new GameClient();
        gameClient.start();
    }
    //Networking

    public static void main(String args[]){
        JFrame gameFrame = new JFrame();

        if (isMacOSX()) {
            System.setProperty(
                    "com.apple.mrj.application.apple.menu.about.name",
                    "Full Screen Demo");
            enableFullScreenMode(gameFrame);
        } else{
            gameFrame.setUndecorated(true);
            //gameFrame.setSize(1280,720);
            gameFrame.setResizable(false);
        }

        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game = new LaunchGame();
        gameFrame.add(game);

        gameFrame.setVisible(true);

        game.startGame();
    }

    //check if OS X

    private static boolean isMacOSX() {
        return System.getProperty("os.name").contains("Mac OS X");
    }

    public static void enableFullScreenMode(Window window) {
        String className = "com.apple.eawt.FullScreenUtilities";
        String methodName = "setWindowCanFullScreen";

        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, Window.class, boolean.class);
            method.invoke(null, window, true);
        } catch (Throwable t) {
            System.err.println("Full screen mode is not supported");
            t.printStackTrace();
        }
    }

    public void ControllerEvent(){
        controller.pollController();

        int xValuePercentageLeftJoystick = controller.getX_LeftJoystick_Percentage();
        int yValuePercentageLeftJoystick = controller.getY_LeftJoystick_Percentage();

        int rightTrigger = controller.getZRotationPercentage();
        int leftTrigger = controller.getZAxisPercentage();

        if(xValuePercentageLeftJoystick > 60){
            LaunchGame.gamePlay.currentChar.movingRight = true;
            LaunchGame.gamePlay.currentChar.movingLeft = false;
        } else if(xValuePercentageLeftJoystick < 35){
            LaunchGame.gamePlay.currentChar.movingRight = false;
            LaunchGame.gamePlay.currentChar.movingLeft = true;
        } else{
            LaunchGame.gamePlay.currentChar.movingRight = false;
            LaunchGame.gamePlay.currentChar.movingLeft = false;
        }

        if(yValuePercentageLeftJoystick < 20){
            LaunchGame.gamePlay.currentChar.jumping = true;
        }

        if(rightTrigger > 60){
            LaunchGame.gamePlay.currentChar.fireBullet(0, 0);
        }

        if(leftTrigger > 60){
            LaunchGame.gamePlay.currentChar.specialSkill(0, 0);
        }

        //for(int i = 0; i < controller.getButtonsValues().size(); i++) {
        //    boolean joystickButton = controller.getButtonValue(i);
        //    if (joystickButton) {
        //       System.out.println(i);
        //   }
        //}
    }
}
