import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Shape;

public abstract class GameObject {
  public final int blockWidth = 32;
  public final int blockHeight = 32;
  
  protected double x, y, angle;
  protected int width;
  protected int height;
  protected int health;
  protected int lives;
  protected ObjectID id;
  
  public GameObject(double x, double y, ObjectID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }
  
  //objects update
  public abstract void tick();
  public abstract void render(Graphics graphics);
  public abstract void renderMini(Graphics graphics, int x, int y);
  public abstract Rectangle getBounds();
  public abstract Shape getShape();
  
  //setters and getters
  public double getX() {
    return x;
  }
  public double getY() {
    return y;
  }
  public double getAngle() {
    return angle;
  }
  public int getWidth() {
    return width;
  }
  public int getHeigh() {
    return height;
  }
  public int getHealth() {
    return health;
  }
  public int getLives() {
    return lives;
  }
  public void setX(double x) {
    this.x = x;
  }
  public void setY(double y) {
    this.y = y;
  }
  public void setAngle(double a) {
    this.angle = a;
  }
  public void setWidth(int w) {
    this.width = w;
  }
  public void setHeight(int h) {
    this.height = h;
  }
  public void setHealth(int h) {
    this.health = h;
  }
  public void setLives(int l) {
    this.lives = l;
  }
  public ObjectID getID() {
    return id;
  }
  public void setID(ObjectID id) {
    this.id = id;
  }
}