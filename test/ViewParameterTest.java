import animator.model.Motion;
import animator.view.ShapeTween;
import animator.view.ViewFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * the class represents the test for view parameters such as shape tween, view factory.
 */
public class ViewParameterTest {

  // test for ShapeTween class

  @Test
  public void shapePosition2D() {
    Motion motion1 = new Motion("E", 13, 12, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    ShapeTween shapeTween = new ShapeTween(motion1,13);
    assertEquals(11, shapeTween.shapePosition2D().getX());
    assertEquals(10, shapeTween.shapePosition2D().getY());
  }

  @Test
  public void shapeColor() {
    Motion motion1 = new Motion("E", 13, 12, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, 75);
    ShapeTween shapeTween = new ShapeTween(motion1,13);
    assertEquals(200, shapeTween.shapeColor().getRed());
    assertEquals(200, shapeTween.shapeColor().getGreen());
    assertEquals(200, shapeTween.shapeColor().getBlue());
  }

  @Test
  public void shapeHeight() {
    Motion motion1 = new Motion("E", 13, 12, 1, 132, 100, 100, 100,
        2, 2, 2, 22, 200, 200, 200, 1, 75);
    ShapeTween shapeTween = new ShapeTween(motion1,13);
    assertEquals(114, shapeTween.shapeHeight());
  }

  @Test
  public void shapeWidth() {
    Motion motion1 = new Motion("E", 13, 12, 15, 1, 100, 100, 100,
        2, 2, 243, 2, 200, 200, 200, 1, 75);
    ShapeTween shapeTween = new ShapeTween(motion1,13);
    assertEquals(51, shapeTween.shapeWidth());
  }

  // test for view factory exception

  @Test(expected = IllegalArgumentException.class)
  public void viewFactoryExcep() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("ea", new StringBuffer("lp"));
  }


}
