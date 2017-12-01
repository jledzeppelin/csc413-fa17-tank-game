import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DestructibleBlock extends GameObject {
  BufferedImage block;
  
  public DestructibleBlock(int x, int y, ObjectID id) {
    super(x, y, id);
     ImageLoader loader = new ImageLoader();  
     block = loader.loadImage("/wall.png");
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render(Graphics graphics) {
    //graphics.setColor(Color.cyan);
    //graphics.fillRect(x, y, 32, 32);
    graphics.drawImage(block, x, y, null);

  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}