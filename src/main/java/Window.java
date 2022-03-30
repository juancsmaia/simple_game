import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Window extends Canvas implements Serializable {

  private static final long serialVersionUID = -5641828237808928700L;

  public Window(int width, int heigth, String title, Game game) {

    JFrame frame = new JFrame(title);

    frame.setPreferredSize(new Dimension(width, heigth));
    frame.setMaximumSize(new Dimension(width, heigth));
    frame.setMinimumSize(new Dimension(width, heigth));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(game);
    frame.setVisible(true);
    game.start();
  }
}
