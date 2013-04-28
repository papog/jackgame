package jackgame.helicopter;

import javax.microedition.lcdui.game.*;
import javax.microedition.lcdui.*;

class Level
{
  public static Sprite dummySprite(int x, int y, int w, int h)
  {
    Sprite s =  new Sprite(Image.createImage(w,h));
    s.setPosition(x,y);
    return s;
  }
  public int [] landingCoord = {401,329};
  public int [] aboveFireCoord = {100,250};
  public int [] fireCoord = {100,310};
  public int [] aboveWaterCoord = {270,259};
  public Sprite waterFillRect = dummySprite(250,295,90,70);
  public Sprite waterDropRect = dummySprite(100,290,100,100);
  public int    cruiseAltitude = 200;
  public int [] upSpeed = {0, -3};
  public int [] downSpeed = {0, 2};
  public int [] leftSpeed = {-3 , 0};
  public int [] rightSpeed = {3, 0};
  public int [] nullSpeed = {0, 0};
  public int    tolerance = 3;
  
  public static Level current = new Level();
  public Level()
  {
  }
}

