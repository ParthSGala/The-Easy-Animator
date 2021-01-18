package animator.model;

/**
 * the class represents key frame needed for the visual animation.
 */
public class KeyFrame {
  private String id;
  private Position2D position2D;
  private Color color;
  private int tick;
  private int width;
  private int height;

  /**
   * constructor to construct the keyFrame.
   *
   * @param tick       smallest unit of time
   * @param id         shape id
   * @param position2D shape position
   * @param width      shape width
   * @param height     shape height
   * @param color      shape color
   */
  public KeyFrame(int tick, String id, Position2D position2D, int width, int height, Color color) {

    if (tick < 0) {
      throw new IllegalArgumentException("INVALID INPUT TICK");
    }

    this.tick = tick;
    this.id = id;
    this.position2D = position2D;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  public String getShapeID() {
    return this.id;
  }

  public int getTick() {
    return this.tick;
  }

  public Position2D getPosition2D() {
    return this.position2D;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public Color getColor() {
    return this.color;
  }

}
