package view;

import controller.MancalaController;
import model.IModel;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Textual representation for a game of Mancala.  Looks like:
 *             "|   (06)(05)(04)(03)(02)(01)   |" +
 *             "|[07]                      [00]|" +
 *             "|   (08)(09)(10)(11)(12)(13)   |"
 */
public class MancalaTextualView implements MancalaView{
  private IModel model;
  private final Appendable output;

  public MancalaTextualView(IModel m) {
    this.model = m;
    output = new PrintStream(System.out);
  }

  public MancalaTextualView(IModel model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  public String toString() {
    int[] boardCopy = new int[14];
    //copies the marbles from the board into the local copy
    for (int i = 0; i <= 13; i++) {
      boardCopy[i] = model.getMarblesAt(i);
    }
    String output = String.format(
            "Turn: P" + model.getTurn() + "\n" +
            "|   (%2d)(%2d)(%2d)(%2d)(%2d)(%2d)   |\n" +
            "|[%2d]                           [%2d]|\n" +
            "|   (%2d)(%2d)(%2d)(%2d)(%2d)(%2d)   |\n",
            boardCopy[6], boardCopy[5], boardCopy[4], boardCopy[3], boardCopy[2], boardCopy[1],
            boardCopy[7], boardCopy[0], boardCopy[8], boardCopy[9], boardCopy[10],
            boardCopy[11], boardCopy[12], boardCopy[13]);
    if (model.isGameOver()) {
      output = output.concat("Game over! P1: "
              + model.getMarblesAt(0) + ", P2: " + model.getMarblesAt(7));
    }
    return output;
  }

  @Override
  public void render() {
    try {
      output.append(this.toString());
    } catch (IOException ioe) {
      //Deal with appending error
    }
  }

  @Override
  public void addController(MancalaController controller) {
    //doesn't have fields necessary
  }

  @Override
  public String getPlayerInput() {
    throw new IllegalCallerException("Cannot get input from this view");
  }
}
