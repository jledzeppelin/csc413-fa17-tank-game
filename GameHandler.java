import java.util.ArrayList;
import java.awt.Graphics;

public class GameHandler {
  ArrayList<GameObject> obj = new ArrayList<GameObject>();
  
  //TO DO change to forward and back and rotate, add shooting
  private boolean up = false;
  private boolean down = false;
  private boolean right = false;
  private boolean left = false;
  
  private boolean up2 = false;
  private boolean down2 = false;
  private boolean right2 = false;
  private boolean left2 = false;
  
  public void tick() { 
    //go through all game objects and tick
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      tmpObj.tick();
    }
  }
  
  public void render(Graphics graphics) {
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      tmpObj.render(graphics);
    }
  }
  
  //add object to the list
  public void addObject(GameObject tmpObj) {
    obj.add(tmpObj);
  }
  
  public void removeObject(GameObject tmpObj) {
    obj.remove(tmpObj);
  }
  
  //player 1
  public boolean isUpPlayer1() {
    return up;
  }
  public boolean isDownPlayer1() {
    return down;
  }
  public boolean isRightPlayer1() {
    return right;
  }
  public boolean isLeftPlayer1() {
    return left;
  }
  public void setUpPlayer1(boolean up) {
    this.up = up;
  }
  public void setDownPlayer1(boolean down) {
    this.down = down;
  }
  public void setRightPlayer1(boolean right) {
    this.right = right;
  }
  public void setLeftPlayer1(boolean left) {
    this.left = left;
  }
  
  //player 2
  public boolean isUpPlayer2() {
    return up2;
  }
  public boolean isDownPlayer2() {
    return down2;
  }
  public boolean isRightPlayer2() {
    return right2;
  }
  public boolean isLeftPlayer2() {
    return left2;
  }
  public void setUpPlayer2(boolean up) {
    this.up2 = up;
  }
  public void setDownPlayer2(boolean down) {
    this.down2 = down;
  }
  public void setRightPlayer2(boolean right) {
    this.right2 = right;
  }
  public void setLeftPlayer2(boolean left) {
    this.left2 = left;
  }
}