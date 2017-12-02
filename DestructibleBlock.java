import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class DestructibleBlock extends GameObject {
  private Shape blockShape = null;
  private Area blockArea = null;
  
  public DestructibleBlock(double x, double y, ObjectID id) {
    super(x, y, id);
    blockShape = new Rectangle2D.Double(x, y, blockWidth, blockHeight);
    blockArea = new Area(blockShape);
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
  public Area getArea() {
    return blockArea;
  }
}