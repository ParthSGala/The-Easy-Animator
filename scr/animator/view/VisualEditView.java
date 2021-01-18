package animator.view;

import animator.controller.AnimationController;
import animator.model.Color;
import animator.model.IShape;
import animator.model.KeyFrame;
import animator.model.Position2D;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * the class represents the edit window which will pop in the
 * animation window where the user can edit the shapes
 * as they desire.
 */
public class VisualEditView extends AbstactAnimationView implements ActionListener {

  // panels for the animation and edit the animation
  Panel firstPanel;
  Panel secondPanel;
  Panel animationEditPanel = new Panel();
  Panel animationPlayBackPanel = new Panel();

  // scrollPane for the animation window
  JScrollPane scrollPane;

  // buttons required for different animation function like start etc
  JButton animationStartStopButton = new JButton("Start The Animation");
  JButton animationRestartButton = new JButton("Restart The Animation");
  JButton animationSpeedUpButton = new JButton("+");
  JButton animationSpeedDownButton = new JButton("-");
  JButton animationLoopButton = new JButton("Animation Loop : Off");
  JButton animationEditButton = new JButton("Edit");
  JButton animationRemoveButton = new JButton("Frame Remove");
  JButton animationAddButton = new JButton("Frame Add");

  // labels for the different animation modes
  JLabel editInstructions = new JLabel("");
  JLabel currentTickLabel = new JLabel("");
  JLabel currentShapeLabel = new JLabel("");

  // text fields for the editing window
  JTextField frameTxt = new JTextField(8);
  JTextField dimWidthTxt = new JTextField(8);
  JTextField dimHeightTxt = new JTextField(8);
  JTextField positionXTxt = new JTextField(8);
  JTextField positionYTxt = new JTextField(8);
  JTextField colorRedTxt = new JTextField(8);
  JTextField colorGreenTxt = new JTextField(8);
  JTextField colorBlueTxt = new JTextField(8);

  JLabel speedText = new JLabel();

  private AnimationController controller;
  private int animationTick;
  private String animationShapeName;
  private KeyFrame animationFrame;

  /**
   * the constructor constructs the edit window.
   */
  public VisualEditView() {
    super();
    firstPanel = new Panel();
    secondPanel = new Panel();
    scrollPane = new JScrollPane(firstPanel);
    animationFrame = new KeyFrame(0, animationShapeName, new Position2D(0, 0), 0, 0,
        new Color(0, 0, 0));
  }

  @Override
  public String getViewType() {
    return "edit";
  }

