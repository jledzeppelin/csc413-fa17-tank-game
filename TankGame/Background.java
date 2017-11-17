package TankGame;

import java.util.*;

import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class Background {

  
  protected final String wall = 
    "Tank Graphics/Tank Graphics/wall.png";
  
  protected final String indestructibleWall = 
    "Tank Graphics/Tank Graphics/wall_indestructible.png";
  
  ArrayList<XYObject> wallsList;
  
  int width, length = 640 * 2;
  private BufferedImage wallImage;
  private BufferedImage indestructableWallImage;

  public Background() {
    try{
      wallImage = ImageIO.read( new File( wall ));
      indestructableWallImage = ImageIO.read( new File( wall ));
    }catch(IOException e){
      
    }
  }
  
  /*
   * get the x and y coordinates of each concrete walls and put them in 
   * the array list to draw them later in the Map class.
  */
  public void concreteWallsPlaceMent(int Width, int Length) {
    int val;
   
    for (val = 0; val < 40; val++){
      wallsList.add(new XYObject( val, 0, 1 )); 
      wallsList.add(new XYObject( val + 1, 39, 1 )); 
      wallsList.add(new XYObject( 0, val + 1, 1 )); 
      wallsList.add(new XYObject( 39, val, 1 )); 
    }
    
    // top row L
    for(val = 0; val < 4; val++){
    
      wallsList.add(new XYObject( 19, val, 1 )); 
      wallsList.add(new XYObject( 13, 39 - val, 1 )); 
    }  
    for(val = 0; val < 5; val++){
    
      wallsList.add(new XYObject( 15 + val, 5, 1 )); 
      wallsList.add(new XYObject( 14 + val, 39, 1 )); 
    }  
    for(val = 0; val < 15; val++){   
      wallsList.add(new XYObject(20 + val, 8,  1 )); 
    } 
    for(val = 0; val < 12; val++){   
      wallsList.add(new XYObject(4 + val, 31,  1 )); 
    } 
    for(val = 0; val < 4; val++){   
      wallsList.add(new XYObject(15, val - 26,  1 )); 
    } 
    for(val = 0; val < 5; val++){   
      wallsList.add(new XYObject(19, val+13,  1 )); 
    } 
    
    
  }
  
  public void wallsPlaceMent(int Width, int Length) {
     int val;
     for(val = 0; val < 13; val++){   
      wallsList.add(new XYObject(15, val + 18 ,  2 )); 
      
    } 
    for(val = 0; val < 12; val++){   
      wallsList.add(new XYObject(17, val + 18 ,  2 )); 
      
    } 
    for(val = 0; val < 15; val++){   
      
      wallsList.add(new XYObject(19, val + 9 ,  2 ));
    } 
    for(val = 0; val < 5; val++){   
      
      wallsList.add(new XYObject(16, val + 27 ,  2 ));
      wallsList.add(new XYObject(16, val + 18 ,  2 ));
      wallsList.add(new XYObject(18, val + 19 ,  2 ));
      wallsList.add(new XYObject(18, val + 8 ,  2 ));
    } 
    for(val = 0; val < 2; val++){   
      
      wallsList.add(new XYObject(17, val + 18 ,  2 ));
      wallsList.add(new XYObject(17, val + 22 ,  2 ));
      wallsList.add(new XYObject(18, val + 18 ,  2 )); 
    } 
     
     
     
    
    
  }

}

