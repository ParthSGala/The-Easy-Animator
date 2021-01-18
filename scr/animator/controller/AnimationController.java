package animator.controller;

import animator.model.IAnimationModel;
import animator.model.IShape;
import animator.model.KeyFrame;
import animator.model.Motion;
import animator.model.ShapeFactory;
import animator.util.AnimationReader;
import animator.view.IAnimationView;
import animator.model.AnimationModel;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * the class represents the controller which would be used to control the animations for
 * displaying.
 */
public class AnimationController implements IAnimationController {
  // animation speed properties
  private int speed;
  private int milliseconds;
  private int currentShapeIndex;

  // animation playing properties
  private boolean animationloop;
  private boolean animationpaused;
  private boolean animationplayback;
  private IShape iShape;

  int tick = 1;
  IAnimationModel animationModel;
  IAnimationView animationView;
  Timer timer;

  /**
   * the constructor constructs the controller.
   *
   * @param readable read files
   * @param secondsPerTick tick per second
   */
  public AnimationController(Readable readable, int secondsPerTick) {
    if (readable == null) {
      throw new IllegalArgumentException("null Readable object.");
    }
    this.read(readable);
    this.animationloop = false;
    this.animationpaused = true;
    this.animationplayback = true;
    this.currentShapeIndex = 0;
    iShape = animationModel.getShapeAtFrame(animationModel.getKeyFramesList().get(0));
    this.speed = secondsPerTick;
    this.milliseconds = (int) ((1 / (double) this.speed) * 1000);
  }


  @Override
  public void build(IAnimationView animationViewView) {
    if (animationViewView == null) {
      throw new IllegalArgumentException("Null view.");
    }
    this.animationView = animationViewView;
    this.animationView.setInfo(this.outputTypeData(animationViewView.getViewType()));
    animationView.setSpeed(speed);

    if (animationView.getViewType().equals("edit")) {
      animationView.addShapeControl(this);
    }
  }

  @Override
  public void animationLoop() {
    this.animationloop = !this.animationloop;

    if (animationView.getViewType().equals("edit")) {
      animationView.shapeLoop();
    }
  }

  @Override
  public void animationStartStop() {
    this.animationpaused = !this.animationpaused;

    if (animationView.getViewType().equals("edit")) {
      animationView.shapeStartStop();
    }
  }

  @Override
  public void animationEdit() {
    this.animationplayback = !this.animationplayback;

    if (animationView.getViewType().equals("edit")) {
      animationView.shapeEdit();
    }
  }

  @Override
  public void animationNextShape() {
    IShape iShapeNext = null;
    if (currentShapeIndex < animationModel.getShapeList().size() - 1) {
      currentShapeIndex += 1;
      iShapeNext = animationModel.getShapeList().get(currentShapeIndex);
    }

    IShape shapeTempory = null;
    if (iShapeNext != null) {
      shapeTempory = animationModel.getShapeAtFrame(animationModel
          .getFramesShape(iShapeNext.getID(),tick));
    }

    if (shapeTempory != null) {
      iShape = shapeTempory;
    }
    if (animationView.getViewType().equals("edit")) {
      animationView.setShapeName(iShape.getID());
    }
  }

  @Override
  public void animationPrevShape() {
    IShape iShapePrevious = null;
    if (currentShapeIndex > 0) {
      currentShapeIndex -= 1;
      iShapePrevious = animationModel.getShapeList().get(currentShapeIndex);
    }

    IShape iShape1 = null;
    if (iShapePrevious != null) {
      iShape1 = animationModel.getShapeAtFrame(animationModel
          .getFramesShape(iShapePrevious.getID(),tick));
    }

    if (iShape1 != null) {
      iShape = iShape1;
    }
    if (animationView.getViewType().equals("edit")) {
      animationView.setShapeName(iShape.getID());
    }
  }

  @Override
  public void animationNextFrame() {
    KeyFrame next = animationModel.getNextFrameShape(iShape.getID(),tick);
    if (next != null) {
      IShape shapeTemp = animationModel.getShapeAtFrame(next);
      tick = next.getTick();
      iShape = shapeTemp;
    }
    if (animationView.getViewType().equals("edit")) {
      animationView.setTick(tick);
    }
  }

  @Override
  public void animationPrevFrame() {
    KeyFrame prev = animationModel.getPreviousFrameShape(iShape.getID(),tick);
    if (prev != null) {
      IShape shapeTemp = animationModel.getShapeAtFrame(prev);
      tick = prev.getTick();
      iShape = shapeTemp;
    }
    if (animationView.getViewType().equals("edit")) {
      animationView.setTick(tick);
    }
  }

