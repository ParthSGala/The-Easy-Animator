package animator.view;

import animator.model.IShape;

import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 * the class represents the extension of JPanel and overriding its
 * method in order to composite the visual view of the
 * animation.
 */
public class Panel extends JPanel {
  // declare the i shape list
  List<IShape> shapeList = null;


  /**
   * the constructor constructs the panel.
   */
  public Panel() {
    super();
  }

  /**
   * the method paints the shape.
   *
   * @param g graphics to represent the shape.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.shapeList != null) {
      for (IShape shape : this.shapeList) {
        g.setColor(shape.getColor());
        switch (shape.getType()) {
          case "rectangle":
            g.fillRect(shape.getPosition2D().getX(),
                shape.getPosition2D().getY(),
                shape.getWidth(),
                shape.getHeight());
            break;
          case "ellipse":
            g.fillOval(shape.getPosition2D().getX(),
                shape.getPosition2D().getY(),
                shape.getWidth(),
                shape.getHeight());
            break;
          default:
            break;
        }
      }

    }
  }

  /**
   * the method draws using repaint.
   *
   * @param shapesList shapes list
   */
  public void draw(List<IShape> shapesList) {
    this.shapeList = shapesList;
    repaint();
  }

}
