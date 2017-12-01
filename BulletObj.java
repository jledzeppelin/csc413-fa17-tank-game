
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class BulletObj extends GameObject{
  GameHandler handler;
  private BufferedImage[] bullet = null;
  int tileSize;
  int angle;
 
  
  public BulletObj(int x, int y, ObjectID id, GameHandler handler){
    super(x, y, id);
    this.handler = handler;
    
    ImageLoader loader = new ImageLoader();  
    BufferedImage bullet1 = loader.loadImage("/Shell_basic_strip60.png");
    
    tileSize = bullet1.getWidth() / 60;
    this.bullet = new BufferedImage[ 60 ];
    
    for( int index = 0; index < this.bullet.length; index++ ) {
      this.bullet[ index ] = bullet1.getSubimage(
          index * tileSize, 0, tileSize, tileSize );
    }
    angle = 360 / bullet.length;
  }
  
  @Override
  public void tick() {
    
    
  }
  
  private void collision() {
    
  }
  
  @Override
  public void render(Graphics graphics) {
    
  }
  
  @Override
  public Rectangle getBounds() {
    return null;//new Rectangle(x, y, tank[i].getWidth(), tank[i].getHeight());
  }
  
}
