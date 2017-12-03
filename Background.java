import java.awt.Graphics2D;
import java.awt.image.*;

public class Background {
  BufferedImage background;
  BufferedImage backgroundTile;
  Graphics2D g2;
  int x1,y1;
  
  Background(){  
    ImageLoader loader = new ImageLoader();
    backgroundTile = loader.loadImage("res/background_tile.png");
    background = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
    g2 = (Graphics2D)background.getGraphics();
    addBackground();
  }
  
  public void addBackground(){
    for(int x = 0; x < 2000; x += 300){
      for(int y = 0; y < 1000; y += 240){
        g2.drawImage(backgroundTile, x, y, null);
      }
    }
  }
 

  public BufferedImage getBackground(){
    return background;
  }
}