package animator.view;

import java.io.IOException;


/**
 * the class represents the text view type formatted
 * output for the shapes.
 */

public class TextView extends AbstactAnimationView {
  private Appendable app;

  /**
   * construct the text view.
   *
   * @param appendable append file
   */
  public TextView(Appendable appendable) {

    if (appendable == null) {
      throw new IllegalArgumentException("INVALID NULL ARGUMENT");
    }

    this.app = appendable;
    this.speed = 0;
  }

  @Override
  public void output() {
    try {
      app.append(this.info);
    } catch (IOException e) {
      throw new IllegalArgumentException("ERROR - APPENDING ERROR");
    }
  }

  @Override
  public String getViewType() {
    return "text";
  }
}
