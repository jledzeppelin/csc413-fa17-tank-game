import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class TankCollision {
  private Area tmpArea1;
  private Area tmpArea2;
  
  public boolean collides(Shape shape1, double angle1, Shape shape2, double angle2) {
    tmpArea1 = new Area(shape1);
    tmpArea2 = new Area(shape2);
    
    AffineTransform shape1Trans = AffineTransform.getRotateInstance(angle1, shape1.getBounds().x,
            shape1.getBounds().y);
    tmpArea1.transform(shape1Trans);
    
    AffineTransform shape2Trans = AffineTransform.getRotateInstance(angle2, shape2.getBounds().x,
            shape2.getBounds().y);
    tmpArea2.transform(shape2Trans);
    
    tmpArea1.intersect(tmpArea2);
    return !tmpArea1.isEmpty();
  }
  
}