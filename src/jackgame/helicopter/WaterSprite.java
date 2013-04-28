package jackgame.helicopter;

import javax.microedition.lcdui.*;

/**
animate the water falling on the fire 
 */
class WaterSprite extends GameSprite {

    private GameEventListener listener;

    public WaterSprite(Image image, int frameWidth, int frameHeight, GameEventListener listener) {
        super(image, frameWidth, frameHeight);
        setVisible(false);
        defineReferencePixel(frameWidth / 2, 0);
        this.listener = listener;
    }

    public void drop() {
        setVisible(true);
        setFrame(0);
    }

    public void update() {
        try {
            if (isVisible()) {
                if (getFrame() == 3) {
                    listener.signalEvent(GameEvent.dropWaterEnded);
                    setVisible(false);
                }
                setFrame((getFrame() + 1) % 4);
            }
        } catch (Exception e) {
            System.err.println("Can't set frame" + getFrame());
        }
    }
}
