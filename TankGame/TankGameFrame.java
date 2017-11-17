


package TankGame;

import javax.swing.JFrame;


/**
 *
 * @author Daniel
 */
public class TankGameFrame {
  
  
  
  public static void main(String args[]){
    JFrame f = new JFrame();
    f.add(new TankGame());
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(640,640);
    
    Thread t = new Thread();
    t.start();
  }
}
 
    

