package animator.model;

import animator.util.AnimationBuilder;
import animator.view.ShapeTween;

import java.util.ArrayList;
import java.util.List;

/**
 * the class implements the methods required for the animation of the
 * for the shapes to come to live on the screen with the help
 * of the users, thus it has methods required and implemented
 * for displaying the animation.
 */
public class AnimationModel implements IAnimationModel {
  private int screenX;
  private int screenY;
  private int screenWidth;
  private int screenHeight;
  private List<IShape> shapesList;
  private List<Motion> motionsList;
  private List<KeyFrame> keyFramesList;

  /**
   * the constructor constructs the animation model of the shape.
   */
  public AnimationModel() {
    this.shapesList =
        new ArrayList<IShape>();
    this.motionsList =
        new ArrayList<Motion>();
    this.keyFramesList =
        new ArrayList<KeyFrame>();
  }

  public int getScreenX() {
    return screenX;
  }

  public int getScreenY() {
    return screenY;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public List<IShape> getShapeList() {
    return shapesList;
  }

  public List<Motion> getMotionList() {
    return motionsList;
  }

  public List<KeyFrame> getKeyFramesList() {
    return keyFramesList;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.screenX = x;
    this.screenY = y;
    this.screenWidth = width;
    this.screenHeight = height;
  }

  @Override
  public void addMotion(Motion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("INVALID MOTION - NULL");
    }

    if (this.getItem(m.getShapeID()) == null) {
      throw new IllegalArgumentException("INVALID ID - NONE EXIST");
    }

    if (overlappingMotions(m)) {
      throw new IllegalArgumentException("INVALID MOTION - OVERLAPPING MOTION");
    }

    this.motionsList.add(m);
  }

  @Override
  public int lastTickShape(String id) {
    int maximum = 0;

    for (Motion m : this.motionsList) {
      if (m.getShapeID().equals(id) && m.getEndTimeShape() > maximum) {
        maximum = m.getEndTimeShape();
      }
    }
    return maximum;
  }

  /**
   * the method checks for the last tick in the motion list.
   *
   * @return maximum tick
   */
  private int lastTickShape() {
    int maximum = 0;
    for (Motion m : this.motionsList) {
      if (m.getEndTimeShape() > maximum) {
        maximum = m.getEndTimeShape();
      }
    }
    return maximum;
  }

  /**
   * the method checks for the first tick in the motion list.
   *
   * @param id shape id
   * @return minimum tick
   */
  private int firstTickShape(String id) {
    int minimum = this.lastTickShape();
    for (Motion m : this.motionsList) {
      if (m.getShapeID().equals(id) && m.getStartTimeShape() < minimum) {
        minimum = m.getStartTimeShape();
      }
    }
    return minimum;
  }

  /**
   * the method check for over ridding motions with usage of the times.
   *
   * @param motion motion of the shapes between two time intervals.
   * @return boolean of whether motion is overriding or not.
   */
  private boolean overlappingMotions(Motion motion) {
    return (motion.getStartTimeShape() > this.firstTickShape(motion.getShapeID())
        && motion.getStartTimeShape() < this.lastTickShape(motion.getShapeID()))
        || (motion.getEndTimeShape() > this.firstTickShape(motion.getShapeID())
        && motion.getEndTimeShape() < this.lastTickShape(motion.getShapeID()));
  }

