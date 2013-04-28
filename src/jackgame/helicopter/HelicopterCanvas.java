package jackgame.helicopter;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;

public class HelicopterCanvas
        extends GameCanvas
        implements Runnable, GameEventListener {

    private volatile boolean mTrucking;
    private long mFrameDelay;
    private Image background;
    private TiledLayer backgroundLayer;
    private int mState;
    private HelicopterSprite helicopter;
    private FireSprite fire;
    private WaterSprite water;
    private BucketSprite bucket;
    private LayerManager layerManager;

    public HelicopterCanvas() throws IOException {
        super(true);
        System.out.println("HelicopterCanvas width:" + getWidth() + "height:" + getHeight());
        mState = 0;
        mFrameDelay = 10;
        backgroundLayer = createBackground();

        helicopter = createHelicopter();
        fire = createFire();
        water = createWater();
        bucket = createBucket(helicopter, water);

        layerManager = new LayerManager();
        layerManager.append(water);
        layerManager.append(fire);
        layerManager.append(helicopter);
        layerManager.append(bucket);
        layerManager.append(backgroundLayer);
    }

    private TiledLayer createBackground() throws IOException {
        background = Image.createImage("/images/background.png");
        TiledLayer layer = new TiledLayer(2, 2, background, background.getWidth() / 2, background.getHeight() / 2);
        layer.setCell(0, 0, 1);
        layer.setCell(1, 0, 2);
        layer.setCell(0, 1, 3);
        layer.setCell(1, 1, 4);
        return layer;

    }

    private HelicopterSprite createHelicopter() throws IOException {
        Image tmpHelicopter = Image.createImage("/images/helicopter.png");
        return new HelicopterSprite(tmpHelicopter, tmpHelicopter.getWidth(), tmpHelicopter.getHeight());
    }

    private FireSprite createFire() throws IOException {
        Image tmpFire = Image.createImage("/images/fireall.png");
        return new FireSprite(tmpFire, 64, tmpFire.getHeight(), this);
    }

    private WaterSprite createWater() throws IOException {
        Image tmpWater = Image.createImage("/images/waterall.png");
        return new WaterSprite(tmpWater, 64, tmpWater.getHeight(), this);
    }

    private BucketSprite createBucket(HelicopterSprite helicopter, WaterSprite water) throws IOException {
        Image tmpBucket = Image.createImage("/images/bucketall.png");
        return new BucketSprite(tmpBucket, 16, tmpBucket.getHeight(), helicopter, this);
    }

    public void start() {
        mTrucking = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void stop() {
        mTrucking = false;
    }

    public void run() {
        Graphics g = getGraphics();

        while (mTrucking == true) {
            tick();
            input();
            render(g);
            try {
                Thread.sleep(mFrameDelay);
            } catch (InterruptedException ie) {
                stop();
            }
        }
    }

    private void tick() {
        helicopter.update();
        fire.update();
        bucket.update(helicopter);
        water.update();
    }

    private void input() {
        int keyStates = getKeyStates();
        if ((keyStates & RIGHT_PRESSED) != 0) {
            helicopter.gotoFire();
        }
        if ((keyStates & UP_PRESSED) != 0) {
            helicopter.gotoWater();
        }
        if ((keyStates & DOWN_PRESSED) != 0) {
            helicopter.gotoBase();
        }
    }

    public void signalEvent(GameEvent event) {
        System.err.println("event" + event.getName());
        if (event == GameEvent.dropWater) {
            water.drop();
            water.setRefPixelPosition(bucket.getX() + bucket.getWidth() / 2,
                    bucket.getY() + bucket.getHeight());
        }

        if (event == GameEvent.dropWaterEnded) {
            fire.reduceSize();
        }
        if (event == GameEvent.fireIsDead) {
            fire.setVisible(false);
        }
    }

    private void render(Graphics g) {
        int w = this.getWidth();
        int h = this.getHeight();

        //System.err.println("helicopter" + helicopter.getX() + " " + helicopter.getY());
        //System.err.println("w h" + w + " " + h);
        layerManager.setViewWindow(helicopter.getX() - w / 2,
                helicopter.getY() - h / 2,
                w,
                h);
        layerManager.paint(g, 0, 0);
        flushGraphics();
    }
}
