import java.util.ArrayList;
import java.awt.Graphics;

public class GameHandler {
  ArrayList<GameObject> obj = new ArrayList<GameObject>();
  
  private boolean forwardP1 = false;
  private boolean backwardP1 = false;
  private boolean rotateRightP1 = false;
  private boolean rotateLeftP1 = false;
  private boolean shootP1 = false;
  
  private boolean forwardP2 = false;
  private boolean backwardP2 = false;
  private boolean rotateRightP2 = false;
  private boolean rotateLeftP2 = false;
  private boolean shootP2 = false;
  
  public void tick() { 
    //tick all game objects
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      tmpObj.tick();
    }
  }
  
  public void renderPlayer1(Graphics graphics, Camera camera) {
    //render objects for player1
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      
      if (tmpObj.getX() < camera.getX() + 501) {
        tmpObj.render(graphics);
      }
    }
  }
  
  public void renderPlayer2(Graphics graphics, Camera camera) {
    for (int i = 0; i < obj.size(); i++) {
      GameObject tmpObj = obj.get(i);
      
      if (tmpObj.getX() > camera.getX() + 501) {
        tmpObj.render(graphics);
      }
    }
  }
  
  public void addObject(GameObject tmpObj) {
    obj.add(tmpObj);
  }
  
  public void removeObject(GameObject tmpObj) {
    obj.remove(tmpObj);
  }
  
  //player 1
  public boolean isForwardPlayer1() {
    return forwardP1;
  }
  public boolean isBackwardPlayer1() {
    return backwardP1;
  }
  public boolean isRightPlayer1() {
    return rotateRightP1;
  }
  public boolean isLeftPlayer1() {
    return rotateLeftP1;
  }
  public boolean isShootPlayer1() {
    return shootP1;
  }
  public void setForwardPlayer1(boolean forward) {
    this.forwardP1 = forward;
  }
  public void setBackwardPlayer1(boolean backward) {
    this.backwardP1 = backward;
  }
  public void setRightPlayer1(boolean right) {
    this.rotateRightP1 = right;
  }
  public void setLeftPlayer1(boolean left) {
    this.rotateLeftP1 = left;
  }
  public void setShootPlayer1(boolean shoot) {
    this.shootP1 = shoot;
  }
  
  //player 2
  public boolean isForwardPlayer2() {
    return forwardP2;
  }
  public boolean isBackwardPlayer2() {
    return backwardP2;
  }
  public boolean isRightPlayer2() {
    return rotateRightP2;
  }
  public boolean isLeftPlayer2() {
    return rotateLeftP2;
  }
  public boolean isShootPlayer2() {
    return shootP2;
  }
  public void setForwardPlayer2(boolean forward) {
    this.forwardP2 = forward;
  }
  public void setBackwardPlayer2(boolean backward) {
    this.backwardP2 = backward;
  }
  public void setRightPlayer2(boolean right) {
    this.rotateRightP2 = right;
  }
  public void setLeftPlayer2(boolean left) {
    this.rotateLeftP2 = left;
  }
  public void setShootPlayer2(boolean shoot) {
    this.shootP2 = shoot;
  }
}