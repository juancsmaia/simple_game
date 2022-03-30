import com.github.javafaker.Faker;

import java.awt.*;
import java.util.Random;

public class Spawn {

  private Handler handler;
  private HUD hud;
  private Random r = new Random();
  private Faker faker = new Faker();

  private int scoreKeep;

  public Spawn(Handler handler, HUD hud) {
    this.handler = handler;
    this.hud = hud;
  }

  public void tick() {
    scoreKeep++;
    if (scoreKeep == 500) {
      new Life(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.LIFE, handler);
    }

    if (scoreKeep > 99) {
      hud.setLevel(hud.getLevel() + 1);

      int enemies = faker.number().numberBetween(1, 20);
      for (int i = 0; i < enemies; i++) {
        int spawnPoint = faker.number().numberBetween(-10, 10);
        new BasicEnemy(
            Game.WIDTH + (spawnPoint), Game.HEIGHT + (spawnPoint), Color.WHITE, 1, 1, ID.SMART_ENEMY, handler);
      }

      scoreKeep = 0;
    }
  }
}