  @Override
  public void removeAnimationFrame() {
    KeyFrame cFrame = animationModel.getFramesShape(iShape.getID(),tick);
    KeyFrame nFrame = animationModel.getNextFrameShape(iShape.getID(),tick);
    KeyFrame pFrame = animationModel.getPreviousFrameShape(iShape.getID(),tick);

    if (cFrame != null) {
      animationModel.removeKeyFrame(cFrame);
    }
    if (nFrame != null) {
      this.animationNextFrame();
    } else if (pFrame != null) {
      this.animationPrevFrame();
    } else {
      animationModel.removeShape(iShape);
    }
    animationView.request();
  }

  @Override
  public void addAnimationFrame(KeyFrame k) {
    animationModel.addKeyFrame(k);
    animationView.request();
  }

  private void read(Readable readable) {
    this.animationModel = AnimationReader.parseFile(readable, new AnimationModel.Builder());
  }

  /**
   * the method gets the required shape.
   *
   * @param shape animation shape
   *
   * @return shape required
   */
  private IShape getShapeRequired(IShape shape) {
    IShape iShape1 = null;
    for (Motion motion : animationModel.getMotionList()) {
      if (motion.getShapeID().equals(shape.getID())) {
        iShape1 = ShapeFactory.produce(shape.getType(), shape.getID(),
            motion.getStartPositionShape(), motion.getStartWidthShape(),
            motion.getStartHeightShape(), motion.getStartColorShape());
        break;
      }
    }
    return iShape1;
  }

  /**
   * the method selects the type of output one wants
   * the animation in.
   * @param viewType output view type
   *
   * @return string format output
   */
  private String outputTypeData(String viewType) {
    switch (viewType) {
      case "svg":
        return this.svgoutput();
      case "text":
        return this.animationModel.getStateShape();
      default:
        return null;
    }
  }

  @Override
  public int getAnimationTick() {
    return this.tick;
  }

  @Override
  public int getAnimationSpeed() {
    return this.speed;
  }
  /**
   * the method converts animation description to
   * svg view format.
   *
   * @return svg format output
   */

