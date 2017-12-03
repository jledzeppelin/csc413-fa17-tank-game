import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
  private int bulletSpeed;
  private BufferedImage bullet;
  private int tileSize;
  private double xSpeed;
  private double ySpeed;
  GameHandler handler;
  ObjectID enemy = null;
  
  public Bullet(double x, double y, double a, int speed, ObjectID id, ObjectID origin, GameHandler handler) {
    super(x, y, id);
    angle = a;
    width = 17;
    height = 17;
    bulletSpeed = speed;
    this.handler = handler;
    
    xSpeed = Math.cos(angle) * bulletSpeed;
    ySpeed = Math.sin(angle) * bulletSpeed;
    
    ImageLoader loader = new ImageLoader();
    BufferedImage bulletStrip = loader.loadImage("/Shell_heavy_strip60.png");
    tileSize = bulletStrip.getWidth() / 60;
    bullet = bulletStrip.getSubimage(30 * tileSize, 0, tileSize, tileSize);
    
    if (origin == ObjectID.Player1) {
      enemy = ObjectID.Player2;
    } else {
      enemy = ObjectID.Player1;
    }
  }
  
  @Override
  public void tick() {
    x += xSpeed;
    y += ySpeed;
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      // TO DO change collision with enemy to be done by player classes
      if (tmpObj.getID() == ObjectID.IndestructibleBlock || tmpObj.getID() == ObjectID.DestructibleBlock ||
              tmpObj.getID() == enemy) {
        if (getBounds().intersects(tmpObj.getBounds())) {
          handler.removeObject(this);
          
          if (tmpObj.getID() == ObjectID.DestructibleBlock) {
            handler.removeObject(tmpObj);
          }
          if (tmpObj.getID() == enemy) {
            tmpObj.setHealth(tmpObj.getHealth() - 20);
          }
        }
      }
      
      
    }
    
  }
  
  @Override
  public void render(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;
    
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    AffineTransform old = graphics2D.getTransform();
    
    graphics2D.rotate(angle, x + width / 2, y + height / 2);
    graphics2D.drawImage(bullet, (int)x, (int)y, width, height, null);
    //bullet is a little off
    
    graphics2D.setTransform(old);
  }
  
  @Override
  public void renderMini(Graphics graphics, int x, int y) {
    
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, width, height);
  }
  
  @Override
  public Shape getShape() {
    return new Rectangle2D.Double(x, y, width, height);
  }
  
  public int getSpeed() {
    return bulletSpeed;
  }
  public void setSpeed(int speed) {
    bulletSpeed = speed;
  }
}