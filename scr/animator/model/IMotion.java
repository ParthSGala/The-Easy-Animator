package animator.model;

/**
 * The interface represents motion of the shapes between two time intervals or
 * at the given time.
 */
public interface IMotion {
  /**
   * the method gets the string id of the shapes.
   *
   * @return the string id of shape.
   */
  public String getShapeID();

  /**
   * the method gets the start time of shape motion needed for the animation.
   *
   * @return start time of motion.
   */
  public int getStartTimeShape();

  /**
   * the method gets the end time of shape motion needed for the animation.
   *
   * @return end time of motion.
   */
  public int getEndTimeShape();

  /**
   * the method gets the start position of shape motion needed for the animation.
   *
   * @return end time of motion.
   */
  public Position2D getStartPositionShape();

  /**
   * the method gets the end position of shape motion needed for the animation.
   *
   * @return end time of motion.
   */
  public Position2D getEndPositionShape();


  /**
   * the method gets the start color of shape motion needed for the animation.
   *
   * @return end time of motion.
   */
  public Color getStartColorShape();


  /**
   * the method gets the end color of shape motion needed for the animation.
   *
   * @return end time of motion.
   */
  public Color getEndColorShape();

  /**
   * the method gets the start width of shape motion needed for the animation.
   *
   * @return start shape width of motion.
   */
  public int getStartWidthShape();

  /**
   * the method gets the end width of shape motion needed for the animation.
   *
   * @return end shape width of motion.
   */
  public int getEndWidthShape();

  /**
   * the method gets the start height of shape motion needed for the animation.
   *
   * @return end shape height of motion.
   */
  public int getStartHeightShape();

  /**
   * the method gets the end height of shape motion needed for the animation.
   *
   * @return end shape height of motion.
   */
  public int getEndHeightShape();

}
