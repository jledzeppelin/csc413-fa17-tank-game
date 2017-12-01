
import java.awt.Rectangle;

public class TankCollision {
  
  public boolean collides(int x, int y, Rectangle rec1, Rectangle rec2) {
    rec1.x = x;
    rec1.y = y;
    
    if (rec1.intersects(rec2)) {
      return true;
    }
    return false;
  }
  
}