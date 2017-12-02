import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TankPlayer2 extends GameObject {
  private int tmpAngle;
  private BufferedImage tank;
  private int tileSize;
  private double xSpeed;
  private double ySpeed; 
  private int reloadTime;
  GameHandler handler;
  
  public TankPlayer2(double x, double y, int a, ObjectID id, GameHandler handler) {
    super(x, y, id);
    angle = Math.toRadians(a);
    width = 64;
    height = 64;
    tmpAngle = a;
    health = 100;
    lives = 2;
    
    ImageLoader loader = new ImageLoader();
    BufferedImage tankStrip = loader.loadImage("/Tank_red_heavy_strip60.png");
    tileSize = tankStrip.getWidth() / 60;
    tank = tankStrip.getSubimage(0 * tileSize, 0, tileSize, tileSize);

    reloadTime = 0;
    this.handler = handler;
  }
  
  @Override
  public void tick() {
    angle = Math.toRadians(tmpAngle);
    
    x += xSpeed;
    y += ySpeed;
    collision();
    
    if (handler.isForwardPlayer2()) {
      xSpeed = Math.cos(angle);
      ySpeed = Math.sin(angle);
    } else if (!handler.isBackwardPlayer2()) {
      xSpeed = 0;
      ySpeed = 0;
    }
    
    if (handler.isBackwardPlayer2()) {
      xSpeed = -(Math.cos(angle));
      ySpeed = -(Math.sin(angle));
    } else if (!handler.isForwardPlayer2()) {
      xSpeed = 0;
      ySpeed = 0;
    }
    
    if (handler.isRightPlayer2()) {
      tmpAngle += 2;
    } else if (!handler.isLeftPlayer2()) {
      tmpAngle += 0;
    }
    
    if (handler.isLeftPlayer2()) {
      tmpAngle -= 2;
    } else if (!handler.isRightPlayer2()) {
      tmpAngle -= 0;
    }
    
    if (handler.isShootPlayer2()) {
      if (reloadTime == 0) {
        handler.addObject(new Bullet(x + width / 2, y + height / 2, angle, 8, ObjectID.Bullet, ObjectID.Player2, handler));
        reloadTime = 100;
      } else {
        reloadTime -= 1;
      }
    } else if (!handler.isShootPlayer2()) {
      if (reloadTime > 0) {
        reloadTime -= 1;
      }
    }
    
    if (tmpAngle > 360) {
      tmpAngle = 0;
    } else if (tmpAngle < 0) {
      tmpAngle = 360;
    }
  }
  
  @Override
  public void render(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;
    
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    AffineTransform old = graphics2D.getTransform();
    
    graphics2D.rotate(angle, x + width / 2, y + height / 2);
    graphics2D.drawImage(tank, (int)x, (int)y, width, height, null);
    
    graphics2D.setTransform(old);
  }
  
  @Override
  public void renderMini(Graphics graphics, int x, int y) {
    graphics.drawImage(tank, (int)this.x / 8 + x, (int)this.y / 8 + y, width / 5, height / 5, null);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, width, height);
  }
  
  @Override
  public Shape getShape() {
    return new Rectangle2D.Double(x, y, width, height);
  }
 
  private void collision() {
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
        if (tmpObj.getID() == ObjectID.IndestructibleBlock || tmpObj.getID() == ObjectID.DestructibleBlock ||
                tmpObj.getID() == ObjectID.Player1){
          if (getBounds().intersects(tmpObj.getBounds())) {
            x += xSpeed * -1;
            y += ySpeed * -1;
          }
        }
    }
  }
}