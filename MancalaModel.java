package model;


import model.linkedList.CircularList;

/**
 * Class representing the basic version MVC model, i.e. the game data in a Mancala game.
 * This model version follows traditional rules, such as a two player limit.
 */
public class MancalaModel implements IModel{

  public enum Turn {
    P1, P2
  }

  private CircularList grid;
  private MancalaState state;
  private Turn turn;

  public MancalaModel() {
    grid = new CircularList(14);
    state = MancalaState.Playing;
    turn = Turn.P1;
  }

  @Override
  public void updateGameState() {
    if (sumPlayerMarbles(1) == 0 || sumPlayerMarbles(2) == 0) {
      state = MancalaState.Over;
      grid.get(0).addValue(sumPlayerMarbles(1));
      grid.get(7).addValue(sumPlayerMarbles(2));
      for (int i = 1; i <= 6; i++) {
        grid.get(i).clear();
      }
      for (int i = 8; i <= 13; i++) {
        grid.get(i).clear();
      }
    }
  }

  @Override
  public int sumPlayerMarbles(int player) {
    player = 2 - player; //if input 2 --> 0, input 1 --> 1
    int sum = 0;
    for (int i = 1; i < 7; i++) {
      sum += getMarblesAt(i + player * 7);
    }
    return sum;
  }

  @Override
  public int getMarblesAt(int pitIndex) {
    return grid.get(pitIndex % 14).getValue();
  }

  @Override
  public boolean move(int pitIndex) {
    //Exceptions
    System.out.println(pitIndex);
    if (state == MancalaState.Over || state == MancalaState.NotStarted) {
      throw new IllegalStateException("Game is not in progress");
    } else if (pitIndex == 0 || pitIndex == 7) {
      throw new IllegalArgumentException("Cannot move on a scoring pit");
    } else if ((turn == Turn.P1 && pitIndex < 7) || (turn == Turn.P2 && pitIndex > 7)) {
      throw new IllegalStateException("Moving out of turn");
    } else if (getMarblesAt(pitIndex) == 0) {
      throw new IllegalArgumentException("Cannot move on an empty pit");
    }
    pitIndex = validMove(pitIndex);
    if ((pitIndex == 0 && turn == Turn.P1) || (pitIndex == 7 && turn == Turn.P2)) {
      return true; //ended in scoring pit, keep same turn
    } else {
      checkCapture(pitIndex);
      turn = (turn == Turn.P1)? Turn.P2: Turn.P1; //toggle turn
      return false;
    }
  }

  /**
   * Checks and, if necessary, performs a capture according to mancala rules.
   * @param pitIndex
   */
  private void checkCapture(int pitIndex) {
    if (pitIndex == 0 || pitIndex == 7) {
      return;
    }
    int threshold = turn == Turn.P1? 7 : 0;
    if (getMarblesAt(pitIndex) == 1 && pitIndex > threshold && pitIndex < 7 + threshold) {
      grid.get(turn == Turn.P1? 0: 7).addValue(getMarblesAt(14 - pitIndex) + 1);
      grid.get(14 - pitIndex).clear();
      grid.get(pitIndex).clear();
    }
  }
  /**
   * Helper for performing a Mancala move.
   * @param pitIndex the pit the player is moving from
   * @return the pit index that the player's move landed on.
   */
  private int validMove(int pitIndex) {
    int marbles = getMarblesAt(pitIndex);
    grid.get(pitIndex).clear();
    int skipPitIndex = turn == Turn.P1? 7 : 0;
    while (marbles > 0) {
      pitIndex = (pitIndex + 1) % 14;
      if (pitIndex == skipPitIndex) {
        continue;
      }
      grid.get(pitIndex).addValue(1);
      marbles-= 1;
    }
    return pitIndex;
  }

  @Override
  public int getTurn() {
    return (this.turn == Turn.P1)? 1 : 2;
  }

  @Override
  public boolean isGameOver() {
    updateGameState();
    return this.state == MancalaState.Over;
  }
}
