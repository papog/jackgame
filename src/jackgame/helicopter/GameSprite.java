package jackgame.helicopter;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 */
public class GameSprite
        extends Sprite {

    public GameSprite(Image image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        defineReferencePixel(frameWidth / 2, frameHeight / 2);
    }

 
}
