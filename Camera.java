
public class Camera {
  public static int WIDTH = 1000;
  public static int HEIGHT = 563;
  private float x, y;
  
  public Camera(float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  public void tick(GameObject obj) {
    //makes camera smooth
    x += ((obj.getX() - x) - WIDTH / 4) * 0.05f;
    y += ((obj.getY() - y) - HEIGHT / 2) * 0.05f;
    
    if (x <= 0) x = 0;
    if (x >= 1100) x = 1100;
    if (y <= 0) y = 0;
    if (y >= 1065) y = 1065;
  }
  public void tick2(GameObject obj) {
    //makes camera smooth
    x += ((obj.getX() - x) - 3*WIDTH / 4) * 0.05f;
    y += ((obj.getY() - y) - HEIGHT / 2) * 0.05f;
    
    if (x <= -500) x = -500;
    if (x >= 605) x = 605;
    if (y <= 0) y = 0;
    if (y >= 1065) y = 1065;
  }
  
  public float getX() {
    return x;
  }
  public float getY() {
    return y;
  }
  public void setX(float x) {
    this.x = x;
  }
  public void setY(float y) {
    this.y = y;
  }
}
