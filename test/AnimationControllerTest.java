import animator.controller.AnimationController;
import animator.view.IAnimationView;
import animator.view.TextView;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * the class represents the test for animation controller class.
 */
public class AnimationControllerTest {

  // test for null controller
  @Test(expected = IllegalArgumentException.class)
  public void animationControllerConstructorExcp() {
    new AnimationController(null, 1000);
  }

  StringWriter stringWriter;
  Readable testModelFile;

  private void init() {
    stringWriter = new StringWriter();
    try {
      testModelFile = new FileReader("smalldemo.txt");
    } catch (FileNotFoundException e) {
      // DO NOTHING
    }
  }

  // test for illegal null build view
  @Test(expected = IllegalArgumentException.class)
  public void animationBuildExpt()  {
    init();
    IAnimationView animationView;
    AnimationController animationController;
    animationView = new TextView(stringWriter);
    animationController = new AnimationController(testModelFile, 100);
    animationController.build(animationView);
    animationController.build(null);

  }

  // test for checking the working of animation restart method.
  @Test
  public void animationRestart()  {
    init();
    IAnimationView animationView;
    AnimationController animationController;
    animationView = new TextView(stringWriter);
    animationController = new AnimationController(testModelFile, 100);
    animationController.build(animationView);
    animationController.animationRestart();
    assertEquals(1, animationController.getAnimationTick());
  }

  // test for checking increase speed
  @Test
  public void animationIncSpeed() {
    init();
    IAnimationView animationView;
    AnimationController animationController;
    animationView = new TextView(stringWriter);
    animationController = new AnimationController(testModelFile, 100);
    animationController.build(animationView);
    animationController.animationIncreaseSpeed();
    assertEquals(102, animationController.getAnimationSpeed());
  }

  // test for checking decrease speed
  @Test
  public void animationDecrSpeed() {
    init();
    IAnimationView animationView;
    AnimationController animationController;
    animationView = new TextView(stringWriter);
    animationController = new AnimationController(testModelFile, 100);
    animationController.build(animationView);
    animationController.animationDecreaseSpeed();
    assertEquals(98, animationController.getAnimationSpeed());
  }

}
