package model.strategy;

import model.IModel;

/**
 * Strategy pattern component that attempts to score in one of the first 3 pits.
 */
public class EarlyScoreStrategy implements MancalaStrategy {
  private MancalaStrategy backup;

  public EarlyScoreStrategy(MancalaStrategy strategy) {
    this.backup = strategy;
  }

  @Override
  public int getMoveIndex(IModel model) {
    for (int i = 6; i >= 4; i--) {
      if (model.getMarblesAt(i) >= 7 - i) {
        return 7 - i;
      }
    }
    return backup.getMoveIndex(model);
  }
}
