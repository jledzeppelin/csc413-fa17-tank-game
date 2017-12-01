import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DestructibleBlock extends GameObject {
  
  public DestructibleBlock(double x, double y, ObjectID id) {
    super(x, y, id);
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.setColor(Color.cyan);
    graphics.fillRect((int)x, (int)y, blockWidth, blockHeight);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, blockWidth, blockHeight);
  }
}