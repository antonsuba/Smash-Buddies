import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Anton on 3/4/2015.
 *
 */
public class KeyEventHandler implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {

        if(LaunchGame.State == LaunchGame.STATE.GAME) {
            int Key = e.getKeyCode();

            if(Key == KeyEvent.VK_A){
                LaunchGame.gamePlay.currentChar.facingLeft = true;
                LaunchGame.gamePlay.currentChar.facingRight = false;
                LaunchGame.gamePlay.currentChar.movingLeft = true;
                LaunchGame.gamePlay.currentChar.movingRight = false;

                LaunchGame.gamePlay.currentChar.hasMoved = true;
            }
            else if(Key == KeyEvent.VK_D){
                LaunchGame.gamePlay.currentChar.facingLeft = false;
                LaunchGame.gamePlay.currentChar.facingRight = true;
                LaunchGame.gamePlay.currentChar.movingLeft = false;
                LaunchGame.gamePlay.currentChar.movingRight = true;

                LaunchGame.gamePlay.currentChar.hasMoved = true;
            }
            else if (Key == KeyEvent.VK_W || Key == KeyEvent.VK_SPACE) {
                LaunchGame.gamePlay.currentChar.jumping = true;

                LaunchGame.gamePlay.currentChar.hasMoved = true;

                LaunchGame.gamePlay.currentChar.soundClip("Jump");
            }
            else if(Key == KeyEvent.VK_SHIFT){
                //LaunchGame.gamePlay.currentChar.fireBullet();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(LaunchGame.State == LaunchGame.STATE.GAME) {
            int Key = e.getKeyCode();

            if(Key == KeyEvent.VK_A){
                LaunchGame.gamePlay.currentChar.movingLeft = false;

                LaunchGame.gamePlay.currentChar.hasMoved = false;
            }
            else if (Key == KeyEvent.VK_D) {
                LaunchGame.gamePlay.currentChar.movingRight = false;

                LaunchGame.gamePlay.currentChar.hasMoved = false;
            }
        }
    }

    //Unneeded Methods
    @Override
    public void keyTyped(KeyEvent e) {}
    //Unneeded Methods
}
