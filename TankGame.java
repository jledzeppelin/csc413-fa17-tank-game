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
  private BufferedImage background = null;
  private Camera cameraPlayer1;
  private Camera cameraPlayer2;
  public static int player1Health;
  public static int player1Lives;
  public static int player2Health;
  public static int player2Lives;
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
    
    
    background = loader.loadImage("/background_tile.png");
    
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
        player1Health = handler.obj.get(i).getHealth();
        player1Lives = handler.obj.get(i).getLives();
        
      }
      //TO DO add another camera for player 2
      if (handler.obj.get(i).getID() == ObjectID.Player2) {
        cameraPlayer2.tick2(handler.obj.get(i));
        player2Health = handler.obj.get(i).getHealth();
        player2Lives = handler.obj.get(i).getLives();
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
    for(int x = 0; x <= WIDTH + 600; x+= 300){
      for(int y = 0; y<= HEIGHT + 1000; y+= 200){
        
        graphics.drawImage(background, x, y, null);
       //graphics.drawImage(background, background.getWidth(), 0, null);
      }
    }
    handler.render(graphics, cameraPlayer1);
    graphics2D.translate(cameraPlayer1.getX(), cameraPlayer1.getY());
    
    graphics2D.translate(-cameraPlayer2.getX(), -cameraPlayer2.getY());  
    for(int x = 0; x <= WIDTH + 600; x+= 300){
     for(int y = 0; y<= HEIGHT + 1000; y+= 200){
       if( x >= cameraPlayer2.getX() + WIDTH/2)
       graphics.drawImage(background, x, y, null);
       //graphics.drawImage(background, background.getWidth(), 0, null);
      }
    }
    handler.render2(graphics, cameraPlayer2);
    graphics2D.translate(cameraPlayer2.getX(), cameraPlayer2.getY());
    //***************** End of drawing section
    graphics.setColor(Color.black);
    graphics.fillRect(WIDTH/2, 0, 30, HEIGHT);
    
    
    for(int x = 0; x <= WIDTH + 600; x+= 300){
     for(int y = 0; y<= HEIGHT + 1000; y+= 200){
      // if( x >= cameraPlayer2.getX() + WIDTH/2)
      // graphics.drawImage(background, x, y, null);
       graphics.drawImage(background, x/8 + 380, y/8 + 330, 300 / 7, 240 / 7, null);
      }
    }
    handler.renderMinimap(graphics, 400, 330);
    
    graphics.setColor(Color.green);
    graphics.fillRect(0, 500, player1Health, 20);
    
    for(int x = 0; x < player1Lives; x++){
      graphics.setColor(Color.gray);
      graphics.fillOval(150 + 20 * x, 500, 20, 20);
    }
    
    graphics.setColor(Color.green);
    graphics.fillRect(600, 500, player2Health, 20);
    
    for(int x = 0; x < player2Lives; x++){
      graphics.setColor(Color.gray);
      graphics.fillOval(750 + 20 * x, 500, 20, 20);
    }
    
    graphics.dispose();
    buffStrat.show();
  }
  
  public void loadLevel(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    boolean p1 = true;
    boolean p2 = true;
    
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
          if(p1){
          handler.addObject(new TankPlayer1(xAxis * 32, yAxis * 32, ObjectID.Player1, handler));
          p1 = false;
          }
        }
        if (green == 255) {
          if(p2){
            handler.addObject(new TankPlayer2(xAxis * 32, yAxis * 32, ObjectID.Player2, handler));
            p2 = false;
          }
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