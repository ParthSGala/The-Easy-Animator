package animator.view;

import animator.model.Color;
import animator.model.KeyFrame;
import animator.model.Motion;
import animator.model.Position2D;

/**
 * the class implements the formula to calculate
 * the new numbers for visualizing the shapes that is
 * in between animation.
 */
public class ShapeTween {
  // tick of the animation
  private int tick;
  private int tickstart;
  private int tickend;

  // start and end position of shape
  private int startShapeX;
  private int endShapeX;
  private int startShapeY;
  private int endShapeY;

  // start and end dimension of shape
  private int startShapeWidth;
  private int endShapeWidth;
  private int startShapeHeight;
  private int endShapeHeight;

  // start and end color of shape
  private int startShapeRed;
  private int endShapeRed;
  private int startShapeGreen;
  private int endShapeGreen;
  private int startShapeBlue;
  private int endShapeBlue;

  // start and end tweeting of shape
  private double num1;
  private double num2;

  /**
   * the constructor constructs shape tweening shape based on
   * motion and tick.
   *
   * @param motion motion of the shape
   * @param tick   smallest unit of time
   */
  public ShapeTween(Motion motion, int tick) {
    this.tick = tick;

    this.tickstart = motion.getStartTimeShape();
    this.tickend = motion.getEndTimeShape();

    // position of shape
    this.startShapeX = motion.getStartPositionShape().getX();
    this.endShapeX = motion.getEndPositionShape().getX();
    this.startShapeY = motion.getStartPositionShape().getY();
    this.endShapeY = motion.getEndPositionShape().getY();

    // dimension of shape
    this.startShapeWidth = motion.getStartWidthShape();
    this.endShapeWidth = motion.getEndWidthShape();
    this.startShapeHeight = motion.getStartHeightShape();
    this.endShapeHeight = motion.getEndHeightShape();

    // color of shape
    this.startShapeRed = motion.getStartColorShape().getRed();
    this.endShapeRed = motion.getEndColorShape().getRed();
    this.startShapeGreen = motion.getStartColorShape().getGreen();
    this.endShapeGreen = motion.getEndColorShape().getGreen();
    this.startShapeBlue = motion.getStartColorShape().getBlue();
    this.endShapeBlue = motion.getEndColorShape().getBlue();
  }


  /**
   * the constructor constructs shape tweening shape based on
   * keyframes and tick.
   * @param f1 first keyframe
   * @param f2 second keyframe
   * @param tick time of keyframe
   */
  public ShapeTween(KeyFrame f1, KeyFrame f2, int tick) {
    this.tick = tick;

    this.tickstart = f1.getTick();
    this.tickend = f2.getTick();

    this.startShapeX = f1.getPosition2D().getX();
    this.endShapeX = f2.getPosition2D().getX();

    this.startShapeY = f1.getPosition2D().getY();
    this.endShapeY = f2.getPosition2D().getY();

    this.startShapeWidth = f1.getWidth();
    this.endShapeWidth = f2.getWidth();

    this.startShapeHeight = f1.getHeight();
    this.endShapeHeight = f2.getHeight();

    this.startShapeRed = f1.getColor().getRed();
    this.endShapeRed = f2.getColor().getRed();

    this.startShapeGreen = f1.getColor().getGreen();
    this.endShapeGreen = f2.getColor().getGreen();

    this.startShapeBlue = f1.getColor().getBlue();
    this.endShapeBlue = f2.getColor().getBlue();
  }

  private void calculationAnimation() {
    if (tickend - tickstart != 0) {
      num1 = ((double) (tickend - tick))
          / (tickend - tickstart);
      num2 = ((double) (tick - tickstart))
          / (tickend - tickstart);
    }
  }

  /**
   * the method calculates the color.
   */
  public Color shapeColor() {
    int red;
    int green;
    int blue;

    if (tickend - tickend == 0) {
      red = this.endShapeRed;
      green = this.endShapeGreen;
      blue = this.endShapeBlue;
    } else {
      calculationAnimation();
      red = (int) (this.startShapeRed * num1
          + this.endShapeRed * num2);
      green = (int) (this.startShapeGreen * num1
          + this.endShapeGreen * num2);
      blue = (int) (this.startShapeBlue * num1
          + this.endShapeBlue * num2);
    }

    return new Color(red, green, blue);

  }

  /**
   * the method calculates the width.
   */
  public int shapeWidth() {
    int width;

    if (tickend - tickstart == 0) {
      width = this.endShapeWidth;
      calculationAnimation();
    } else {
      calculationAnimation();
      width = (int) (this.startShapeWidth * num1
          + this.endShapeWidth * num2);
    }

    return width;
  }

  /**
   * the method calculates the height.
   */
  public int shapeHeight() {
    int height;

    if (tickend - tickstart == 0) {
      height = this.endShapeHeight;
    } else {
      calculationAnimation();
      height = (int) (this.startShapeHeight * num1 + this.endShapeHeight * num2);
    }
    return height;
  }

  /**
   * the method calculates the position2D.
   */
  public Position2D shapePosition2D() {
    int x;
    int y;

    if (tickend - tickstart == 0) {
      x = this.endShapeX;
      y = this.endShapeY;
    } else {
      calculationAnimation();
      x = (int) (this.startShapeX * num1
          + this.endShapeX * num2);
      y = (int) (this.startShapeY * num1
          + this.endShapeY * num2);
    }

    return new Position2D(x, y);
  }
}
