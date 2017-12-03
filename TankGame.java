import java.awt.*;
import java.awt.Canvas;
import java.awt.image.*;

public class TankGame extends Canvas implements Runnable {
  public static int WIDTH = 1000;
  public static int HEIGHT = 563;
  
  private boolean isRunning = false;
  private Thread thread;
  private GameHandler handler;
  
  private Camera cameraPlayer1;
  private Camera cameraPlayer2;
  public static int player1Health;
  public static int player1Lives;
  public static int player2Health;
  public static int player2Lives;
  
  
  private BufferedImage level;
  private BufferedImage livesP1;
  private BufferedImage livesP2;
  private BufferedImage background;
  private Background b;
  private BufferedImage b1;
  
  private int tileSize;
          
  public TankGame() {
    new GameWindow(WIDTH, HEIGHT, "Tank Game", this);
    start();
    
    handler = new GameHandler();
    cameraPlayer1 = new Camera(0, 0, WIDTH, HEIGHT);
    cameraPlayer2 = new Camera(0, 0, WIDTH, HEIGHT);
    this.addKeyListener(new KeyInput(handler));
    
    ImageLoader loader = new ImageLoader();
    level = loader.loadImage("/tankGameMap.png");
    
    BufferedImage livesStrip = loader.loadImage("/Ball_strip9.png");
    tileSize = livesStrip.getWidth() / 9;
    livesP1 = livesStrip.getSubimage(3 * tileSize, 0, tileSize, tileSize);
    livesP2 = livesStrip.getSubimage(6 * tileSize, 0, tileSize, tileSize);
    
    loadLevel(level);
    
    b = new Background();
    background = b.getBackground();
    b1 = background.getSubimage(0, 0, WIDTH, WIDTH);
    
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
    
    //*****left camera is bigger than right******
    for (int i = 0; i < handler.obj.size(); i++) {
      if (handler.obj.get(i).getID() == ObjectID.Player1) {
        cameraPlayer1.tickPlayer1(handler.obj.get(i));
        player1Health = handler.obj.get(i).getHealth();
        player1Lives = handler.obj.get(i).getLives();
        
        
      }
      
      if (handler.obj.get(i).getID() == ObjectID.Player2) {
        cameraPlayer2.tickPlayer2(handler.obj.get(i));
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
    
    Graphics graphics = buffStrat.getDrawGraphics();
    Graphics2D graphics2D = (Graphics2D) graphics;
    
    //***************** Start of drawing section
    
    //background
    graphics.setColor(Color.lightGray);
    graphics.fillRect(0, 0, WIDTH, HEIGHT);
    graphics.drawImage(background, 0, 0, null);

    //cameras
    graphics2D.translate(-cameraPlayer1.getX(), -cameraPlayer1.getY());
   
  
    handler.renderPlayer1(graphics, cameraPlayer1);
    graphics2D.translate(cameraPlayer1.getX(), cameraPlayer1.getY());
    
    graphics2D.translate(-cameraPlayer2.getX(), -cameraPlayer2.getY());
    handler.renderPlayer2(graphics, cameraPlayer2);
    graphics2D.translate(cameraPlayer2.getX(), cameraPlayer2.getY());
    
    //separator
    graphics.setColor(Color.black);
    graphics.fillRect(WIDTH / 2 - 20, 0, 32, HEIGHT);
    
    //minimap
    graphics.drawImage(b1, 400, 330, WIDTH/5, WIDTH/5, null);
    handler.renderMinimap(graphics, 400, 330);
    
    //health and lives
    graphics.setColor(Color.green);
    graphics.fillRect(0, 500, player1Health, 20);
    
    
    
    for (int i = 0; i < player1Lives; i++){
      graphics.drawImage(livesP1, 150 + 20 * i, 500, 20, 20, null);
    }
    
    graphics.setColor(Color.green);
    graphics.fillRect(600, 500, player2Health, 20);
    
    for (int i = 0; i < player2Lives; i++){
      graphics.drawImage(livesP2, 750 + 20 * i, 500, 20, 20, null);
    }
    
    
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
        if (blue == 255) {
          handler.addObject(new TankPlayer1(xAxis * 32, yAxis * 32, 180, ObjectID.Player1, handler));
        }
        if (green == 255) {
          handler.addObject(new TankPlayer2(xAxis * 32, yAxis * 32, 0, ObjectID.Player2, handler));
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