  @Override
  public IShape getItem(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("INVALID ID - NULL");
    }
    for (IShape shape : this.shapesList) {
      if (id.equals(shape.getID())) {
        return shape;
      }
    }
    return null;
  }

  @Override
  public String getStateShape() {
    String str = "";
    for (IShape shape : this.shapesList) {
      str = str.concat(shape.getShapeOutput());
      for (Motion m : this.motionsList) {
        if (m.getShapeID().equals(shape.getID())) {
          str = str.concat("\n" + m.getMotionShape());
        }
      }
      str = str.concat("\n");
    }

    if (str.length() == 0) {
      return str;
    }
    return str.substring(0, str.length() - 1);
  }

  @Override
  public void addShape(IShape shape)
      throws IllegalArgumentException {

    if (shape == null) {
      throw new IllegalArgumentException("INVALID ARGUMENT - NULL");
    }

    if (this.getShapeExist(shape.getID())) {
      throw new IllegalArgumentException("INVALID ARGUMENT - SHAPE EXISTS");
    }

    this.shapesList.add(shape);
  }

  /**
   * the method checks if shape is their or not in the shape list.
   *
   * @param id shape id
   * @return boolean
   */
  private boolean getShapeExist(String id) {
    for (IShape s : this.shapesList) {
      if (s.getID().equals(id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void removeShape(IShape shape)
      throws IllegalArgumentException {

    if (shape == null || this.getItem(shape.getID()) == null) {
      throw new IllegalArgumentException("INVALID ARGUMENT - NULL");
    }

    this.shapesList.remove(shape);
  }

  @Override
  public KeyFrame getFramesShape(String id, int tick) {
    for (KeyFrame keyFrame : this.keyFramesList) {
      if (keyFrame.getTick() == tick && keyFrame.getShapeID().equals(id)) {
        return keyFrame;
      }
    }
    return getNextFrameShape(id, tick);
  }

  @Override
  public KeyFrame getNextFrameShape(String id, int tick) {
    for (KeyFrame keyFrame : this.keyFramesList) {
      if (keyFrame.getTick() > tick && keyFrame.getShapeID().equals(id)) {
        return keyFrame;
      }
    }
    return null;
  }

  @Override
  public KeyFrame getPreviousFrameShape(String id, int tick) {
    for (int i = this.keyFramesList.size() - 1; i >= 0; i--) {
      KeyFrame frame = this.keyFramesList.get(i);
      if (frame.getShapeID().equals(id) && frame.getTick() < tick) {
        return frame;
      }
    }
    return this.getFramesShape(id, tick);
  }

  /**
   * the method gets keyframe one wants depending on the id.
   *
   * @param id shape id
   *
   * @return list of keyframe
   */
  public List<KeyFrame> getKeyFrames(String id) {
    List<KeyFrame> frameArrayList = new ArrayList<>();
    for (KeyFrame keyFrame : this.keyFramesList) {
      if (keyFrame.getShapeID().equals(id)) {
        frameArrayList.add(keyFrame);
      }
    }
    return frameArrayList;
  }

  /**
   * the method checks if keyframe is their or not in the keyframe list.
   *
   * @param id   shape id
   * @param tick keyframe time
   * @return boolean
   */
  private boolean getFrameExist(String id, int tick) {
    for (KeyFrame frame : this.keyFramesList) {
      if (frame.getTick() == tick && frame.getShapeID().equals(id)) {
        return true;
      }
    }
    return false;
  }

  /**
   * the method organizes the keyframe in order of the time or tick.
   */
  private void organizeKeyFrameList() {
    KeyFrame orderkeyFrame;
    for (int i = 1; i < this.keyFramesList.size(); i++) {
      for (int j = i; j > 0; j--) {
        if (this.keyFramesList.get(j).getTick() < this.keyFramesList.get(j - 1).getTick()) {
          orderkeyFrame = this.keyFramesList.get(j);
          this.keyFramesList.set(j, this.keyFramesList.get(j - 1));
          this.keyFramesList.set(j - 1, orderkeyFrame);
        }
      }
    }
  }

  @Override
  public void addKeyFrame(KeyFrame keyFrame)
      throws IllegalArgumentException {

    if (keyFrame == null) {
      throw new IllegalArgumentException("INVALID ARGUMENT - NULL");
    }
    if (this.getItem(keyFrame.getShapeID()) == null) {
      throw new IllegalArgumentException("INVALID ARGUMENT - NONE EXIST");
    }

    if (getFrameExist(keyFrame.getShapeID(), keyFrame.getTick())) {
      this.keyFramesList.remove(this.getFramesShape(keyFrame.getShapeID(), keyFrame.getTick()));
    }

    this.keyFramesList.add(keyFrame);
    this.organizeKeyFrameList();
  }


  @Override
  public void removeKeyFrame(KeyFrame keyFrame)
      throws IllegalArgumentException {

    if (keyFrame == null) {
      throw new IllegalArgumentException("INVALID ARGUMENT - NULL");
    }
    if (this.getItem(keyFrame.getShapeID()) == null) {
      throw new IllegalArgumentException("INVALID ARGUMENT - NONE EXIST");
    }

    this.keyFramesList.remove(keyFrame);
  }

  /**
   * the method gets maximum frame time.
   *
   * @return maximum time
   */
  public int getLastTickFrame() {
    int maximumFrametime = 1;
    for (KeyFrame keyFrame : this.keyFramesList) {
      if (keyFrame.getTick() > maximumFrametime) {
        maximumFrametime = keyFrame.getTick();
      }
    }
    return maximumFrametime;
  }

  private boolean frameIn(List<List<KeyFrame>> frameList, String id) {
    for (List<KeyFrame> list : frameList) {
      if (list.get(0).getShapeID().equals(id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public IShape getShapeAtFrame(KeyFrame keyFrame) {
    ShapeTween shapeTween = new ShapeTween(keyFrame, keyFrame, keyFrame.getTick());
    IShape iShape = this.getItem(keyFrame.getShapeID());
    return ShapeFactory.produce(iShape.getType(), keyFrame.getShapeID(),
        shapeTween.shapePosition2D(),
        shapeTween.shapeWidth(), shapeTween.shapeHeight(),
        shapeTween.shapeColor());
  }

  @Override
  public List<IShape> shapesAtTick(int tick) {
    List<IShape> shapes = new ArrayList<>();
    List<List<KeyFrame>> listOfKeyFrameSets = new ArrayList<>();
    List<KeyFrame> framesTemp = new ArrayList<>();

    for (KeyFrame k : this.keyFramesList) {
      framesTemp.add(k);
    }

    for (KeyFrame keyFrame : framesTemp) {
      if (!frameIn(listOfKeyFrameSets, keyFrame.getShapeID())) {
        listOfKeyFrameSets.add(getKeyFrames(keyFrame.getShapeID()));
      }
    }

    IShape currentShape;
    for (List<KeyFrame> listofKeyframe : listOfKeyFrameSets) {
      for (int i = 0; i < listofKeyframe.size() - 1; i++) {
        KeyFrame frame1 = listofKeyframe.get(i);
        KeyFrame frame2 = listofKeyframe.get(i + 1);
        ShapeTween t = new ShapeTween(frame1, frame2, tick);
        
        if (tick >= frame1.getTick() && tick <= frame2.getTick()) {
          currentShape = this.getItem(frame1.getShapeID());
          shapes.add(ShapeFactory.produce(currentShape.getType(), currentShape.getID(),
              t.shapePosition2D(), t.shapeWidth(), t.shapeHeight(), t.shapeColor()));
        }
      }
    }

    return shapes;
  }


  /**
   * the class is an inner class use to build the animation model.
   */
  public static class Builder implements AnimationBuilder<IAnimationModel> {

    private int screenX;
    private int screenY;
    private int screenWidth;
    private int screenHeight;
    private List<IShape> shapesList = new ArrayList<IShape>();
    private List<Motion> motionsList = new ArrayList<Motion>();
    private List<KeyFrame> keyFramesList = new ArrayList<KeyFrame>();

    @Override
    public IAnimationModel build() {
      IAnimationModel model = new AnimationModel();
      for (IShape shape : this.shapesList) {
        model.addShape(shape);
      }
      for (Motion motion : this.motionsList) {
        model.addMotion(motion);
      }
      for (KeyFrame keyFrame : this.keyFramesList) {
        model.addKeyFrame(keyFrame);
      }
      model.setBounds(this.screenX, this.screenY, this.screenWidth, this.screenHeight);

      return model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {
      this.screenX = x;
      this.screenY = y;
      this.screenWidth = width;
      this.screenHeight = height;
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added.
     *             No shape with this name should already exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added.
     *             The set of supported shapes is unspecified, but should
     *             include "ellipse" and "rectangle" as a minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          this.shapesList.add(new Rectangle(name));
          break;
        case "ellipse":
          this.shapesList.add(new Ellipse(name));
          break;
        default:
          throw new IllegalArgumentException("INVALID SHAPE");
      }
      return this;
    }

    /**
     * the method gets the shape we desired from the shape list.
     *
     * @param id shape id
     *
     * @return shape we want
     */
    private IShape getShapeItem(String id) {
      for (IShape shape : this.shapesList) {
        if (shape.getID().equals(id)) {
          return shape;
        }
      }
      return null;
    }

    /**
     * the method gets the required keyframe from the list.
     *
     * @param name shape name
     * @param tick keyframe time
     * @return
     */
    private KeyFrame gettingkf(String name, int tick) {
      for (KeyFrame keyFrame : this.keyFramesList) {
        if (keyFrame.getShapeID().equals(name) && keyFrame.getTick() == tick) {
          return keyFrame;
        }
      }
      return null;
    }

    /**
     * the method checks if a particular keyframe exists in the
     * keyframe list.
     *
     * @param id   shape id
     * @param tick keyframe time
     */
    private boolean existKeyFrame(String id, int tick) {
      for (KeyFrame frame : this.keyFramesList) {
        if (frame.getTick() == tick && frame.getShapeID().equals(id)) {
          return true;
        }
      }
      return false;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                       int h1, int r1, int g1, int b1,
                                                       int t2, int x2,
                                                       int y2, int w2, int h2, int r2,
                                                       int g2, int b2) {

      this.motionsList.add(new Motion(name, x1, y1, w1, h1, r1, g1, b1, x2,
          y2, w2, h2, r2, g2, b2,
          t1, t2));

      if (existKeyFrame(name, t1)) {
        this.keyFramesList.remove(this.gettingkf(name, t1));
      }
      this.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);

      if (existKeyFrame(name, t2)) {
        this.keyFramesList.remove(this.gettingkf(name, t2));
      }
      this.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);
      return this;
    }

    /**
     * Adds an individual keyframe to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @param x    The x-position of the shape
     * @param y    The y-position of the shape
     * @param w    The width of the shape
     * @param h    The height of the shape
     * @param r    The red color-value of the shape
     * @param g    The green color-value of the shape
     * @param b    The blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IAnimationModel> addKeyframe(String name, int t, int x, int y, int w,
                                                         int h, int r, int g, int b) {
      this.keyFramesList.add(new KeyFrame(t, name, new Position2D(x, y), w, h, new Color(r, g, b)));
      return this;
    }
  }

}
