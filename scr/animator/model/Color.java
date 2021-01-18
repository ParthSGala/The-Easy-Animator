package animator.model;

/**
 * the class represents the color of the shape.
 */

public class Color extends java.awt.Color implements IColor {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * the constructor constructs the color of the shape.
   *
   * @param red   red value of the color
   * @param green green value of the color
   * @param blue  blue value of the color
   */
  public Color(int red, int green, int blue) {
    super(red, green, blue);

    if ((red < 0 || red > 255) || (green < 0 || green > 255) || (blue < 0 || blue > 255)) {
      throw new IllegalArgumentException("INVALID COLOR");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

}
