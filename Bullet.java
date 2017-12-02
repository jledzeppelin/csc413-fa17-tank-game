import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Bullet extends GameObject {
  private int bulletSpeed;
  GameHandler handler;
  
  public Bullet(double x, double y, double a, int speed, ObjectID id, GameHandler handler) {
    super(x, y, id);
    angle = a;
    width = 5;
    height = 5;
    bulletSpeed = speed;
    this.handler = handler;
  }
  
  @Override
  public void tick() {
    x += Math.cos(angle) * bulletSpeed;
    y += Math.sin(angle) * bulletSpeed;
  }
  
  @Override
  public void render(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;
    
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    AffineTransform old = graphics2D.getTransform();
    
    graphics2D.setColor(Color.blue);
    graphics2D.fillRect((int)x, (int)y, width, height);
    
    graphics2D.setTransform(old);
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