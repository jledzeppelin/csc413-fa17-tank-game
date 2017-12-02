import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.*;
import java.math.*;


public class TankPlayer1 extends GameObject {
  
  GameHandler handler;
  private BufferedImage[] tank = null;
  int i;
  boolean positiveX;
  boolean positiveY;
  float incX;
  float incY;
  int tileSize;
  int angle;
  
  
  public TankPlayer1(int x, int y, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.handler = handler;
    i = 0;  
    
    ImageLoader loader = new ImageLoader();  
    BufferedImage tank1 = loader.loadImage("/Tank_red_basic_strip60.png");
    tileSize = tank1.getWidth() / 60;
    this.tank = new BufferedImage[ 60 ];
    
    for( int index = 0; index < this.tank.length; index++ ) {
      this.tank[ index ] = tank1.getSubimage(
          index * tileSize, 0, tileSize, tileSize );
    }
    angle = 360 / tank.length;
  }
  
 
  @Override
  public void tick() {
    //collision();
    x +=  velocityX;
    y -=  velocityY;
    
    if (collision()){
    // while(collision()){ 
      x += velocityX * -1.2;
      y -= velocityY * -1.2;
     //}
    }
   
    //player 1 movement
    if (handler.isUpPlayer1()) {
      
      velocityX = incX;
      velocityY = incY;
      //if (collision()){
     // velocityX += velocityX * -1.5;
     // velocityY += velocityY * -1.5;
    // }
    } else if (!handler.isDownPlayer1()) {
      velocityX = 0;
      velocityY = 0;
    }
    
    if (handler.isDownPlayer1()) {
      velocityX = -incX;
      velocityY = -incY;
    //  if (collision()){
    //  velocityX += velocityX * -1.5;
    //  velocityY += velocityY * -1.5;
     //}
    } else if (!handler.isUpPlayer1()) {
      velocityX = 0;
      velocityY = 0;
    }
    
    if (handler.isLeftPlayer1()) {
      
      i = (i +1) % tank.length;
      
      //incX = 3 * (float)Math.cos((i * angle));
      //incY = 3 * (float)Math.sin((i * angle));
    } else if (!handler.isRightPlayer1()) {
      //velocityX = 0;
    }
    
    if (handler.isRightPlayer1()) {
      
      
      if(i == 0){
       i = tank.length -1;
      } else {
        i = (i - 1) % tank.length;
      }
    } else if (!handler.isLeftPlayer1()) {
      //velocityX = 0;
    }
    float tmpAngle = (float)Math.toRadians((i * angle));
    incX = 3 * (float)Math.cos(tmpAngle);
    incY = 3 * (float)Math.sin(tmpAngle);
  }
  
  
  
  private boolean collision() {
    for ( int i = 0; i < handler.obj.size(); i++ ) {
      GameObject tmpObj = handler.obj.get(i);
        if(tmpObj.getID() == ObjectID.IndestructibleBlock){
          if(getBounds().intersects(tmpObj.getBounds())){
            //velocityX += velocityX * -1;
            //velocityY += velocityY * -1;
           // incX += incX * -1.1;
            //incY += incY * -1.1;
            return true;
          }
        }
    }
    return false;
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.drawImage(tank[i], x, y, null);

    //graphics.setColor(Color.blue);
    //graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public void renderMini(Graphics graphics, int x1, int y1) {
    //Graphics2d g2 = 
    graphics.drawImage(tank[i], x/8 + x1, y/8 + y1, 64 / 5, 64 / 5, null);
    //graphics.setColor(Color.blue);
    //graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 64, 64);
   // return new Rectangle(x, y, 32, 32);
  }
}