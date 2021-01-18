package animator.model;

/**
 * the class represents the ellipse shape.
 */
public class Ellipse extends AShape {

  /**
   * constructor to construct the ellipse.
   *
   * @param id         shape id
   * @param position2D shape position
   * @param width      shape width
   * @param height     shape height
   * @param color      shape color
   */
  public Ellipse(String id, Position2D position2D, int width, int height, Color color) {
    this.id = id;
    this.position2D = position2D;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * constructor to construct the ellipse.
   *
   * @param id shape id
   */
  public Ellipse(String id) {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("INVALID ELLIPSE");
    }
    this.id = id;
  }

  @Override
  public String getType() {
    return "ellipse";
  }

}
