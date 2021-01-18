package animator.model;

import java.util.List;

/**
 * This interface is the heart of the model which implements the animation
 * needed for the shapes to come to live on the screen with the help
 * of the users, thus it has methods required for displaying the animation.
 */
public interface IAnimationModel {

  /**
   * the method gets the screen x position or the coordinate of the background.
   *
   * @return the x value of screen.
   */
  public int getScreenX();

  /**
   * the method gets the screen y position or the coordinate of the background.
   *
   * @return the y value of screen.
   */
  public int getScreenY();

  /**
   * the method gets the screen width value from the background.
   *
   * @return the width value of screen.
   */
  public int getScreenWidth();

  /**
   * the method gets the screen height value from the background.
   *
   * @return the height value of screen.
   */
  public int getScreenHeight();

  /**
   * the method gets the list of the shapes added or created by the user.
   *
   * @return list of Ishapes or shapes defined by the user.
   */
  public List<IShape> getShapeList();

  /**
   * the method gets the list of the motions added or created by the user.
   *
   * @return list of motion or shapes defined by the user.
   */
  public List<Motion> getMotionList();

  /**
   * the method gets the list of the keyframes for the shape required.
   *
   * @return list of keyframes for the shape
   */
  public List<KeyFrame> getKeyFramesList();

  /**
   * the method sets the background required for the animation model to
   * live.
   */
  public void setBounds(int x, int y, int width, int height);

  /**
   * the method adds the motion to list of the motion after checking the nullness and
   * overriding motions in the list.
   *
   * @param m motion of the shape to be added.
   * @throws IllegalArgumentException if motion is null or overriding motion.
   */
  public void addMotion(Motion m)
      throws IllegalArgumentException;

  /**
   * the method gets the item based on the string shapeID added or created by the user.
   *
   * @param id id of the shape defined.
   * @return the shape corresponding to the shape.
   * @throws IllegalArgumentException when id not found.
   */
  public IShape getItem(String id)
      throws IllegalArgumentException;

  /**
   * the method gets the last tick in the motion list of the animation.
   *
   * @param id shape id
   * @return maximum tick
   */
  public int lastTickShape(String id);

  /**
   * the method return all shapes in string format.
   *
   * @return string description of all shapes
   */
  public String getStateShape();

  /**
   * the method add a shape to the shape list.
   *
   * @param shape given shape to be added
   * @throws IllegalArgumentException Null arguments or shape
   *                                  already exists
   */
  public void addShape(IShape shape)
      throws IllegalArgumentException;

  /**
   * the method removes a shape from the shape list.
   *
   * @param shape given shape to be added
   * @throws IllegalArgumentException Null arguments or shape
   *                                  does not exists in the list
   */
  public void removeShape(IShape shape)
      throws IllegalArgumentException;

  /**
   * the method gets the frame for that shape for the visual view.
   *
   * @param id   shape id
   * @param tick keyframe time
   * @return keyframe required
   */
  public KeyFrame getFramesShape(String id, int tick);

  /**
   * the method gets the next frame for that shape for the visual view.
   *
   * @param id   shape id
   * @param tick keyframe time
   * @return keyframe required
   */
  public KeyFrame getNextFrameShape(String id, int tick);

  /**
   * the method gets the previous frame for that shape for the visual view.
   *
   * @param id   shape id
   * @param tick keyframe time
   * @return keyframe required
   */
  public KeyFrame getPreviousFrameShape(String id, int tick);

  /**
   * the method gets the list of keyframes required as per the shape id.
   *
   * @param id shape id
   * @return list of keyframes
   */
  public List<KeyFrame> getKeyFrames(String id);

  /**
   * the method adds the keyframe to the keyframe list.
   *
   * @param keyFrame keyframe of shape
   * @throws IllegalArgumentException null arguments
   */
  public void addKeyFrame(KeyFrame keyFrame)
      throws IllegalArgumentException;

  /**
   * the method removes the keyframe from the keyframe list.
   *
   * @param keyFrame keyframe of shape
   * @throws IllegalArgumentException null arguments
   */
  public void removeKeyFrame(KeyFrame keyFrame)
      throws IllegalArgumentException;

  /**
   * the method gets the last time of the keyframe from the keyframe list.
   *
   * @return maximum time of the keyframe
   */
  public int getLastTickFrame();

  /**
   * the method gets the shape animation at that frame.
   *
   * @param keyFrame keyframe of the time
   *
   * @return animated shape
   */
  public IShape getShapeAtFrame(KeyFrame keyFrame);

  /**
   * the methods get the all the shape and motions at that particular
   * tick one wants.
   *
   * @param tick timeframe time
   *
   * @return shape list at that particular tick
   */
  public List<IShape> shapesAtTick(int tick);
}
