package model.strategy;

public class StrategyFactory {

  /**
   * Creates a MancalaStrategy opponent for the player to play against.
   * @param difficulty the difficulty of the CPU (1 = easy, 2 = medium, 3 = hard)
   * @return the opponent for a Mancala game.
   */
  public static MancalaStrategy createStrategy(int difficulty) {
    switch (difficulty) {
      case 1: return new ScoreIfPossibleStrategy(
              new EmptyRightmostStrategy());
      case 2: return new ScoreIfPossibleStrategy(
              new CaptureStrategy(
                      new EmptyRightmostStrategy()));
      case 3: return new ScoreIfPossibleStrategy(
              new CaptureStrategy(
                      new EmptyFirstStrategy(
                              new CaptureDefenseStrategy(
                                      new EmptyRightmostStrategy()))));
      default: throw new IllegalArgumentException("Difficulty must be in range 1-3");
    }
  }
}
