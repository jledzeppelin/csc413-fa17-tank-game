import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class TankCollision {
  private Area tmpArea1;
  private Area tmpArea2;
  
  public boolean collides(Area area1, double angle1, Rectangle rec1,
          Area area2, double angle2, Rectangle rec2) {
    
    AffineTransform tank1 = new AffineTransform();
    tank1.rotate(angle1, rec1.x, rec1.y);
    
    AffineTransform tank2 = new AffineTransform();
    tank2.rotate(angle2, rec2.x, rec2.y);
    
    tmpArea1 = area1.createTransformedArea(tank1);
    tmpArea2 = area2.createTransformedArea(tank2);
    
    if (tmpArea1.intersects(tmpArea2.getBounds()) && tmpArea2.intersects(tmpArea1.getBounds())) {
      return true;
    } 
    return false;
  }
  
}