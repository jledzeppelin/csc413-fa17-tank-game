
package TankGame;

import java.util.*;

import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class Map {
  XYObject tankPosition1;
  XYObject tankPosition2;
  Sprite tank1;
  protected final String tank = 
    "Tank Graphics/Tank Graphics/Tank_blue_base_strip60.png";
  protected final String bullet = 
    "Tank Graphics/Tank Graphics/Shell_basic_strip60.png";
  protected final String wall = 
    "Tank Graphics/Tank Graphics/wall.png";
  
  protected final String indestructibleWall = 
    "Tank Graphics/Tank Graphics/wall_indestructible.png";
  private BufferedImage wallImage;
  private BufferedImage indestructableWallImage;
  Background background;
  //private BufferedImage[] tank1;
  //private BufferedImage[] tank2;

  Map(){
    
    
    background = new Background();
    background.concreteWallsPlaceMent();
    background.wallsPlaceMent();
    tankPosition1 = new XYObject(300,600, 3);
    tankPosition2 = new XYObject(1000, 100, 3);
    try{
      wallImage = ImageIO.read( new File( wall ));
      indestructableWallImage = ImageIO.read( new File( indestructibleWall ));
      tank1 = new Sprite(tank, 32);
    }catch(IOException e){
      System.out.println("error on Map constructor");
    }
  }
  
  void player1Map(Graphics graphics){
    int maxX = 320;
    
    int tankLeft = tankPosition1.getX() - 160;
    int tankUp = tankPosition1.getY() - 330;
    
    for (XYObject ob : background.getArray()) { 
       
      /*
        if(ob.getOb() == 1){
             System.out.println(ob.getX()* wallImage.getWidth());
             System.out.println((ob.getY() * wallImage.getHeight()));
          if(ob.getX()* wallImage.getWidth() >= tankLeft && 
            ob.getX()* wallImage.getWidth() < tankLeft + 640 &&
            ob.getY()* wallImage.getHeight() >= tankUp &&
            ob.getY()* wallImage.getHeight() < tankUp + 650){
            // System.out.println(ob.getX()* wallImage.getWidth());
            // System.out.println((ob.getY() * wallImage.getHeight()));
            // System.out.println((ob.getX() * wallImage.getWidth()) - tankLeft);
            // System.out.println((ob.getY() * wallImage.getHeight()) - tankUp);
              graphics.drawImage(indestructableWallImage, (ob.getX() * wallImage.getWidth()) - tankLeft,
                (ob.getY() * wallImage.getHeight()) - tankUp, null);
              
          }
        }  	
*/
      
      
      if(ob.getX() * wallImage.getWidth() >= 0 && ob.getX() * wallImage.getWidth() < 640 * 2 &&
         ob.getY() * wallImage.getHeight() >= 0 && ob.getY() * wallImage.getHeight() < 640 * 2){
           graphics.drawImage(indestructableWallImage, ob.getX() * wallImage.getWidth() , 
           ob.getY() * wallImage.getHeight(), null);
      }
      
      }
    
  }
  
  
  //notes add sprites and x and y coordinate for each
  // add maps
  // add key listeners which causes stuf ti happen
  // add 
  
  
}
