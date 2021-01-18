package animator.model;

/**
 * the class represents the motion of the shapes
 * between two time intervals.
 */
public class Motion implements IMotion {

  // starting and ending height and widths of the shape
  private int startWidthShape;
  private int endWidthShape;
  private int startHeightShape;
  private int endHeightShape;

  // starting and ending position of the shape
  private Position2D startPositionShape;
  private Position2D endPositionShape;

  // starting and ending color of the shape
  private Color startColorShape;
  private Color endColorShape;

  //starting and ending time of the shape
  private int startTimeShape;
  private int endTimeShape;

  // unique shape id
  private String shapeID;


  /**
   *  constructor to construct the motion.
   *
   * @param name      name of shape
   * @param x1        position x
   * @param y1        position y
   * @param w1        width 1
   * @param h1        heigth 1
   * @param r1        red color 1
   * @param g1        green color 1
   * @param b1        blue color 1
   * @param x2        position x 2
   * @param y2        position y 2
   * @param w2        width 2
   * @param h2        heigth 2
   * @param r2        red color 2
   * @param g2        green color 2
   * @param b2        blue color 2
   * @param startTime start time of motion
   * @param endTime   end time of motion
   */
  public Motion(String name, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int x2,
                int y2, int w2, int h2, int r2, int g2, int b2, int startTime, int endTime) {
    this.shapeID = name;

    this.startPositionShape = new Position2D(x1, y1);
    this.endPositionShape = new Position2D(x2, y2);

    this.startWidthShape = w1;
    this.endWidthShape = w2;

    this.startHeightShape = h1;
    this.endHeightShape = h2;

    this.startColorShape = new Color(r1, g1, b1);
    this.endColorShape = new Color(r2, g2, b2);

    if (startTime < 0 || endTime < startTime) {
      throw new IllegalArgumentException("INVALID TIME");
    }
    this.startTimeShape = startTime;
    this.endTimeShape = endTime;
  }

  public int getStartWidthShape() {
    return startWidthShape;
  }

  public int getEndWidthShape() {
    return endWidthShape;
  }

  public int getStartHeightShape() {
    return startHeightShape;
  }

  public int getEndHeightShape() {
    return endHeightShape;
  }

  public Position2D getStartPositionShape() {
    return startPositionShape;
  }

  public Position2D getEndPositionShape() {
    return endPositionShape;
  }

  public Color getStartColorShape() {
    return startColorShape;
  }

  public Color getEndColorShape() {
    return endColorShape;
  }

  public int getStartTimeShape() {
    return startTimeShape;
  }

  public int getEndTimeShape() {
    return endTimeShape;
  }

  public String getShapeID() {
    return shapeID;
  }

  /**
   * the method gets the start details of shape motion needed for the animation.
   *
   * @return string of start shape
   */
  private String startShapeState() {
    return this.startTimeShape + " " + this.startPositionShape.getX() + " "
        + this.startPositionShape.getY()
        + " " + this.startWidthShape + " " + this.startHeightShape + " "
        + this.startColorShape.getRed()
        + " " + this.startColorShape.getGreen() + " "
        + this.startColorShape.getBlue();
  }

  /**
   * the method gets the end details of shape motion needed for the animation.
   *
   * @return string of end shape
   */
  private String endShapeState() {
    return this.endTimeShape + " " + this.endPositionShape.getX() + " "
        + this.endPositionShape.getY()
        + " " + this.endWidthShape + " " + this.endHeightShape + " "
        + this.endColorShape.getRed()
        + " " + this.endColorShape.getGreen() + " "
        + this.endColorShape.getBlue();
  }

  /**
   * the method gets the start and end details of shape motion needed for the animation.
   *
   * @return string of motion of the shape
   */
  public String getMotionShape() {
    return "motion " + this.shapeID + " " + this.startShapeState()
        + " " + this.endShapeState();
  }
}
