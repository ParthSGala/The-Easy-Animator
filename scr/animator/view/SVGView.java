package animator.view;

import java.io.IOException;

/**
 * the class represents the svg view type
 * we want to represent.
 */
public class SVGView extends AbstactAnimationView {

  private Appendable app;

  /**
   * construct the svg constructor.
   *
   * @param appendable append file
   */
  public SVGView(Appendable appendable) {

    if (appendable == null) {
      throw new IllegalArgumentException("INVALID NULL ARGUMENT");
    }

    this.app = appendable;
  }

  @Override
  public void output() {
    try {
      app.append(this.info + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("ERROR - APPENDING ERROR");
    }
  }

  @Override
  public String getViewType() {
    return "svg";
  }
}
