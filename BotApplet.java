import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BotApplet extends Applet {
    @Override
    public Graphics getGraphics() {
        //Initialize TWO images..
        BufferedImage debugImage = null;
        if (debugImage == null || debugImage.getWidth() != getWidth() || debugImage.getHeight() != getHeight()) {
            debugImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        BufferedImage gameImage = null;
        if (gameImage == null || gameImage.getWidth() != getWidth() || gameImage.getHeight() != getHeight()) {
            gameImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        //Get the graphics for the DEBUG image..
        Graphics g = debugImage.getGraphics();

        //Draw gameImage on top of the DEBUG image..
        g.drawImage(gameImage, 0, 0, null);

        //Draw your own stuff on top of the DEBUG image..
        g.drawString("Hello World", 50, 50);

        //Draw debug on top of the original graphics..
        g.dispose();
        Graphics G = super.getGraphics();
        G.drawImage(debugImage, 0, 0, null);
        return gameImage.getGraphics();
    }
}
