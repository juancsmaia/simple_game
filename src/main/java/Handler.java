import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {

  LinkedList<GameObject> objects = new LinkedList<GameObject>();

  public void tick() {
    objects.removeIf(object -> object.removed);
    for (int i = objects.size() - 1; i >= 0; i--) {
      objects.get(i).tick();
    }
  }

  public void render(Graphics g) {
    objects.forEach(o -> o.render(g));
  }

  public void addObject(GameObject object) {
    this.objects.add(object);
  }
}
