import java.awt.*;

public class Trail extends GameObject {

  private final Color color;
  private final int width, height;
  private final float life;
  private float alpha = 1;

  public Trail(
      float x, float y, ID id, int width, int height, float life, Color color, Handler handler) {
    super(x, y, id, handler);
    this.color = color;
    this.width = width;
    this.height = height;
    this.life = life;
  }

  public void tick() {
    if (alpha > life) {
      alpha -= (life - 0.0001f);
    } else {
      this.removed = true;
    }
  }

  public void render(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(makeTransparent(alpha));
    g.setColor(color);
    g.fillRect((int) x, (int) y, width, height);
    g2d.setComposite(makeTransparent(1));
  }

  private AlphaComposite makeTransparent(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return (AlphaComposite.getInstance(type, alpha));
  }

  public Rectangle getBounds() {
    return null;
  }
}
