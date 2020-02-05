package model.strategy;

import model.IModel;

/**
 * Strategy component prioritizing keeping the rightmost pit empty.
 */
public class EmptyFirstStrategy implements MancalaStrategy {
  private MancalaStrategy backup;

  public EmptyFirstStrategy(MancalaStrategy strategy) {
    this.backup = strategy;
  }

  @Override
  public int getMoveIndex(IModel model) {
    System.out.println("Possibly emptying first pit");
    if (model.getMarblesAt(6) != 0) {
      return 1;
    } else {
      return backup.getMoveIndex(model);
    }
  }
}
