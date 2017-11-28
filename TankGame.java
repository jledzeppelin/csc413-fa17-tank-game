import java.awt.*;
import java.awt.Canvas;
import java.awt.image.*;
//import java.util.Observable;
//import java.util.Observer;
//import javax.swing.*;

public class TankGame extends Canvas implements Runnable {
  public static int WIDTH = 1000;
  public static int HEIGHT = 563;
  
  private boolean isRunning = false;
  private Thread thread;
  private GameHandler handler;
  private BufferedImage level = null;
  private Camera cameraPlayer1;
  private Camera cameraPlayer2;
  //need another camera for player2
  //private Camera cameraPlayer2
          
  public TankGame() {
    new GameWindow(WIDTH, HEIGHT, "Tank Game", this);
    start();
    
    //use this handler only (only instance created)
    handler = new GameHandler();
    cameraPlayer1 = new Camera(0, 0);
    cameraPlayer2 = new Camera(0, 0);
    this.addKeyListener(new KeyInput(handler));
    
    ImageLoader loader = new ImageLoader();
    level = loader.loadImage("/tankGameMap.png");
    
    loadLevel(level);
  }
  
  private void start() {
    isRunning = true;
    thread = new Thread(this);
    thread.start();
  }
  
  private void stop() {
    isRunning = false;
    
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  //update everything in game
  public void tick() {
    
    for (int i = 0; i < handler.obj.size(); i++) {
      if (handler.obj.get(i).getID() == ObjectID.Player1) {
        cameraPlayer1.tick(handler.obj.get(i));
      }
      //TO DO add another camera for player 2
      if (handler.obj.get(i).getID() == ObjectID.Player2) {
        cameraPlayer2.tick2(handler.obj.get(i));
      }
    }
    
    handler.tick();
  }
  
  //render everything in game
  public void render() {
    BufferStrategy buffStrat = this.getBufferStrategy();
    
    if (buffStrat == null) {
      this.createBufferStrategy(3);
      return;
    }
    
    //TO DO camera for player 2
    Graphics graphics = buffStrat.getDrawGraphics();
    Graphics2D graphics2D = (Graphics2D) graphics;
    
    //***************** Start of drawing section
    
    graphics.setColor(Color.lightGray);
    graphics.fillRect(0, 0, WIDTH, HEIGHT);
    
    graphics2D.translate(-cameraPlayer1.getX(), -cameraPlayer1.getY());  
    handler.render(graphics, cameraPlayer1);
    graphics2D.translate(cameraPlayer1.getX(), cameraPlayer1.getY());
    
    graphics2D.translate(-cameraPlayer2.getX(), -cameraPlayer2.getY());  
    handler.render2(graphics, cameraPlayer2);
    graphics2D.translate(cameraPlayer2.getX(), cameraPlayer2.getY());
    
    //***************** End of drawing section
    
    graphics.dispose();
    buffStrat.show();
  }
  
  public void loadLevel(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    
    for (int xAxis = 0; xAxis < width; xAxis++) {
      for (int yAxis = 0; yAxis < height; yAxis++) {
        int pixel = image.getRGB(xAxis, yAxis);
        
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        
        if (red == 255) {
          handler.addObject(new IndestructibleBlock(xAxis * 32, yAxis * 32, ObjectID.IndestructibleBlock));
        }
        if (red == 170) {
          handler.addObject(new DestructibleBlock(xAxis * 32, yAxis * 32, ObjectID.DestructibleBlock));
        }
        //maybe change to xAxis * 64 and only draw in one pixel for the tanks in the level
        if (blue == 255) {
          handler.addObject(new TankPlayer1(xAxis * 32, yAxis * 32, ObjectID.Player1, handler));
        }
        if (green == 255) {
          handler.addObject(new TankPlayer2(xAxis * 32, yAxis * 32, ObjectID.Player2, handler));
        }
        
      }
    }
  }
  
  //game loop here
  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    
    while (isRunning) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      
      while (delta >= 1) {
        tick();
        delta--;
      }
      
      render();
      frames++;
      
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        frames = 0;
      }
    }
    stop();
  }
  
  public static void main(String args[]) {
    new TankGame();
  }
  
}