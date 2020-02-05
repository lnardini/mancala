package model.strategy;

import model.IModel;

public interface MancalaStrategy {

  /**
   * Determines the optimal move for P2 to make in the given mancala game.
   * Result is bounded 1 <= R <= 6 where 1 is the rightmost pit.
   * @param model the game being played
   * @return the move according to the desired strategy
   */
  int getMoveIndex(IModel model);
}
