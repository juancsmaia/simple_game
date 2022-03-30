import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class GameObject {

  protected float x, y;
  protected ID id;
  protected float velX, velY;
  protected Handler handler;
  protected boolean removed;

  public GameObject(float x, float y, ID id, Handler handler) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
    handler.addObject(this);
  }

  public abstract void tick();
  public abstract void render(Graphics g);
  public abstract Rectangle getBounds();
}
