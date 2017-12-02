import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TankPlayer1 extends GameObject {
  private int tmpAngle;
  
  private BufferedImage tank;
  private int tileSize;
  
  private int reloadTime;

  GameHandler handler;
  
  public TankPlayer1(double x, double y, int a, ObjectID id, GameHandler handler) {
    super(x, y, id);
    angle = Math.toRadians(a);
    width = 64;
    height = 64;
    tmpAngle = a;
    
    ImageLoader loader = new ImageLoader();
    BufferedImage tankStrip = loader.loadImage("/Tank_blue_heavy_strip60.png");
    tileSize = tankStrip.getWidth() / 60;
    tank = tankStrip.getSubimage(0 * tileSize, 0, tileSize, tileSize);

    reloadTime = 0;
    
    this.handler = handler;
  }
  
  @Override
  public void tick() {
    angle = Math.toRadians(tmpAngle);
    
    collision();
    
    if (handler.isForwardPlayer1()) {
      x += Math.cos(angle); //could add a speed variable to change in TankGame
      y += Math.sin(angle);
    } else if (!handler.isBackwardPlayer1()) {
      x += 0;
      y += 0;
    }
    
    if (handler.isBackwardPlayer1()) {
      x -= Math.cos(angle);
      y -= Math.sin(angle);
    } else if (!handler.isForwardPlayer1()) {
      x += 0;
      y += 0;
    }
    
    if (handler.isRightPlayer1()) {
      tmpAngle += 2;
    } else if (!handler.isLeftPlayer1()) {
      tmpAngle += 0;
    }
    
    if (handler.isLeftPlayer1()) {
      tmpAngle -= 2;
    } else if (!handler.isRightPlayer1()) {
      tmpAngle -= 0;
    }
    
    if (handler.isShootPlayer1()) {
      if (reloadTime == 0) {
        //bullet takes (x, y, angle, speed, id, handler)
        handler.addObject(new Bullet(x + width / 2, y + height / 2, angle, 8, ObjectID.Bullet, handler));
        reloadTime = 100;
      } else {
        reloadTime -= 1;
      }
    } else if (!handler.isShootPlayer1()) {
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
    
    //rotating tank around center
    graphics2D.rotate(angle, x + width / 2, y + height / 2);
    graphics2D.drawImage(tank, (int)x, (int)y, width, height, null);
    
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
 
  private void collision() {
    boolean collides;
    TankCollision collisionCheck = new TankCollision();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      if (tmpObj.getID() == ObjectID.DestructibleBlock || tmpObj.getID() == ObjectID.IndestructibleBlock ||
              tmpObj.getID() == ObjectID.Player2) {
        
        collides = collisionCheck.collides(this.getShape(), angle, tmpObj.getShape(), tmpObj.getAngle());
        
        if (collides) {
          //TO DO
        }
      }
    }
  }
}