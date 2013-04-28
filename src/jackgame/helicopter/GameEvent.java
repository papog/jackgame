package jackgame.helicopter;

class GameEvent
{
    static final GameEvent dropWater = new GameEvent("drop water");
    static final GameEvent dropWaterEnded = new GameEvent("drop water ended");
    static final GameEvent fillBucket = new GameEvent("fill bucket");
    static final GameEvent fireIsDead = new GameEvent("fire is dead");
 
    private String name;


    private GameEvent(String name)
    {
        this.name = name;
    }

   public String getName()
   {
        return name;
   }
}


