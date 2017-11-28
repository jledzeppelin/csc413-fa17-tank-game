import java.util.Observable;

public class GameClock extends Observable {
  private int start;
  private int currentFrame;
  
  public GameClock() {
    start = (int)System.currentTimeMillis();
    currentFrame = 0;
  }
  
  public void tick() {
    //setChanged()
    currentFrame++;
    this.notifyObservers();
  }
  
  public int getCurrentFrame() {
    return currentFrame;
  }
  
  public int getTime() {
    return (int)System.currentTimeMillis() - start;
  }
}