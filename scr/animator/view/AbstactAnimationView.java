package animator.view;

import animator.controller.AnimationController;
import animator.model.IShape;

import javax.swing.JFrame;
import java.util.List;

/**
 * the interface represents the way of implementing the animation model
 * to live view where one can display shapes with different variety of
 * possibilities.
 */
public abstract class AbstactAnimationView extends JFrame
    implements IAnimationView {
  protected String info;
  protected int speed;

  @Override
  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void setTick(int tick) {
    // DEFAULT
    throw new
        UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / SVG VIEW / VISUAL");
  }

  @Override
  public void setShapeName(String name) {
    // DEFAULT
    throw new
        UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / SVG VIEW / VISUAL");
  }

  @Override
  public void render(List<IShape> objects) {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / SVG VIEW");
  }

  @Override
  public void output() {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - VISUAL VIEW");
  }

  @Override
  public abstract String getViewType();

  @Override
  public void build(int x, int y, int width, int height) {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / SVG VIEW");
  }

  @Override
  public void addShapeControl(AnimationController controller) {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW " +
        "/ SVG VIEW / VISUAL");
  }

  @Override
  public void shapeStartStop() {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / " +
        "SVG VIEW / VISUAL");
  }

  @Override
  public void shapeLoop() {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / " +
        "SVG VIEW / VISUAL");
  }

  @Override
  public void shapeEdit() {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / " +
        "SVG VIEW / VISUAL");
  }

  @Override
  public void request() {
    // DEFAULT
    throw new UnsupportedOperationException("UNSUPPORTED OPERATION - TEXT VIEW / " +
        "SVG VIEW / VISUAL");
  }

}
