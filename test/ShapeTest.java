import animator.model.Ellipse;
import animator.model.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * the class represents the test for Rectangle and Ellipse
 * class.
 */
public class ShapeTest {

  // test for Rectangle class

  @Test
  public void getType1() {
    Rectangle rectangle = new Rectangle("R");
    assertEquals("rectangle", rectangle.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void rectangleConstructorExp1() {
    new Rectangle("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void rectangleConstructorExp2() {
    new Rectangle(null);
  }

  @Test
  public void getShapeOutput1() {
    Rectangle rectangle = new Rectangle("R");
    assertEquals("shape R rectangle", rectangle.getShapeOutput());
  }

  // test for Ellipse class

  @Test
  public void getType2() {
    Ellipse ellipse = new Ellipse("E");
    assertEquals("ellipse", ellipse.getType());
  }


  @Test(expected = IllegalArgumentException.class)
  public void ellipseConstructorExp1() {
    new Ellipse("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ellipseConstructorExp2() {
    new Ellipse(null);
  }

  @Test
  public void getShapeOutput2() {
    Ellipse ellipse = new Ellipse("E");
    assertEquals("shape E ellipse", ellipse.getShapeOutput());
  }
}
