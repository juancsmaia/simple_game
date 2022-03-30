import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class BasicEnemy extends GameObject {

  private float speed;
  private Color color;
  private GameObject player;

  public BasicEnemy(float x, float y, Color color, int velX, int velY, ID id, Handler handler) {
    super(x, y, id, handler);

    if (ID.SMART_ENEMY.equals(id)) {
      handler.objects.forEach(
          object -> {
            if (ID.PLAYER.equals(object.id)) {
              player = object;
            }
          });
    }

    this.velX = velX;
    this.velY = velY;
    this.color = color;
  }

  public void tick() {
    x += velX;
    y += velY;

    if (ID.SMART_ENEMY.equals(id)) {
      float diffX = x - player.getX() - 16;
      float diffY = y - player.getY() - 16;
      float distance =
          (float)
              Math.sqrt(
                  (x - player.getX()) * (x - player.getX())
                      + (y - player.getY()) * (y - player.getY()));
      velX = (float) ((-1.0 / distance) * diffX);
      velY = (float) ((-1.0 / distance) * diffY);
    }

//    if (y <= 0 || y >= Game.HEIGHT - 49) {
//      velY = -velY;
//    }
//
//    if (x <= 0 || x >= Game.WIDTH - 35) {
//      velX = -velX;
//    }

    new Trail(x, y, ID.TRAIL, 16, 16, 0.05f, color, handler);
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, 16, 16);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 16, 16);
  }

  public void rebound() {
    velY = -velY;
    velX = -velX;
  }
}
