import java.awt.*;

public class Life extends GameObject {

  public Life(float x, float y, ID id, Handler handler) {
    super(x, y, id, handler);
  }

  public void tick() {
    velX = 0;
    velY = 0;
  }

  public void render(Graphics g) {
    g.setColor(Color.MAGENTA);
    g.fillRect((int) x, (int) y, 10, 10);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 10, 10);
  }

  public void rebound() {}
}
