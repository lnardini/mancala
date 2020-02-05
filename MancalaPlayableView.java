package view;

import controller.MancalaController;
import model.IModel;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MancalaPlayableView extends JFrame implements MancalaView {
  private IModel model;
  private DisplayField display;
  private InteractionField console;
  private Scanner scan;

  public MancalaPlayableView(IModel model) {
    this.model = model;
    this.display = new DisplayField();
    this.console = new InteractionField();
    this.scan = new Scanner(console);
    this.initialize();
  }

  private void initialize() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(400, 400);
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);
    this.setBackground(Color.gray);

    //display window
    display.setEditable(false);
    display.setMaximumSize(new Dimension(400, 300));
    GridBagConstraints displayConstraints = new GridBagConstraints();
    displayConstraints.gridx = 1;
    displayConstraints.gridy = 1;
    displayConstraints.weighty = 2;
    this.add(display, displayConstraints);

    //interactions
    console.setMinimumSize(new Dimension(400, 100));
    console.setVisible(true);
    console.setFont(new Font("displayFont", Font.BOLD, 20));
    GridBagConstraints interactionsConstraints = new GridBagConstraints();
    interactionsConstraints.gridx = 1;
    interactionsConstraints.gridy = 2;
    interactionsConstraints.weighty = 0;
    this.add(console, interactionsConstraints);
  }

  public void addController(MancalaController controller) {
    controller.addIO(console, display);
    console.addKeyListener(controller);
  }

  @Override
  public String getPlayerInput() {
    return console.getText();
  }

  @Override
  public void render() {
    this.setVisible(true);
    console.setText("");
  }
}
