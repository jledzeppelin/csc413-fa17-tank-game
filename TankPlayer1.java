import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class TankPlayer1 extends GameObject {
  
  GameHandler handler;
  
  public TankPlayer1(int x, int y, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.handler = handler;
  }
  
  @Override
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
  
  @Override
  public void render(Graphics graphics) {
    graphics.setColor(Color.blue);
    graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}