package animator.view;

import animator.controller.AnimationController;
import animator.model.IShape;

import java.util.List;

/**
 * the interface represents the way of implementing the animation model
 * to live view where one can display shapes with different variety of
 * possibilities.
 */
public interface IAnimationView {

  /**
   * the methods basically outputs the view for us.
   */
  public void output();

  /**
   * the method basically renders the animation view.
   *
   * @param iShapeList it is a list of shape one wants to render.
   */
  public void render(List<IShape> iShapeList);

  /**
   * the method sets the information.
   *
   * @param data information of model
   */
  public void setInfo(String data);

  /**
   * the method sets the speed.
   *
   * @param speed information of model
   */
  public void setSpeed(int speed);

  /**
   * the method sets the tick.
   *
   * @param tick time of keyframe
   */
  public void setTick(int tick);

  /**
   * the method gets the view type the user wants.
   */
  public String getViewType();

  /**
   * the method sets the shape name.
   * @param name shape name
   */
  public void setShapeName(String name);

  /**
   * the method build the view.
   *
   * @param x x coordinate in screen
   * @param y y coordinate in screen
   * @param width width of screen
   * @param height height of screen
   */
  public void build(int x, int y, int width, int height);

  /**
   * the method starts or stop animation.
   */
  public void shapeStartStop();

  /**
   * the method puts the animation in loop.
   */
  public void shapeLoop();

  /**
   * the method edits the animation.
   */
  public void shapeEdit();

  /**
   * the method adds the animation control to the animation window.
   *
   * @param controller animation controller
   */
  public void addShapeControl(AnimationController controller);

  /**
   * the method is a kind of a action listener
   * created to see the user keyboard output.
   */
  public void request();

}
