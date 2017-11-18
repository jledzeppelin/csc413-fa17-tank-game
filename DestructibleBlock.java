import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DestructibleBlock extends GameObject {
  
  public DestructibleBlock(int x, int y, ObjectID id) {
    super(x, y, id);
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.setColor(Color.cyan);
    graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}