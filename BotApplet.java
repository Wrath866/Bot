import java.applet.Applet;
import java.awt.*;

public class BotApplet extends Applet {
    public Graphics getGraphics() {
        Graphics g = super.getGraphics();
        g.setColor(Color.RED);
        g.drawString("Some string here!", 100, 100);
        return g;
    }
}
