import java.awt.*;

public class EnemyBoss extends GameObject {

  private final Color color;

  public EnemyBoss(float x, float y, Color color, int velX, int velY, ID id, Handler handler) {
    super(x, y, id, handler);
    this.velX = velX;
    this.velY = velY;
    this.color = color;
  }

  public void tick() {
    x += velX;
    y += velY;

    if (y <= 0 || y >= Game.HEIGHT - 127) velY *= -1;
    if (x <= 0 || x >= Game.WIDTH - 115) velX *= -1;
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, 96, 96);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 96, 96);
  }
}
