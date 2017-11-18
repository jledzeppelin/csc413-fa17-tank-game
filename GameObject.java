import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject {
  protected int x, y;
  protected float velocityX = 0;
  protected float velocityY = 0;
  protected ObjectID id;
  
  public GameObject(int x, int y, ObjectID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }
  
  //objects update
  public abstract void tick();
  public abstract void render(Graphics graphics);
  public abstract Rectangle getBounds();
  
  //setters and getters
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public float getVelocityX() {
    return velocityX;
  }
  public float getVelocityY() {
    return velocityY;
  }
  public void setVelocityX(float velX) {
    velocityX = velX;
  }
  public void setVelocityY(float velY) {
    velocityY = velY;
  }
  public ObjectID getID() {
    return id;
  }
  public void setID(ObjectID id) {
    this.id = id;
  }
}