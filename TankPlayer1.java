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
  public final int tankWidth = 64;
  public final int tankHeight = 64;
  private double angle;
  private int tmpAngle;
  private Shape tankShape = null;
  private Area tankArea = null;
  
  private BufferedImage tank;
  private int tileSize;
  
  private int reloadTime;

  GameHandler handler;
  
  public TankPlayer1(double x, double y, int angle, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.angle = Math.toRadians(angle);
    tmpAngle = angle;
    
    ImageLoader loader = new ImageLoader();
    BufferedImage tankStrip = loader.loadImage("/Tank_blue_heavy_strip60.png");
    tileSize = tankStrip.getWidth() / 60;
    tank = tankStrip.getSubimage(0 * tileSize, 0, tileSize, tileSize);

    reloadTime = 0;
    
    tankShape = new Rectangle2D.Double(x, y, tankWidth, tankHeight);
    tankArea = new Area(tankShape);
    
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
        handler.addObject(new Bullet(x + tankWidth / 2, y + tankHeight / 2, angle, 8, ObjectID.Bullet, handler));
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
    graphics2D.rotate(angle, x + tankWidth / 2, y + tankHeight / 2);
    graphics2D.drawImage(tank, (int)x, (int)y, tankWidth, tankHeight, null);
    
    graphics2D.setTransform(old);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, tankWidth, tankHeight);
  }
 
  private void collision() {
    double tmpAngle;
    Area tmpArea;
    Rectangle tmpRec;
    boolean collides;
    TankCollision collisionCheck = new TankCollision();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      if (tmpObj.getID() == ObjectID.DestructibleBlock || tmpObj.getID() == ObjectID.IndestructibleBlock ||
              tmpObj.getID() == ObjectID.Player2) {
        
        //need angle, area of both objects, bounds of both objects
        if (tmpObj.getID() != ObjectID.Player2) {
          tmpAngle = 0;
          
          if (tmpObj.getID() == ObjectID.DestructibleBlock) {
            DestructibleBlock tmpBlock;
            tmpBlock = (DestructibleBlock) tmpObj;
            
            tmpArea = tmpBlock.getArea();
            tmpRec = tmpBlock.getBounds();
          } else {
            IndestructibleBlock tmpBlock;
            tmpBlock = (IndestructibleBlock) tmpObj;
            
            tmpArea = tmpBlock.getArea();
            tmpRec = tmpBlock.getBounds();
          }
          
        } else {
          TankPlayer2 tmpTank;
          tmpTank = (TankPlayer2) tmpObj;
          
          tmpAngle = tmpTank.getAngle();
          tmpArea = tmpTank.getArea();
          tmpRec = tmpTank.getBounds();
        }
        
        collides = collisionCheck.collides(this.getArea(), angle, this.getBounds(), tmpArea, tmpAngle, tmpRec);
        
        if (collides) {
          
        }
      }
    }
  }
  
  public double getAngle() {
    return angle;
  }
  public void setAngle(double angle) {
    this.angle = angle;
  }
  public Area getArea() {
    return tankArea;
  }
}