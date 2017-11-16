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
  
  protected XYObject background;
  ArrayList<XYObject> wallsList;
  ArrayList<XYObject> indestructableWallsList;
  int width, length = 640;
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
    int xVal, yVal;
    indestructableWallsList = new ArrayList<XYObject>();
    yVal = 0;
    for(xVal = 0; xVal < Width; xVal += indestructableWallImage.getWidth()){
      indestructableWallsList.add(new XYObject(xVal, yVal));
    }
    
    for(yVal = 0 + indestructableWallImage.getHeight(); 
      yVal < length - indestructableWallImage.getHeight(); 
      xVal += indestructableWallImage.getHeight()){
      
      indestructableWallsList.add(new XYObject(0, yVal));

      indestructableWallsList.add(new XYObject(xVal, yVal));
      
    }
    for(xVal = 0; xVal < Width; xVal += indestructableWallImage.getWidth()){
      indestructableWallsList.add(new XYObject(xVal, yVal));
    }
  }

}
