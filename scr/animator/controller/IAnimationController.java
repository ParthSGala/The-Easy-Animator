package animator.controller;

import animator.model.KeyFrame;
import animator.view.IAnimationView;

/**
 * The interface represents the animation controller required for the animation.
 */
public interface IAnimationController {

  /**
   * the method loops the animation again and agian
   * until the user switched the loop of itself.
   */
  public void animationLoop();

  /**
   * the method starts the animation and stops
   * the animation depending on the user.
   */
  public void animationStartStop();

  /**
   * the methods pops up the edit window
   * where the user can edit the animation itself.
   */
  public void animationEdit();

  /**
   * the method adds animation frame to the overall animation.
   *
   * @param keyFrame animation keyframe
   */
  public void addAnimationFrame(KeyFrame keyFrame);

  /**
   * the method removes animation frame to the overall animation.
   */
  public void removeAnimationFrame();

  /**
   * the method gets the next animation shape.
   */
  public void animationNextShape();

  /**
   * the method gets the previous animation shape.
   */
  public void animationPrevShape();

  /**
   * the method gets the next keyframe in the animation.
   */
  public void animationNextFrame();

  /**
   * the method returns animation time.
   *
   * @return animation tick
   */
  public int getAnimationTick();

  /**
   * the method returns animation speed.
   *
   * @return animation speed
   */
  public int getAnimationSpeed();

  /**
   * the method gets the previous keyframe in the animation.
   */
  public void animationPrevFrame();

  /**
   * the method build the animation view in the window.
   *
   * @param view animation view
   */
  public void build(IAnimationView view);

  /**
   * the method restart the animation.
   */
  public void animationRestart();

  /**
   * the method increases the animation speed.
   */
  public void animationIncreaseSpeed();

  /**
   * the method decreases the animation speed.
   */
  public void animationDecreaseSpeed();

  /**
   * the method runs the whole animation.
   */
  public void run();

}
