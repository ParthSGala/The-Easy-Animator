package animator.model;

/**
 * the class represents the color of the shape.
 */
public class Position2D implements  IPosition2D {

  // x position
  private int x;
  // y position
  private int y;

  /**
   * the constructor constructs the position of the shape.
   *
   * @param x x position on the board
   * @param y y position on the board
   */
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}
