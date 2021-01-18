import animator.model.Color;
import animator.model.KeyFrame;
import animator.model.Motion;
import animator.model.Position2D;
import animator.model.ShapeFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * the class represents the test for the Shape parameters
 * such as Color, Position, Motion, KeyFrame, ShapeFactory.
 */
public class ShapeParameterTest {

  // Test for Color class

  // test for constructor exceptions

  // r,b,g > 255
  @Test(expected = IllegalArgumentException.class)
  public void colorconstructorexp1() {
    new Color(300, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorconstructorexp2() {
    new Color(0, 300, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorConstructorexp3() {
    new Color(0, 0, 300);
  }

  // negative color
  @Test(expected = IllegalArgumentException.class)
  public void colorConstructorexp4() {
    new Color(-2, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorConstructorexp5() {
    new Color(0, -2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorConstructorexp6() {
    new Color(0, 0, -2);
  }

  @Test
  public void getRed() {
    Color c1 = new Color(255, 0, 0);
    assertEquals(255, c1.getRed());
  }

  @Test
  public void getGreen() {
    Color c1 = new Color(0, 255, 0);
    assertEquals(255, c1.getGreen());
  }

  @Test
  public void getBlue() {
    Color c1 = new Color(0, 0, 255);
    assertEquals(255, c1.getBlue());
  }

  // Test for Position2D class

  @Test
  public void getX() {
    Position2D p1 = new Position2D(-100, 100);
    Position2D p2 = new Position2D(76, 4322);
    assertEquals(-100, p1.getX());
    assertEquals(76, p2.getX());
  }

  @Test
  public void getY() {
    Position2D p1 = new Position2D(-100, 100);
    Position2D p2 = new Position2D(76, 4322);

    assertEquals(100, p1.getY());
    assertEquals(4322, p2.getY());
  }

  // Test for Motion Class

  // motion constructor exception test

  // start time < 0
  @Test(expected = IllegalArgumentException.class)
  public void motionConstructorExp1() {
    new Motion("R", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, -55, 8);
  }

  // end time < start time
  @Test(expected = IllegalArgumentException.class)
  public void motionConstructorExp2() {
    new Motion("R", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 10, 5);
  }

  // end time < 0
  @Test(expected = IllegalArgumentException.class)
  public void motionConstructorExp4() {
    new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 5, -5);
  }

  // end time = 0
  @Test(expected = IllegalArgumentException.class)
  public void motionConstructorExp5() {
    new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 5, 0);
  }

  @Test
  public void getStartWidthShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(1, motion.getStartWidthShape());
  }

  @Test
  public void getEndWidthShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(2, motion.getEndWidthShape());
  }

  @Test
  public void getStartHeightShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(1, motion.getStartHeightShape());
  }

  @Test
  public void getEndHeightShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(2, motion.getEndHeightShape());
  }

  @Test
  public void getStartColorShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(new Color(100, 100, 100),
        motion.getStartColorShape());
  }

  @Test
  public void getEndColorShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(new Color(200, 200, 200),
        motion.getEndColorShape());
  }

  @Test
  public void getStartTimeShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(1, motion.getStartTimeShape());
  }

  @Test
  public void getEndTimeShape() {
    Motion motion = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals(75, motion.getEndTimeShape());
  }

  @Test
  public void getShapeID() {
    Motion motion = new Motion("R", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals("R", motion.getShapeID());
  }


  @Test
  public void getMotionShape() {
    Motion motion1 = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    Motion motion2 = new Motion("R", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    assertEquals("motion E 1 1 1 1 1 100 100 100 75 2 2 2 2 200 200 200",
        motion1.getMotionShape());
    assertEquals("motion R 1 1 1 1 1 100 100 100 75 2 2 2 2 200 200 200",
        motion2.getMotionShape());
  }

  // Test for Shape Factory class

  @Test(expected = IllegalArgumentException.class)
  public void produceExp1() {
    ShapeFactory shapeFactory = new ShapeFactory();
    Position2D position2D = new Position2D(32, 234);
    Color color = new Color(11, 11, 11);
    shapeFactory.produce("Pentagon", "X", position2D,
        11, 22, color);

  }

  @Test(expected = IllegalArgumentException.class)
  public void produceExp2() {
    ShapeFactory shapeFactory = new ShapeFactory();
    Position2D position2D = new Position2D(32, 234);
    Color color = new Color(11, 11, 11);
    shapeFactory.produce("rider", "X", position2D,
        11, 22, color);
  }

  // Test for Keyframe class

  @Test(expected = IllegalArgumentException.class)
  public void keyFrameConstructorExp() {
    new KeyFrame(-78, "cdiw", new Position2D(10, 10),
        1, 1,
        new Color(1, 1, 10));
  }

  @Test
  public void getShapeIDkeyFrame() {
    Position2D position2D = new Position2D(25, 98);
    Color color = new Color(19, 19, 19);
    KeyFrame keyFrame = new KeyFrame(72, "keyFrame", position2D,
        56, 77, new Color(200, 240, 100));
    assertEquals("keyFrame", (keyFrame.getShapeID()));

  }

  @Test
  public void getTickKeyFrame() {
    Position2D position2D = new Position2D(25, 98);
    Color color = new Color(190, 191, 192);
    KeyFrame keyFrame = new KeyFrame(72, "keyFrame", position2D,
        56, 78, color);
    assertEquals(72, keyFrame.getTick());
  }

  @Test
  public void getWidthKeyFrame() {
    Position2D position2D = new Position2D(25, 98);
    Color color = new Color(190, 191, 192);
    KeyFrame keyFrame = new KeyFrame(72, "keyFrame", position2D,
        45, 34, color);
    assertEquals(45, keyFrame.getWidth());
  }

  @Test
  public void getHeightKeyFrame() {
    Position2D position2D = new Position2D(25, 98);
    Color color = new Color(190, 191, 192);
    KeyFrame keyFrame = new KeyFrame(72, "keyFrame", position2D,
        45, 34, color);
    assertEquals(34, keyFrame.getHeight());
  }

  @Test
  public void getColorKeyFrame() {
    Position2D position2D = new Position2D(25, 98);
    Color color = new Color(190, 191, 192);
    KeyFrame keyFrame = new KeyFrame(72, "keyFrame", position2D,
        3, 2, color);
    assertEquals(190, keyFrame.getColor().getRed());
    assertEquals(191, keyFrame.getColor().getGreen());
    assertEquals(192, keyFrame.getColor().getBlue());
  }
}
