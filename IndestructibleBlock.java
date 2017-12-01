import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class IndestructibleBlock extends GameObject {
  BufferedImage iBlock;
  
  public IndestructibleBlock(int x, int y, ObjectID id) {
    super(x, y, id);
    ImageLoader loader = new ImageLoader();  
    iBlock = loader.loadImage("/wall_Indestructible.png");
    
  }
  
  @Override
  public void tick() {
  
  }
  
  @Override
  public void render(Graphics graphics) {
    //graphics.setColor(Color.black);
    //graphics.fillRect(x, y, 32, 32);
    graphics.drawImage(iBlock, x, y, null);
  }
  
  @Override
  public Rectangle getBounds() {
    
    return new Rectangle(x, y, 32, 32);
  }
}
