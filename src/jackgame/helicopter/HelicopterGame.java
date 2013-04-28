package jackgame.helicopter;


import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;


import java.io.IOException;


public class HelicopterGame 
    extends MIDlet
    implements CommandListener {
  private Display mDisplay;
  
  private HelicopterCanvas mCanvas;
  private Command mExitCommand;
  
  public void startApp() {
    try{
      if (mCanvas == null) {
        mCanvas = new HelicopterCanvas();
        mCanvas.start();
        mExitCommand = new Command("Exit", Command.EXIT, 0);
        mCanvas.addCommand(mExitCommand);
        mCanvas.setCommandListener(this);
      }
    }
    catch (IOException ioe) {
      System.out.println(ioe);
    }       	    
    mDisplay = Display.getDisplay(this);
    mDisplay.setCurrent(mCanvas);
  }
  
  public void pauseApp() {}
  
  public void destroyApp(boolean unconditional) {
    mCanvas.stop();
  }
  
  public void commandAction(Command c, Displayable s) {
    if (c.getCommandType() == Command.EXIT) {
      destroyApp(true);
      notifyDestroyed();
    }
  }
}
