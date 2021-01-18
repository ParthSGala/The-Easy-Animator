package animator.model;

/**
 * This is the abstract class which represents the common properties between the shapes like the
 * common parameters and methods.
 */
public abstract class AShape implements IShape {
  protected String id;
  protected int width = 0;
  protected int height = 0;
  protected Position2D position2D;
  protected Color color;

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public Position2D getPosition2D() {
    return this.position2D;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public abstract String getType();

  @Override
  public String getShapeOutput() {
    return "shape " + this.id + " " + this.getType();
  }


}
