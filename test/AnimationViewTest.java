import animator.controller.AnimationController;
import animator.controller.IAnimationController;
import animator.view.IAnimationView;
import animator.view.SVGView;
import animator.view.TextView;
import animator.view.ViewFactory;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;


import static org.junit.Assert.assertEquals;

/**
 * the class represents the tests for different view types such as
 * text, svg and visual with edit variation.
 */
public class AnimationViewTest {
  StringWriter stringWriter;
  Readable readable;

  private void init() {
    stringWriter = new StringWriter();

    try {
      readable = new FileReader("smalldemo.txt");
    } catch (FileNotFoundException e) {
      // DO NOTHING
    }
  }

  // tests for TextView class
  @Test
  public void textoutput() {
    init();
    IAnimationView animationView;
    IAnimationController animationController;
    animationView = new TextView(stringWriter);
    animationController = new AnimationController(readable, 55);
    animationController.build(animationView);
    animationView.output();
    assertEquals("shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0",
        stringWriter.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void textConstructorExp1() {
    IAnimationView animationView;
    IAnimationController animationController;
    animationView = new TextView(null);
  }

  // tests for svgView class
  @Test
  public void svgoutput() {
    init();
    IAnimationView animationView;
    IAnimationController animationController;
    animationView = new SVGView(stringWriter);
    animationController = new AnimationController(readable, 55);
    animationController.build(animationView);
    animationView.output();
    assertEquals("<svg width=\"560\" height=\"430\" "
            + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"18ms\" dur=\"180ms\" "
            + "attributeName=\"x\" from=\"200\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18ms\" dur=\"180ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18ms\" dur=\"180ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18ms\" dur=\"180ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18ms\" dur=\"180ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"180ms\" dur=\"900ms\" "
            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"180ms\" dur=\"900ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"180ms\" dur=\"900ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"180ms\" dur=\"900ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"180ms\" dur=\"900ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"918ms\" "
            + "attributeName=\"x\" from=\"300\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"918ms\" "
            + "attributeName=\"y\" from=\"300\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"918ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"918ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"918ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"918ms\" dur=\"1260ms\" "
            + "attributeName=\"x\" from=\"300\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"918ms\" dur=\"1260ms\" "
            + "attributeName=\"y\" from=\"300\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"918ms\" dur=\"1260ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"918ms\" dur=\"1260ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"918ms\" dur=\"1260ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1800ms\" "
            + "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1800ms\" "
            + "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1800ms\" "
            + "attributeName=\"width\" from=\"25\" to=\"25\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1800ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1800ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"60\" ry=\"30\" "
            + "fill=\"rgb(0,0,255}\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"108ms\" dur=\"360ms\" " +
            "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"108ms\" dur=\"360ms\" " +
            "attributeName=\"cy\" from=\"70\" to=\"70\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"108ms\" dur=\"360ms\" " +
            "attributeName=\"rw\" from=\"60\" to=\"60\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"108ms\" dur=\"360ms\" " +
            "attributeName=\"rh\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"108ms\" dur=\"360ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"360ms\" dur=\"900ms\" " +
            "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"360ms\" dur=\"900ms\" " +
            "attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"360ms\" dur=\"900ms\" " +
            "attributeName=\"rw\" from=\"60\" to=\"60\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"360ms\" dur=\"900ms\" " +
            "attributeName=\"rh\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"360ms\" dur=\"900ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"1260ms\" " +
            "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"1260ms\" " +
            "attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"1260ms\" " +
            "attributeName=\"rw\" from=\"60\" to=\"60\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"1260ms\" " +
            "attributeName=\"rh\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"900ms\" dur=\"1260ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1440ms\" " +
            "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1440ms\" " +
            "attributeName=\"cy\" from=\"370\" to=\"370\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1440ms\" " +
            "attributeName=\"rw\" from=\"60\" to=\"60\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1440ms\" " +
            "attributeName=\"rh\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1260ms\" dur=\"1440ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1440ms\" dur=\"1800ms\" " +
            "attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1440ms\" dur=\"1800ms\" " +
            "attributeName=\"cy\" from=\"370\" to=\"370\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1440ms\" dur=\"1800ms\" " +
            "attributeName=\"rw\" from=\"60\" to=\"60\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1440ms\" dur=\"1800ms\" " +
            "attributeName=\"rh\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1440ms\" dur=\"1800ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>\n"
        ,
        stringWriter.toString()
    );
  }

  // test for abstract animation view for unsupported operation

  // settick method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void setTickExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).setTick(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void setTickExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).setTick(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void setTickExcp3() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).setTick(1);
  }

  // setshape method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void setshapeExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).setShapeName("rectangle");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void setshapeExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).setShapeName("rectangle");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void setshapeExcp3() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).setShapeName("rectangle");
  }

  // output method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void outputExcp() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).output();
  }

  // request method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void requestExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).request();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void requestExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).request();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void requestExcp3() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).request();
  }

  // ShapeEdit method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void shapeEditExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).shapeEdit();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shapeEditExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).shapeEdit();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shapeEditExcp3() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).shapeEdit();
  }

  // shapeStartStop method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void shapeStartStopExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).shapeStartStop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shapeStartStopExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).shapeStartStop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shapeStartStopExcp3() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).shapeStartStop();

  }

  // shapeLoop method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void shapeLoopExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("visual", new StringBuffer()).shapeLoop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shapeLoopExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).shapeLoop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shapeLoopExcp3() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).shapeLoop();
  }

  // build method unsupported operation exception tests
  @Test(expected = UnsupportedOperationException.class)
  public void buildExcp1() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("svg", new StringBuffer()).shapeLoop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void buildExcp2() {
    ViewFactory viewFactory = new ViewFactory();
    viewFactory.produce("text", new StringBuffer()).shapeLoop();
  }


}