  private String svgoutput() {
    StringWriter stringWriter = new StringWriter();
    List<IShape> shapeslist = animationModel.getShapeList();
    List<Motion> motionslist = animationModel.getMotionList();

    stringWriter.append("<svg width=\"" + (animationModel.getScreenWidth()
        + animationModel.getScreenX()) + "\" height=\""
        + (animationModel.getScreenHeight() + animationModel.getScreenY())
        + "\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (IShape shape : shapeslist) {
      IShape ogShape = this.getShapeRequired(shape);
      if (shape.getType().equals("rectangle")) {
        stringWriter.append("<rect id=\"" + ogShape.getID()
            + "\" x=\"" + ogShape.getPosition2D().getX() + "\" y=\""
            + ogShape.getPosition2D().getY()
            + "\" width=\"" + ogShape.getWidth() + "\" height=\"" + ogShape.getHeight()
            + "\" fill=\"rgb(" + ogShape.getColor().getRed() + "," + ogShape.getColor().getGreen()
            + "," + ogShape.getColor().getBlue() + ")\" visibility=\"visible\" >\n");
        for (Motion motion : motionslist) {
          if (motion.getShapeID().equals(shape.getID())) {
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"x\"" +
                " from=\"" + motion.getStartPositionShape().getX() + "\" to=\"" +
                motion.getEndPositionShape().getX() + "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"y\"" +
                " from=\"" + motion.getStartPositionShape().getY()
                + "\" to=\"" +
                motion.getEndPositionShape().getY() + "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"" +
                "width\"" +
                " from=\"" + motion.getStartWidthShape() + "\" to=\""
                + motion.getEndWidthShape() +
                "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"" +
                "height\"" +
                " from=\"" + motion.getStartHeightShape() + "\" to=\""
                + motion.getEndHeightShape() +
                "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"" +
                "fill\"" +
                " from=\"rgb(" + motion.getStartColorShape().getRed() + ","
                + motion.getStartColorShape().getGreen()
                + "," + motion.getStartColorShape().getBlue() + ")\" to=\"rgb("
                + motion.getEndColorShape().getRed()
                + "," + motion.getEndColorShape().getGreen() + ","
                + motion.getEndColorShape().getBlue()
                + ")\" fill=\"freeze\" />\n");
          }
        }
        stringWriter.append("</rect>\n");
      }
      if (shape.getType().equals("ellipse")) {
        stringWriter.append("<ellipse id=\"" + ogShape.getID()
            + "\" cx=\"" + ogShape.getPosition2D().getX() + "\" cy=\""
            + ogShape.getPosition2D().getY()
            + "\" rx=\"" + ogShape.getWidth() / 2 + "\" ry=\""
            + ogShape.getHeight() / 2
            + "\" fill=\"rgb(" + ogShape.getColor().getRed() + ","
            + ogShape.getColor().getGreen()
            + "," + ogShape.getColor().getBlue() + "}\" visibility=\"visible\" >\n");
        for (Motion motion : motionslist) {
          if (motion.getShapeID().equals(shape.getID())) {
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"cx\"" +
                " from=\"" + motion.getStartPositionShape().getX() + "\" to=\"" +
                motion.getEndPositionShape().getX() + "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"cy\"" +
                " from=\"" + motion.getStartPositionShape().getY() + "\" to=\"" +
                motion.getEndPositionShape().getY() + "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"rw\"" +
                " from=\"" + motion.getStartWidthShape() / 2 + "\" to=\""
                + motion.getEndWidthShape()
                / 2 +
                "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"rh\"" +
                " from=\"" + motion.getStartHeightShape() / 2 + "\" to=\""
                + motion.getEndHeightShape()
                / 2 +
                "\" fill=\"freeze\" />\n");
            stringWriter.append("<animate attributeType=\"xml\" begin=\""
                + motion.getStartTimeShape() * this.milliseconds
                + "ms\" dur=\"" + motion.getEndTimeShape() * this.milliseconds
                + "ms\" attributeName=\"" +
                "fill\"" +
                " from=\"rgb(" + motion.getStartColorShape().getRed() + "," +
                motion.getStartColorShape().getGreen()
                + "," + motion.getStartColorShape().getBlue()
                + ")\" to=\"rgb(" +
                motion.getEndColorShape().getRed()
                + "," + motion.getEndColorShape().getGreen()
                + "," + motion.getEndColorShape().getBlue()
                + ")\" fill=\"freeze\" />\n");
          }
        }
        stringWriter.append("</ellipse>\n");
      }
    }
    stringWriter.append("</svg>");

    return stringWriter.toString();
  }

  @Override
  public void animationIncreaseSpeed() {
    this.speed += 2;
    milliseconds = (int) ((1 / (double) speed) * 1000);
    animationView.setSpeed(speed);
    if (!(animationView.getViewType().equals("text")
        || animationView.getViewType().equals("svg"))) {
      timer.stop();
      this.animationTimer();
    }
  }

  @Override
  public void animationDecreaseSpeed() {
    if (speed <= 2) {
      speed = 0;
    } else {
      this.speed -= 2;
    }
    milliseconds = (int) ((1 / (double) speed) * 1000);
    animationView.setSpeed(speed);
    if (!(animationView.getViewType().equals("text")
        || animationView.getViewType().equals("svg"))) {
      timer.stop();
      this.animationTimer();
    }
  }

  @Override
  public void animationRestart() {
    tick = 1;
    if (!(animationView.getViewType().equals("text")
        || animationView.getViewType().equals("svg"))) {
      animationView.setTick(1);
    }
  }

  @Override
  public void run() {
    if (animationView.getViewType().equals("visual")
        || animationView.getViewType().equals("edit")) {
      animationView.build(animationModel.getScreenX(),
          animationModel.getScreenY(),
          animationModel.getScreenWidth(),
          animationModel.getScreenHeight());
      this.animationTimer();
    } else {
      animationView.output();
    }
  }

  /**
   * the method starts the animation and performs
   * the function required for the animation.
   */
  private void animationTimer() {
    timer = new Timer(milliseconds, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (animationView.getViewType().equals("edit")) {
          animationView.setTick(tick);
          if (animationplayback) {
            if (animationloop && tick == animationModel.getLastTickFrame()) {
              tick = 1;
            } else if (!animationloop && tick == animationModel.getLastTickFrame()) {
              List<IShape> shapeList =
                  animationModel.shapesAtTick(animationModel.getLastTickFrame());
              animationView.render(shapeList);
            }
            if (animationpaused) {
              List<IShape> shapeList = animationModel.shapesAtTick(tick);
              animationView.render(shapeList);
            }
            if (!animationpaused) {
              List<IShape> shapeList = animationModel.shapesAtTick(tick);
              animationView.render(shapeList);
              tick++;
            }
          }
          if (!animationplayback) {
            animationView.setShapeName(iShape.getID());
            List<IShape> shapeList = new ArrayList<IShape>();
            shapeList.add(iShape);
            animationView.render(shapeList);
          }
        }
        if (animationView.getViewType().equals("visual")) {
          List<IShape> shapeList = new ArrayList<IShape>();
          shapeList = animationModel.shapesAtTick(tick++);
          animationView.render(shapeList);
        }
      }
    });
    timer.start();
  }

}
