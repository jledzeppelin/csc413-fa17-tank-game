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
  
  public TankPlayer1(int x, int y, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.handler = handler;
    i = 0;
    positiveX = false;
    positiveY = true;
    
    incY = 0;
    incX = 15;
    ImageLoader loader = new ImageLoader();  
    BufferedImage tank1 = loader.loadImage("/Tank_red_basic_strip60.png");
    tileSize = tank1.getWidth() / 60;
    this.tank = new BufferedImage[ 60 ];
    
    for( int index = 0; index < this.tank.length; index++ ) {
      this.tank[ index ] = tank1.getSubimage(
          index * tileSize, 0, tileSize, tileSize );
    }
    
  }
  
  @Override
  public void tick() {
    x += velocityX/3;
    y += velocityY/3;
    
    float div = 5 /(tank.length / 4);
   // boolean positiveX;
   // boolean positiveY;
    
    
    
    //player 1 movement
    if (handler.isUpPlayer1()) {
      velocityX = incX;
      velocityY = incY;
    } else if (!handler.isDownPlayer1()) {
      velocityX = 0;
      velocityY = 0;
    }
    
    if (handler.isDownPlayer1()) {
      velocityX = -incX;
      velocityY = -incY;
    } else if (!handler.isUpPlayer1()) {
      velocityX = 0;
      velocityY = 0;
    }
    
    if (handler.isLeftPlayer1()) {
      //velocityX = -3;
      //i = (i+5) % tank.length;
      if(i < tank.length / 4){
        incX -= 1;
        incY -= 1;
      } else if(i >= tank.length /4 && i < tank.length  / 2){
        incX -= 1;
        incY += 1;
      } else if (i >= tank.length / 2 && i < tank.length * 3/ 4){
        incX += 1;
        incY += 1;
      } else if(i >= tank.length * 3 / 4 && i < tank.length ){
        incX += 1;
        incY -= 1;
      }
      //i = (i+5) % tank.length;
      i = (i +1) % tank.length;
    } else if (!handler.isRightPlayer1()) {
      //velocityX = 0;
    }
    
    if (handler.isRightPlayer1()) {
      if(i <= tank.length / 4){
        incX += 1;
        incY += 1;
      } else if(i > tank.length /4 && i <= tank.length  / 2){
        incX += 1;
        incY -= 1;
      } else if (i > tank.length / 2 && i <= tank.length * 3/ 4){
        incX -= 1;
        incY -= 1;
      } else if(i > tank.length * 3 / 4 && i <= tank.length){
        incX -= 1;
        incY += 1;
      }
      if(i == 0){
       i = tank.length -1;
      } else {
        i = (i - 1) % tank.length;
       
      }
    } else if (!handler.isLeftPlayer1()) {
      //velocityX = 0;
    }
  }
  
  /*
  public void tick() {
    x += velocityX;
    y += velocityY;
    
    //player 1 movement
    if (handler.isUpPlayer1()) {
      velocityY = -3;
    } else if (!handler.isDownPlayer1()) {
      velocityY = 0;
    }
    
    if (handler.isDownPlayer1()) {
      velocityY = 3;
    } else if (!handler.isUpPlayer1()) {
      velocityY = 0;
    }
    
    if (handler.isLeftPlayer1()) {
      velocityX = -3;
    } else if (!handler.isRightPlayer1()) {
      velocityX = 0;
    }
    
    if (handler.isRightPlayer1()) {
      velocityX = 3;
    } else if (!handler.isLeftPlayer1()) {
      velocityX = 0;
    }
  }
  */
  @Override
  public void render(Graphics graphics) {
    graphics.drawImage(tank[i], x, y, null);

    //graphics.setColor(Color.blue);
    //graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public Rectangle getBounds() {
    return null;//new Rectangle(x, y, 32, 32);
  }
}