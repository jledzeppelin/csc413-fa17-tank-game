/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;


public class XYObject {
  
  protected int x;
  protected int y;
  protected int ob;

  
  XYObject(int x1, int y1, int ob1){
    x = x1;
    y = y1;
    ob = ob1;
  }
  
  public int getX(){
    return x;
  }
  public void setX(int x1) {
    x = x1;
  }
  public int getY(){
    return y;
  }
  public void setY(int y1) {
    y = y1;
  }
  public int getOb(){
    return y;
  }
  public void setOb(int y1) {
    y = y1;
  }
}
