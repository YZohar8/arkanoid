
import Game.animtion.SunImage;
import Game.interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI("a", 800, 600);
        Sprite s = new SunImage();
            DrawSurface d = gui.getDrawSurface();
            s.drawOn(d);
            gui.show(d);
    }
}