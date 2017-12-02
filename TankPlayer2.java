import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TankPlayer2 extends GameObject {
  
  GameHandler handler;
  private BufferedImage[] tank = null;
  int i;
  boolean positiveX;
  boolean positiveY;
  float incX;
  float incY;
  int tileSize;
  int angle;
  
  
  public TankPlayer2(int x, int y, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.handler = handler;
    
    ImageLoader loader = new ImageLoader();  
    BufferedImage tank1 = loader.loadImage("/Tank_blue_basic_strip60.png");
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
    x += velocityX;
    y += velocityY;
    
    //player 2 movement
    if (handler.isUpPlayer2()) {
      velocityY = -3;
    } else if (!handler.isDownPlayer2()) {
      velocityY = 0;
    }
    
    if (handler.isDownPlayer2()) {
      velocityY = 3;
    } else if (!handler.isUpPlayer2()) {
      velocityY = 0;
    }
    
    if (handler.isLeftPlayer2()) {
      velocityX = -3;
    } else if (!handler.isRightPlayer2()) {
      velocityX = 0;
    }
    
    if (handler.isRightPlayer2()) {
      velocityX = 3;
    } else if (!handler.isLeftPlayer2()) {
      velocityX = 0;
    }
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.drawImage(tank[i], x, y, null);
  }
  
  @Override
  public void renderMini(Graphics graphics, int x1, int y1) {
    graphics.drawImage(tank[i], x/8 + x1, y/8 + y1, 64 / 5, 64 / 5, null);
  }
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}