  @Override
  public void addShapeControl(AnimationController controller) {
    this.controller = controller;
    animationStartStopButton.addActionListener(evt -> controller.animationStartStop());
    animationRestartButton.addActionListener(evt -> controller.animationRestart());
    animationSpeedUpButton.addActionListener(evt -> controller.animationIncreaseSpeed());
    animationSpeedDownButton.addActionListener(evt -> controller.animationDecreaseSpeed());
    animationLoopButton.addActionListener(evt -> controller.animationLoop());
    animationEditButton.addActionListener(evt -> controller.animationEdit());
    animationRemoveButton.addActionListener(evt -> controller.removeAnimationFrame());
    animationAddButton.setActionCommand("add frame");
    animationAddButton.addActionListener(this);

    speedText = new JLabel("Animation Speed: " + this.speed);
    speedText.setHorizontalAlignment(SwingConstants.CENTER);

    secondPanel.setLayout(new GridLayout(2, 1));

    Panel speedButtons = new Panel();
    speedButtons.setLayout(new GridLayout(1, 2));
    speedButtons.add(animationSpeedUpButton);
    speedButtons.add(animationSpeedDownButton);

    Panel speedPanel = new Panel();
    speedPanel.setLayout(new GridLayout(2, 1));
    speedPanel.add(speedText);
    speedPanel.add(speedButtons);

    animationPlayBackPanel.setLayout(new GridLayout(5, 1));
    animationPlayBackPanel.add(speedPanel);
    animationPlayBackPanel.add(animationStartStopButton);
    animationPlayBackPanel.add(animationRestartButton);
    animationPlayBackPanel.add(animationLoopButton);
    animationPlayBackPanel.add(animationEditButton);
    secondPanel.add(animationPlayBackPanel);

    Panel infoPanel = new Panel();
    infoPanel.add(currentShapeLabel);
    infoPanel.add(currentTickLabel);

    Panel infoAndRemPanel = new Panel();
    infoAndRemPanel.setLayout(new GridLayout(3, 1));
    infoAndRemPanel.add(infoPanel);
    infoAndRemPanel.add(animationRemoveButton);
    infoAndRemPanel.add(animationAddButton);

    Panel framePanel = new Panel();
    framePanel.setLayout(new FlowLayout());
    framePanel.add(new JLabel("Frame:"));
    framePanel.add(frameTxt);

    Panel widthPanel = new Panel();
    widthPanel.setLayout(new FlowLayout());
    widthPanel.add(new JLabel("Dimension Width:"));
    widthPanel.add(dimWidthTxt);

    Panel heightPanel = new Panel();
    heightPanel.setLayout(new FlowLayout());
    heightPanel.add(new JLabel("Dimension Height:"));
    heightPanel.add(dimHeightTxt);

    Panel xPanel = new Panel();
    xPanel.setLayout(new FlowLayout());
    xPanel.add(new JLabel("Position X:"));
    xPanel.add(positionXTxt);

    Panel yPanel = new Panel();
    yPanel.setLayout(new FlowLayout());
    yPanel.add(new JLabel("Position Y:"));
    yPanel.add(positionYTxt);

    Panel rPanel = new Panel();
    rPanel.setLayout(new FlowLayout());
    rPanel.add(new JLabel("Red Color:"));
    rPanel.add(colorRedTxt);

    Panel gPanel = new Panel();
    gPanel.setLayout(new FlowLayout());
    gPanel.add(new JLabel("Green Color:"));
    gPanel.add(colorGreenTxt);

    Panel bPanel = new Panel();
    bPanel.setLayout(new FlowLayout());
    bPanel.add(new JLabel("Blue Color:"));
    bPanel.add(colorBlueTxt);


    Panel inputPanel = new Panel();
    inputPanel.setLayout(new GridLayout(4, 2));
    inputPanel.add(framePanel);
    inputPanel.add(widthPanel);
    inputPanel.add(heightPanel);
    inputPanel.add(xPanel);
    inputPanel.add(yPanel);
    inputPanel.add(rPanel);
    inputPanel.add(gPanel);
    inputPanel.add(bPanel);

    animationEditPanel.setLayout(new GridLayout(3, 1));
    animationEditPanel.add(editInstructions);
    animationEditPanel.add(infoAndRemPanel);
    animationEditPanel.add(inputPanel);
    secondPanel.add(animationEditPanel);

    animationEditPanel.setVisible(false);

    setFocusable(true);
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // DEFAULT : NOTHING TO DO
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          controller.animationPrevShape();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          controller.animationNextShape();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          controller.animationNextFrame();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          controller.animationPrevShape();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // does nothing
      }
    });
  }

  @Override
  public void request() {
    this.requestFocus();
  }

  @Override
  public void shapeStartStop() {
    if (animationStartStopButton.getText().equals("Start The Animation")) {
      animationStartStopButton.setText("Stop The Animation");
    } else {
      animationStartStopButton.setText("Start The Animation");
    }
  }

  @Override
  public void shapeLoop() {
    if (animationLoopButton.getText().equals("Animation Loop: Off")) {
      animationLoopButton.setText("Animation Loop: On");
    } else {
      animationLoopButton.setText("Animation Loop: Off");
    }
  }

  @Override
  public void shapeEdit() {
    requestFocus();
    if (editInstructions.getText().equals("")) {
      editInstructions.setText(
          String.format("<html><div WIDTH=%d><font size=2>%s</font></div></html>",
              secondPanel.getWidth(),
              "INSTRUCTION : "
                  + "PRESS UP / DOWN - "
                  + "SHAPES SWITCH, "
                  + "PRESS LEFT / RIGHT - "
                  + "GO TO NEXT FRAME, "
                  + "SELECT REMOVE KEYFRAME - "
                  + "REMOVE KEYFRAME / EDIT KEYFRAME, "
                  + "SELECT ADD FRAME - "
                  + "ADD KEYFRAME / EDIT KEYFRAME"));
    } else {
      editInstructions.setText("");
    }

    if (currentTickLabel.getText().equals("")) {
      currentTickLabel.setText("Animation Frame : " + animationTick);
    } else {
      currentTickLabel.setText("");
    }

    if (currentShapeLabel.getText().equals("")) {
      currentShapeLabel.setText("Shape : " + animationShapeName);
    } else {
      currentShapeLabel.setText("");
    }

    if (animationEditPanel.isVisible()) {
      animationEditPanel.setVisible(false);
    } else {
      animationEditPanel.setVisible(true);
    }

    if (animationStartStopButton.isVisible()) {
      animationStartStopButton.setVisible(false);
    } else {
      animationStartStopButton.setVisible(true);
    }

    if (animationRestartButton.isVisible()) {
      animationRestartButton.setVisible(false);
    } else {
      animationRestartButton.setVisible(true);
    }

    if (animationLoopButton.isVisible()) {
      animationLoopButton.setVisible(false);
    } else {
      animationLoopButton.setVisible(true);
    }

    if (animationEditButton.getText().equals("Edit Mode")) {
      animationEditButton.setText("Animation Playback");
    } else {
      animationEditButton.setText("Edit");
    }
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
    speedText.setText("Animation Speed: " + speed);
  }

  @Override
  public void setTick(int tick) {
    this.animationTick = tick;
    if (!currentTickLabel.getText().equals("")) {
      currentTickLabel.setText("Animation Frame: " + tick);
    }
  }

  @Override
  public void setShapeName(String name) {
    this.animationShapeName = name;
    if (name == null && !currentShapeLabel.getText().equals("")) {
      currentShapeLabel.setText("SHAPE: ");
    }
    if (!currentTickLabel.getText().equals("")) {
      currentShapeLabel.setText("SHAPE: " + animationShapeName);
    }
  }

  @Override
  public void build(int x, int y, int width, int height) {
    setSize(1000, 1000);
    firstPanel.setMinimumSize(new Dimension(200, 200));
    firstPanel.setPreferredSize(new Dimension(width + x, height + y));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(x, y);
    scrollPane = new JScrollPane(firstPanel);
    add(scrollPane, BorderLayout.CENTER);
    add(secondPanel, BorderLayout.EAST);

    setVisible(true);

  }

  @Override
  public void render(List<IShape> shapes) {
    firstPanel.draw(shapes);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("add frame")) {
      int tick = Integer.parseInt(frameTxt.getText());
      int width = Integer.parseInt(dimWidthTxt.getText());
      int height = Integer.parseInt(dimHeightTxt.getText());
      int x = Integer.parseInt(positionXTxt.getText());
      int y = Integer.parseInt(positionYTxt.getText());
      int r = Integer.parseInt(colorRedTxt.getText());
      int g = Integer.parseInt(colorGreenTxt.getText());
      int b = Integer.parseInt(colorBlueTxt.getText());

      this.animationFrame = new KeyFrame(tick, this.animationShapeName, new Position2D(x, y),
          width, height, new Color(r, g, b));
      controller.addAnimationFrame(animationFrame);
      frameTxt.setText("");
      dimWidthTxt.setText("");
      dimHeightTxt.setText("");
      positionXTxt.setText("");
      positionYTxt.setText("");
      colorRedTxt.setText("");
      colorGreenTxt.setText("");
      colorBlueTxt.setText("");
    }
  }
}
