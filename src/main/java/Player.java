import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Player extends GameObject {

  public Player(float x, float y, ID id, Handler handler) {
    super(x, y, id, handler);
  }

  public void tick() {
    x += velX;
    y += velY;

    x = Game.clamp((int) x, 0, Game.WIDTH - 48);
    y = Game.clamp((int) y, 0, Game.HEIGHT - 70);

    collision();
  }

  private void collision() {
    handler.objects.stream()
        .filter(o -> !ID.TRAIL.equals(o.id))
        .forEach(
            o -> {
              if (getBounds().intersects(o.getBounds())) {
                collisionEvent(o);
              }
            });
  }

  private void collisionEvent(GameObject object) {
    switch (object.id) {
      case PLAYER:
        break;
      case BASIC_ENEMY:
        HUD.HEALTH--;
        BasicEnemy b = (BasicEnemy) object;
        b.rebound();
        break;
      case SMART_ENEMY:
        HUD.HEALTH--;
        break;
      case BOSS_ENEMY:
        HUD.HEALTH -= 5;
        break;
      case LIFE:
        HUD.HEALTH++;
        object.setRemoved(true);
        break;
      case TRAIL:
        break;
    }
  }

  public void render(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect((int) x, (int) y, 32, 32);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 32, 32);
  }
}
