package animator.model;

/**
 * the class represents a factory design pattern for creating a IShape.
 */
public class ShapeFactory {

  /**
   * to create the shape required based on the shape type.
   *
   * @param type       shape type
   * @param name       shape name
   * @param position2D shape position
   * @param width      shape width
   * @param height     shape height
   * @param color      shape color
   * @return shape depending on the
   */

  public static IShape produce(String type, String name, Position2D position2D,
                               int width, int height, Color color) {
    switch (type) {
      case "rectangle":
        return new Rectangle(name, position2D, width, height, color);
      case "ellipse":
        return new Ellipse(name, position2D, width, height, color);
      default:
        throw new IllegalArgumentException("INVALID SHAPE");
    }
  }
}
