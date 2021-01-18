package animator.view;

import animator.model.IShape;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JFrame;

/**
 * to show the visualization of the shapes animation.
 */
public class VisualView extends AbstactAnimationView {
  JScrollPane jScrollPane;
  Panel panel;

  /**
   * Constructs a new visual view given a Readable object.
   */
  public VisualView() {
    super();
    panel = new Panel();
    jScrollPane = new JScrollPane(panel);
  }

  @Override
  public void build(int x, int y, int width, int height) {
    setSize(800, 800);
    panel.setMinimumSize(new Dimension(width, height));
    panel.setPreferredSize(new Dimension(width * 2, height * 2));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(x, y);
    add(jScrollPane);
    setVisible(true);
  }

  @Override
  public void render(List<IShape> shapes) {
    panel.draw(shapes);
  }


  @Override
  public String getViewType() {
    return "visual";
  }
}
