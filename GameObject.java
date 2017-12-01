import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject {
  public final int tankWidth = 64;
  public final int tankHeight = 64;
  public final int blockWidth = 32;
  public final int blockHeight = 32;
  public final int bulletWidth = 5;
  public final int bulletHeight = 5;
  
  protected double x, y;
  protected float velocityX = 0; //may not need these
  protected float velocityY = 0;
  protected ObjectID id;
  
  public GameObject(double x, double y, ObjectID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }
  
  //objects update
  public abstract void tick();
  public abstract void render(Graphics graphics);
  public abstract Rectangle getBounds();
  
  //setters and getters
  public double getX() {
    return x;
  }
  public double getY() {
    return y;
  }
  public void setX(double x) {
    this.x = x;
  }
  public void setY(double y) {
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