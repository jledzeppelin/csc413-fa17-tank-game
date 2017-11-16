/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Daniel
 */
public class TankGame extends JPanel implements Runnable{
  
  protected int width = 1280;
  protected int length = 1280;
  public TankGame(){
    
    
    
    
  }
  
  // The thread is for the bullets being shot.
  @Override
  public void run() {
    
    
    
    try {
        Thread.sleep( 10 );
      } catch ( InterruptedException interrupted ) {
          System.out.println("Code Interruputer at trying Thread.sleep");
      }
  }
    
    
}
