import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class IndestructibleBlock extends GameObject {
  
  public IndestructibleBlock(int x, int y, ObjectID id) {
    super(x, y, id);
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.setColor(Color.black);
    graphics.fillRect(x, y, 32, 32);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}
