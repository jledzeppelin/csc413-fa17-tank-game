import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TankPlayer1 extends GameObject {
  private double angle;
  private int tmpAngle;
  
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
    tank = tankStrip.getSubimage(0 * tileSize, 0, tileSize, tileSize); //tank facing left

    reloadTime = 0;
    
    this.handler = handler;
  }
  
  @Override
  public void tick() {
    angle = Math.toRadians(tmpAngle);
    
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
      tmpAngle += 5;
    } else if (!handler.isLeftPlayer1()) {
      tmpAngle += 0;
    }
    
    if (handler.isLeftPlayer1()) {
      tmpAngle -= 5;
    } else if (!handler.isRightPlayer1()) {
      tmpAngle -= 0;
    }
    
    if (handler.isShootPlayer1()) {
      if (reloadTime == 0) {
        handler.addObject(new Bullet(x + tankWidth, y + tankHeight / 2, angle, 8, ObjectID.Bullet, handler));
        reloadTime = 100; //wait between bullets
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
  
  /*
  private void collision() {
    TankCollision tankCollision = new TankCollision();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      GameObject tmpObj = handler.obj.get(i);
      
      if (tmpObj.getID() == ObjectID.DestructibleBlock || tmpObj.getID() == ObjectID.IndestructibleBlock ||
              tmpObj.getID() == ObjectID.Player2) {
        if (tankCollision.collides((int)(x + velocityX), y, getBounds(), tmpObj.getBounds())) {
          velocityX = 0;
        }
        
        if (tankCollision.collides(x, (int)(y + velocityY), getBounds(), tmpObj.getBounds())) {
          velocityY = 0;
        }
      }
    }
    
  }
*/
  public double getAngle() {
    return angle;
  }
  public void setAngle(double angle) {
    this.angle = angle;
  }
}