import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.util.Random;

public class Game extends Canvas implements Runnable, Serializable {

  private static final long serialVersionUID = 190065682132863975L;
  private final Handler handler = new Handler();
  private final HUD hud = new HUD();
  private final Spawn spawner = new Spawn(handler, hud);

  private Thread thread;
  private boolean running = false;
  private Random r = new Random();

  public Game() {
    this.addKeyListener(new KeyInput(handler));
    new Window(WIDTH, HEIGHT, "Jogo do Juan", this);
    new Player(WIDTH / 2.0f - 32, HEIGHT / 2.0f - 32, ID.PLAYER, handler);
  }

  public static void main(String[] args) {
    new Game();
  }

  public static float clamp(float var, float min, float max) {
    if (var >= max) {
      return max;
    } else if (var <= min) {
      return min;
    }
    return var;
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.setName("Game");
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta > 1) {
        tick();
        delta--;
      }
      if (running) {
        render();
      }
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    handler.render(g);
    hud.render(g);

    bs.show();
  }

  private void tick() {
    handler.tick();
    hud.tick();
    spawner.tick();
  }

  public static final int WIDTH = 960, HEIGHT = WIDTH / 12 * 9;
}
