import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class DestructibleBlock extends GameObject {
  private Shape blockShape = null;
  BufferedImage block;
  
  public DestructibleBlock(double x, double y, ObjectID id) {
    super(x, y, id);
    angle = Math.toRadians(0);
    width = 32;
    height = 32;
    health = 100;
    lives = 1;
    blockShape = new Rectangle2D.Double(x, y, width, height);
    
    ImageLoader loader = new ImageLoader();
    block = loader.loadImage("res/wall.png");
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render(Graphics graphics) {
    graphics.drawImage(block, (int)x, (int)y, width, height, null);
  }
  
  @Override
  public void renderMini(Graphics graphics, int x, int y) {
    graphics.drawImage(block, (int)this.x / 8 + x, (int)this.y / 8 + y, width / 8, height / 8, null);
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