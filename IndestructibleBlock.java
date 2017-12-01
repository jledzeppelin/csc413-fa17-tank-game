import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class IndestructibleBlock extends GameObject {
  
  public IndestructibleBlock(double x, double y, ObjectID id) {
    super(x, y, id);
  }
  
  @Override
  public void tick() {
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.setColor(Color.black);
    graphics.fillRect((int)x, (int)y, blockWidth, blockHeight);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, blockWidth, blockHeight);
  }
}
