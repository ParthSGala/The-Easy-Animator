package animator.model;

/**
 * the interface Shape represent change of the shape.
 */
public interface IShape {
  /**
   * the method gets the id of the shape.
   *
   * @return the string which is the id of shape.
   */
  public String getID();

  /**
   * the method gets the type of the shape.
   *
   * @return the string which is the type of shape.
   */
  public String getType();

  /**
   * the method gets the position of the shape.
   *
   * @return the positon2D which is the position of shape.
   */
  public Position2D getPosition2D();

  /**
   * the method gets the color of the shape.
   *
   * @return the color which is the RGB value of shape.
   */
  public Color getColor();

  /**
   * the method gets the string summary type of the shape.
   *
   * @return the string which is the summary of shape.
   */
  public String getShapeOutput();

  /**
   * the method gets the width of the shape.
   */
  public int getWidth();

  /**
   * the method gets the height of the shape.
   */
  public int getHeight();

}
