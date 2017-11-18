import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
  GameHandler handler;
  
  public KeyInput(GameHandler handler) {
    this.handler = handler;
  }
  
  public void keyPressed(KeyEvent event) {
    int key = event.getKeyCode();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      //player 1
      if (tmpObj.getID() == ObjectID.Player1) {
        if (key == KeyEvent.VK_W) handler.setUpPlayer1(true);
        if (key == KeyEvent.VK_S) handler.setDownPlayer1(true);
        if (key == KeyEvent.VK_A) handler.setLeftPlayer1(true);
        if (key == KeyEvent.VK_D) handler.setRightPlayer1(true);
        //if (key == KeyEvent.VK_SPACE) handler.setUp(true);
      }
      
      if (tmpObj.getID() == ObjectID.Player2) {
        if (key == KeyEvent.VK_I) handler.setUpPlayer2(true);
        if (key == KeyEvent.VK_K) handler.setDownPlayer2(true);
        if (key == KeyEvent.VK_J) handler.setLeftPlayer2(true);
        if (key == KeyEvent.VK_L) handler.setRightPlayer2(true);
        //if (key == KeyEvent.VK_RETURN) handler.setUp(true);
      }
    }
  }
  
  public void keyReleased(KeyEvent event) {
    int key = event.getKeyCode();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      //player 1
      if (tmpObj.getID() == ObjectID.Player1) {
        if (key == KeyEvent.VK_W) handler.setUpPlayer1(false);
        if (key == KeyEvent.VK_S) handler.setDownPlayer1(false);
        if (key == KeyEvent.VK_A) handler.setLeftPlayer1(false);
        if (key == KeyEvent.VK_D) handler.setRightPlayer1(false);
        //if (key == KeyEvent.VK_SPACE) handler.setUp(true);
      }
      
      if (tmpObj.getID() == ObjectID.Player2) {
        if (key == KeyEvent.VK_I) handler.setUpPlayer2(false);
        if (key == KeyEvent.VK_K) handler.setDownPlayer2(false);
        if (key == KeyEvent.VK_J) handler.setLeftPlayer2(false);
        if (key == KeyEvent.VK_L) handler.setRightPlayer2(false);
        //if (key == KeyEvent.VK_RETURN) handler.setUp(true);
      }
    }
  }
}
