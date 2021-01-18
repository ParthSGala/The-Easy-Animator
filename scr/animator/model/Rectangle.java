package animator.model;

/**
 * the class represents Rectangle shape.
 */
public class Rectangle extends AShape {

  /**
   * constructor to construct the rectangle.
   *
   * @param id         shape id
   * @param position2D shape position
   * @param width      shape width
   * @param height     shape height
   * @param color      shape color
   */
  public Rectangle(String id, Position2D position2D, int width, int height, Color color) {
    this.id = id;
    this.position2D = position2D;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * constructor to construct the rectangle.
   *
   * @param id shape id
   */
  public Rectangle(String id) {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("INVALID RECTANGLE");
    }
    this.id = id;
  }

  @Override
  public String getType() {
    return "rectangle";
  }
}
