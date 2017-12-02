import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class IndestructibleBlock extends GameObject {
  private Shape blockShape = null;
  
  public IndestructibleBlock(double x, double y, ObjectID id) {
    super(x, y, id);
    angle = Math.toRadians(0);
    width = 32;
    height = 32;
    blockShape = new Rectangle2D.Double(x, y, width, height);
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
  
  @Override
  public Shape getShape() {
    return blockShape;
  }
}
