package controller;

import model.IModel;
import model.strategy.MancalaStrategy;
import model.strategy.StrategyFactory;
import view.MancalaTextualView;
import view.MancalaView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

public class MancalaPlayableController implements MancalaController, KeyListener {
  private IModel model;
  private MancalaView txtView;
  private MancalaView view;
  private Appendable output;
  private MancalaStrategy playerTwo;
  private int difficulty;
  private int numPlayers;

  public MancalaPlayableController(IModel model, MancalaView view) {
    this.model = model;
    this.view = view;
    view.addController(this);
    this.txtView = new MancalaTextualView(model); //used for textual representations
    difficulty = 3;
  }

  @Override
  public void playTwoPlayerGame() {
    numPlayers = 2;
    try {
      output.append(txtView.toString());
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void playSinglePlayerGame(int difficulty) {
    playerTwo = StrategyFactory.createStrategy(difficulty);
    numPlayers = 1;
    try {
      output.append(txtView.toString());
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void addIO(Readable rd, Appendable ap) {
    this.output = ap;
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  /**
   * Goes through a round of moves, starting with player 1 and then player 2.
   */
  private void playMoveCycle() {
    try {
      playerMove(1);
      view.render();
      while (model.getTurn() == 2) {
        playerMove(2);
        view.render();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    if (keyEvent.getKeyCode() == 10) { //Enter key
      playMoveCycle();
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    //don't care
  }

  private void cpuMove() {
    try {
      TimeUnit.SECONDS.sleep(2);
      int move = playerTwo.getMoveIndex(model);
      attemptMove("P2", Integer.toString(move));
      view.render();
      if (model.getTurn() == 2) {
        System.out.println("CPU scored! still P2 turn");
        cpuMove();
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  private void playerMove(int player) throws IOException {
    if (numPlayers == 2) {
      String move = view.getPlayerInput();
      attemptMove("P" + player, move);
      view.render();
    } else if (numPlayers == 1) {
      if (player == 2) {
        cpuMove();
      } else {
        String move = view.getPlayerInput();
        attemptMove("P1", move);
      }
      view.render();
    }
  }

  private void attemptMove(String player, String pitIndex) throws IOException {
    int inputTurn = (player.trim().equalsIgnoreCase("P1")) ? 1 : 2;
    if ((model.getTurn() != inputTurn)) {
      if (inputTurn == 1) {
        output.append("Invalid move: Not P1's turn\n");
      } else {
        output.append("Invalid move: Not P2's turn\n");
      }
      output.append(txtView.toString());
    } else { //correct player moving
      try {
        int inputIndex = Integer.parseInt(pitIndex);
        if (inputIndex <= 0 || inputIndex >= 14) {
          output.append("Invalid move: Invalid score pit\n");
          return;
        }
        int moveIndex = (player.equals("P1")) ? 14 - inputIndex : 7 - inputIndex;
        model.move(moveIndex);
        output.append(txtView.toString());
      } catch (IllegalArgumentException iae) {
        output.append(iae.getMessage()).append("\n");
      }
    }
  }
}
