package jackgame.helicopter;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
/**
   animates a fire 
*/
class FireSprite extends GameSprite
{
    static final int MAX_HIT_POINTS = 20;
    private int hitPoints;
    private int currentFrame;
    private final int maxHeight;
    private GameEventListener listener;

    public FireSprite(Image image, int frameWidth, int frameHeight, GameEventListener listener)
    {   
         super(image, frameWidth, frameHeight);
         setPosition(Level.current.fireCoord[0], Level.current.fireCoord[1]);
         this.hitPoints = MAX_HIT_POINTS;
         this.maxHeight = frameHeight;
         this.listener = listener;
         // TODO : this.attack_fire_sound = load_sound('attaquefeu.wav')
    }


    public void update()
    {  try{
       setFrame((getFrame() + 1 )% 4);

       }
       catch (Exception e)
       {
          System.err.println("Can't set frame" + getFrame());
       }
    }
/*
    def update(this):
        bottomleft = this.rect.bottomleft
        scale = pygame.transform.scale
        this.image = scale(this.variations[this.current_frame / this.animation_speed ][0], (this.rect.width, this.MAX_HEIGHT * this.hit_points / this.MAX_HIT_POINTS))

        self.rect = self.image.get_rect(bottomleft = bottomleft)
        self.current_frame = (self.current_frame + 1) % (self.animation_speed * len(self.variations))

*/


    public void reduceSize()
    {
        System.err.println("reduce size");
        this.hitPoints -= 25;
        if (this.hitPoints <= 0)
        {
            listener.signalEvent(GameEvent.fireIsDead);
        }
        else
        {
            //self.attack_fire_sound.play()
        }

    }
}
