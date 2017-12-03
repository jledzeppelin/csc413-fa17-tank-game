
public class Camera {
  private int width;
  private int height;
  private float x, y;
  
  public Camera(float x, float y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  public void tickPlayer1(GameObject obj) {
    x += ((obj.getX() - x) - width / 4) * 0.05f;
    y += ((obj.getY() - y) - height / 2) * 0.05f;
    
    if (x <= 0) x = 0;
    if (x >= 1100) x = 1100;
    if (y <= 0) y = 0;
    if (y >= 1065) y = 1065;
  }
  
  public void tickPlayer2(GameObject obj) {
    x += ((obj.getX() - x) - 3 * width / 4) * 0.05f;
    y += ((obj.getY() - y) - height / 2) * 0.05f;
    
    if (x <= -510) x = -510;
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
