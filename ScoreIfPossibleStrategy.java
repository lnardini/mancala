package model.strategy;

import model.IModel;

/**
 * Strategy pattern class responsible for ending a move in the score pit or choosing the best move.
 */
public class ScoreIfPossibleStrategy implements MancalaStrategy {
  private MancalaStrategy backup;

  public ScoreIfPossibleStrategy(MancalaStrategy strategy) {
    this.backup = strategy;
  }

  @Override
  public int getMoveIndex(IModel model) {
    System.out.println("Attempting to score");
    for (int i = 6; i >= 1; i--) {
      if (7 - i == model.getMarblesAt(i)) {
        return 7 - i;
      }
    }
    return backup.getMoveIndex(model);
  }
}
