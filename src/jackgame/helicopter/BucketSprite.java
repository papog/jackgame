package jackgame.helicopter;
import javax.microedition.lcdui.*;

/**
   animates a bucket carried by helicopter 
*/
class BucketSprite extends GameSprite
{
    private boolean full;
    private GameEventListener listener;
    private HelicopterSprite helicopter;


    public BucketSprite(Image image, int frameWidth, int frameHeight, HelicopterSprite helicopter, GameEventListener listener)
    {   
         super(image, frameWidth, frameHeight);
         full = false;
         this.helicopter = helicopter;
         this.listener = listener;
    }

    public void dropWater(){
        if (full)
        {
            listener.signalEvent(GameEvent.dropWater);
        }
     
        setEmpty();
    }

    public void setEmpty(){
        full = false;
        setFrame(0);
    }
    public void setFull(){
        full = true;
        setFrame(1);
    }

    public void update(HelicopterSprite helicopter)
    {
        setPosition(helicopter.getX() + helicopter.getWidth()/3,
                    helicopter.getY() + helicopter.getHeight());
        if (collidesWith(Level.current.waterFillRect, false))
        {
            setFull(); 
        }
        if (collidesWith(Level.current.waterDropRect, false))
        {
            dropWater();
        }

    }    
}

