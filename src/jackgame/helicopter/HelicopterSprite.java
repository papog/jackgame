package jackgame.helicopter;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
/**
  * moves a helicopter on screen following predefined track"""
  */


public class HelicopterSprite
    extends GameSprite {

  int [] speed;
  int [] target;


  public HelicopterSprite(Image image, int frameWidth, int frameHeight) {
    super(image, frameWidth, frameHeight);
    defineReferencePixel(frameWidth / 2, frameHeight / 2);
    setPosition(100,100);
    setPosition(Level.current.landingCoord[0], Level.current.landingCoord[1]);
    setSpeed(Level.current.nullSpeed);
    this.target = Level.current.landingCoord;
  }

  
  public void setSpeed(int [] speed)
  {
     this.speed = speed;
  }

  public void changeSpeed(int [] speed)
  {
     this.speed = speed; 
  }
  public void moveBy(int [] speed)
  {
     this.move(speed[0], speed[1] );
  }

  public void moveLeft()
  {
     moveBy(Level.current.leftSpeed); 
  }
  public void moveRight()
  {
     moveBy(Level.current.rightSpeed); 
  }
  public void moveUp()
  {
     moveBy(Level.current.upSpeed); 
  }
  public void moveDown()
  {
     moveBy(Level.current.downSpeed); 
  }

  public void gotoBase()
  {
     this.target = Level.current.landingCoord;
  }
  public void gotoFire()
  {
     this.target = Level.current.aboveFireCoord;
  }
  public void gotoWater()
  {
     this.target = Level.current.aboveWaterCoord;
  }

  /**
    *  move the helicopter
    */

  public void update()
  {

     this.moveBy(this.speed);
     int x_offset = this.getX() - this.target[0];
     int y_offset = this.getY() - this.target[1];

     //System.err.println("gameX = " + this.gameX + "x_offset = "+ x_offset + "y_offset = " + y_offset);
     if (Math.abs(x_offset) > Level.current.tolerance)
     {
         if (this.getY() > Level.current.cruiseAltitude)
         {
            changeSpeed(Level.current.upSpeed);   
         }
         else
         {
            if (x_offset > 0)
                this.changeSpeed(Level.current.leftSpeed);
            else
                this.changeSpeed(Level.current.rightSpeed);
         } 
     }
     else
     {
        if (Math.abs(y_offset) > Level.current.tolerance && y_offset < 0)
           this.changeSpeed(Level.current.downSpeed);
        else
           this.changeSpeed(Level.current.nullSpeed);        
     }
   }
}
