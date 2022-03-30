import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

  private final int speed = 5;
  private boolean uP = false;
  private boolean dP = false;
  private boolean lP = false;
  private boolean rP = false;
  private Handler handler;

  public KeyInput(Handler handler) {
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    handler.objects.stream().filter(o -> ID.PLAYER.equals(o.id)).forEach(o -> movePlayer(o, key));
    if (key == KeyEvent.VK_ESCAPE) System.exit(1);
  }

  private void movePlayer(GameObject object, int key) {
    switch (key) {
      case KeyEvent.VK_W:
        uP = true;
        object.setVelY(-speed);
        break;
      case KeyEvent.VK_S:
        dP = true;
        object.setVelY(speed);
        break;
      case KeyEvent.VK_A:
        lP = true;
        object.setVelX(-speed);
        break;
      case KeyEvent.VK_D:
        rP = true;
        object.setVelX(speed);
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    handler.objects.forEach(
        o -> {
          if (o.id == ID.PLAYER) {
            stopPlayer(o, key);
          }
        });
  }

  private void stopPlayer(GameObject object, int key) {
    switch (key) {
      case KeyEvent.VK_W:
        uP = false;
        if (dP) {
          object.setVelY(speed);
        } else {
          object.setVelY(0);
        }
        break;
      case KeyEvent.VK_S:
        dP = false;
        if (uP) {
          object.setVelY(-speed);
        } else {
          object.setVelY(0);
        }
        break;
      case KeyEvent.VK_A:
        lP = false;
        if (rP) {
          object.setVelX(speed);
        } else {
          object.setVelX(0);
        }
        break;
      case KeyEvent.VK_D:
        rP = false;
        if (lP) {
          object.setVelX(-speed);
        } else {
          object.setVelX(0);
        }
        break;
    }
  }
}
