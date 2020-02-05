package model.strategy;

import model.IModel;

import java.util.Optional;

/**
 * Strategy pattern component that detects opponent captures and defends against them.
 * If an opposing capture is available, two options are available:
 * -Picks the rightmost move that would put a marble in the capturable hole, negating the move
 * -If no move exists, move the marbles that would be captured.
 */
public class CaptureDefenseStrategy implements MancalaStrategy {
  private MancalaStrategy backup;

  public CaptureDefenseStrategy(MancalaStrategy strategy) {
    this.backup = strategy;
  }

  @Override
  public int getMoveIndex(IModel model) {
    System.out.println("Seeing if capture defense is necessary");
    //Searching for empty pit on opponent side corresponding to non-empty pit on bot side
    for (int i = 13; i >= 8; i--) {
      if (model.getMarblesAt(i) == 0 && model.getMarblesAt(14 - i) >= 2) {
        //Sees if opponent has a move that lands in this empty pit
        Optional<Integer> validCapture = this.opponentCanScoreIn(model, i);
        if (validCapture.isEmpty()) {
          continue;
        }
        //sees if any move will land in the empty square
        for (int j = 6; j >= 1; j--) {
          if (j + model.getMarblesAt(j) >= i && model.getMarblesAt(j) != 0) {
            return 7 - j;
          }
        }
        return validCapture.get();
      }
    }
    return backup.getMoveIndex(model);
  }

  private Optional<Integer> opponentCanScoreIn(IModel model, int index) {
    for (int j = 8; j <= 12; j++) {
      if ((j + model.getMarblesAt(j)) % 14 == index && model.getMarblesAt(index - 7) != 0) {
        return Optional.of(index - 7);
      }
    }
    return Optional.empty();
  }
}
