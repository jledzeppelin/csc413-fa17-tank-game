/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Sprite {
  private BufferedImage[] images;
  String spriteFile;
  int tileSize;
  
  public Sprite(String spriteFile1, int tileSize1) throws IOException{
    spriteFile = spriteFile1;
    tileSize = tileSize1;

    this.loadImages();
  }
  
  private void loadImages() throws IOException {
    BufferedImage image = ImageIO.read( new File( spriteFile ));

    this.images = new BufferedImage[ image.getWidth() / tileSize ];

    for( int index = 0; index < this.images.length; index++ ) {
      this.images[ index ] = image.getSubimage(
          index * this.tileSize, 0, this.tileSize, this.tileSize
      );
    }
  }
  
  public BufferedImage getFrame( int frame ) {
    return this.images[ frame ];
  }

  public int frameCount() {
    return this.images.length;
  }
  
  // step is different for tank and bullet
  public void moveAmount(int frame, int x, int y, int stepSize){
    int calc = images.length;
    if (0 <= frame && frame <= (calc/4)){
      x -=  stepSize / (calc / 4);
      y +=  stepSize / (calc / 4);
    } else if ((calc/4) <= frame && frame <= (calc/2)){
      x -=  stepSize / (calc / 4);
      y -=  stepSize / (calc / 4);
    } else if ((calc/2) <= frame && frame <= (3*calc/4)){
      x +=  stepSize / (calc / 4);
      y -=  stepSize / (calc / 4);
    } else {
      x +=  stepSize / (calc / 4);
      y +=  stepSize / (calc / 4);
    }
  }
}
