import animator.model.AnimationModel;
import animator.model.IAnimationModel;
import animator.model.Motion;
import animator.model.Rectangle;
import animator.model.IShape;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * the class represents the test for animation model.
 */
public class AnimationModelTest {

  @Test
  public void addShape1() {
    IAnimationModel animationModel = new AnimationModel();
    animationModel.addShape(new Rectangle("R"));
    assertEquals("shape R rectangle", animationModel.getStateShape());
  }

  @Test(expected = IllegalArgumentException.class)
  public void  addShapeExp1() {
    IAnimationModel animationModel = new AnimationModel();
    animationModel.addShape(null);
  }


  @Test
  public void getShapesList1() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    animationModel.addShape(rectangle);

    String str = "";
    for (IShape shape : animationModel.getShapeList()) {
      str = str.concat(shape.getShapeOutput() + "\n");
    }
    assertEquals("shape R rectangle\n", str);
  }

  @Test
  public void getStateShape1() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    animationModel.addShape(rectangle);
    assertEquals("shape R rectangle", animationModel.getStateShape());
  }

  @Test
  public void getItem() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    animationModel.addShape(rectangle);
    assertEquals(animationModel.getShapeList().get(0), animationModel.getItem("R"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getItemExp() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    animationModel.getItem(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionExp1() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    Motion motion1 = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, -1, 75);
    animationModel.addMotion(motion1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionExp2() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    Motion motion1 = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, -75);
    animationModel.addMotion(motion1);
  }

  // overlap motions exception
  @Test(expected = IllegalArgumentException.class)
  public void addMotionExp3() {
    IAnimationModel animationModel = new AnimationModel();
    Rectangle rectangle = new Rectangle("R");
    Motion motion1 = new Motion("E", 1, 1, 1, 1, 100, 100, 100,
        2, 2, 2, 2, 200, 200, 200, 1, -75);
    animationModel.addMotion(motion1);
    animationModel.addMotion(motion1);
  }
}
