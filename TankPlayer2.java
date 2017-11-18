import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class TankPlayer2 extends GameObject {
  
  GameHandler handler;
  
  public TankPlayer2(int x, int y, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.handler = handler;
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
    graphics.setColor(Color.green);
    graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}