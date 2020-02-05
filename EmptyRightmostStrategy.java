package model.strategy;

import model.IModel;

public class EmptyRightmostStrategy implements MancalaStrategy {

  @Override
  public int getMoveIndex(IModel model) {
    System.out.println("Emptying rightmost pit");
    for (int i = 6; i >= 1; i--) {
      if (model.getMarblesAt(i) != 0) {
        return 7 - i;
      }
    }
    throw new IllegalStateException("Game should already be over");
  }
}
