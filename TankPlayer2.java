import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TankPlayer2 extends GameObject {
  //private int frontOfTankX;
  //private int frontOfTankY;
  //private int centerOfTankX;
  //private int centerOfTankY;
  private double angle;
  private int tmpAngle = 0;
  
  //private int tmpLoad; //shooting
  //private ArrayList bullets;
  //private String img = "/Tank_red_basic_strip60.png";
  //private Image image;

  GameHandler handler;
  
  public TankPlayer2(double x, double y, double angle, ObjectID id, GameHandler handler) {
    super(x, y, id);
    this.angle = Math.toRadians(angle);
    
    //bullets = new ArrayList();
    //tmpLoad = 0;
    
    //ImageIcon ii = new ImageIcon(img);
    //image = ii.getImage();
    
    this.handler = handler;
  }
  
  @Override
  public void tick() {
    angle = Math.toRadians(tmpAngle);
    
    if (handler.isForwardPlayer2()) {
      x += Math.cos(angle); 
      y += Math.sin(angle);
    } else if (!handler.isBackwardPlayer2()) {
      x += 0;
      y += 0;
    }
    
    if (handler.isBackwardPlayer2()) {
      x -= Math.cos(angle);
      y -= Math.sin(angle);
    } else if (!handler.isForwardPlayer2()) {
      x += 0;
      y += 0;
    }
    
    if (handler.isRightPlayer2()) {
      tmpAngle += 5;
    } else if (!handler.isLeftPlayer2()) {
      tmpAngle += 0;
    }
    
    if (handler.isLeftPlayer2()) {
      tmpAngle -= 5;
    } else if (!handler.isRightPlayer2()) {
      tmpAngle -= 0;
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
    graphics2D.setColor(Color.green);
    
    //rotating tank around center
    graphics2D.rotate(angle, x + tankWidth / 2, y + tankHeight / 2);
    graphics2D.drawRect((int)x, (int)y, tankWidth, tankHeight);
    
    //to show where the front of tank is
    graphics2D.fillRect((int)x + tankWidth, (int)y + 10, 10, 30);
    
    graphics2D.setTransform(old);
  }
  
  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, tankWidth, tankHeight);
  }
  
  //public ArrayList getBullets() {
  //  return bullets;
  //}
  
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