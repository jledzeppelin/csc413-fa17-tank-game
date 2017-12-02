
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

public class Bullet extends GameObject {
  public final int bulletWidth = 5;
  public final int bulletHeight = 5;
  private double angle;
  private int bulletSpeed;
  GameHandler handler;
  
  public Bullet(double x, double y, double angle, int speed, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.angle = angle;
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
    graphics2D.fillRect((int)x, (int)y, bulletWidth, bulletHeight);
    
    graphics2D.setTransform(old);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, bulletWidth, bulletHeight);
  }
  
  public double getAngle() {
    return angle;
  }
  public void setAngle(double angle) {
    this.angle = angle;
  }
  public int getSpeed() {
    return bulletSpeed;
  }
  public void setSpeed(int speed) {
    bulletSpeed = speed;
  }
}