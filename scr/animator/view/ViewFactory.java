package animator.view;

/**
 * the class represents a choice of choosing the different view patterns.
 */
public class ViewFactory {

  /**
   * the method selects the appropriate mode as desired.
   *
   * @param shapeType  shape type
   * @param appendable appending the arguments
   * @return desired mode
   */
  public static IAnimationView produce(String shapeType, Appendable appendable) {

    switch (shapeType) {
      case "text":
        return new TextView(appendable);
      case "svg":
        return new SVGView(appendable);
      case "visual":
        return new VisualView();
      case "edit":
        return new VisualEditView();
      default:
        throw new IllegalArgumentException("INVALID ARGUMENT");
    }
  }
}
